package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Genre;
import com.example.filrouge_back.exceptions.ResourceNotFoundException;
import com.example.filrouge_back.mappers.PageMapper;
import com.example.filrouge_back.models.apidtos.ActorsApiResponse;
import com.example.filrouge_back.models.apidtos.MovieApiResponse;
import com.example.filrouge_back.models.apidtos.PersonApiResponse;
import com.example.filrouge_back.models.apidtos.ShowApiResponse;
import com.example.filrouge_back.models.entitydtos.MediaDetailDTO;
import com.example.filrouge_back.models.entitydtos.MediaSummaryDTO;
import com.example.filrouge_back.mappers.MediaMapper;
import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.models.entitydtos.PageDTO;
import com.example.filrouge_back.models.enums.JobForMedia;
import com.example.filrouge_back.models.enums.MediaType;
import com.example.filrouge_back.repositories.MediaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private final GenreService genreService;
    private final ProfessionalService professionalService;
    private final PageMapper pageMapper;

    public List<MediaSummaryDTO> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();

        return mediaMapper.mediaListToMediaSummaryDtoList(mediaList);
    }

    public PageDTO<MediaSummaryDTO> getAllMediaByDefaultSorting(int page) {
        Page<Media> mediaPage = mediaRepository.findAllByOrderById(PageRequest.of(page, 24));
        return pageMapper.pageToPageDto(
                mediaPage,
                mediaMapper.mediaListToMediaSummaryDtoList(mediaPage.getContent())
        );
    }

    public MediaDetailDTO getMediaById(UUID mediaId) {
        Optional<Media> optionalMedia = mediaRepository.findById(mediaId);

        if (optionalMedia.isPresent()) {
            return mediaMapper.mediaToMediaDetailDto(optionalMedia.get());
        } else {
            throw new ResourceNotFoundException("Media not found at id " + mediaId);
        }
    }

    public PageDTO<MediaSummaryDTO> getMediaByGenre(String genre, int page) {
        Page<Media> mediaPage = mediaRepository.findByGenres_GenreName(genre, PageRequest.of(page, 24));
        return pageMapper.pageToPageDto(
                mediaPage,
                mediaMapper.mediaListToMediaSummaryDtoList(mediaPage.getContent())
        );
    }

    public PageDTO<MediaSummaryDTO> getMediaByType(MediaType type, int page) {
        Page<Media> mediaPage = mediaRepository.findByType(type, PageRequest.of(page, 24));
        return pageMapper.pageToPageDto(
                mediaPage,
                mediaMapper.mediaListToMediaSummaryDtoList(mediaPage.getContent())
        );
    }

    public PageDTO<MediaSummaryDTO> getMediaByReleaseDateDescending(int page) {
        Page<Media> mediaPage = mediaRepository.findAllByOrderByIdDescReleaseYearDesc(
                PageRequest.of(page, 24));
        return pageMapper.pageToPageDto(
                mediaPage,
                mediaMapper.mediaListToMediaSummaryDtoList(mediaPage.getContent())
        );
    }

    public PageDTO<MediaSummaryDTO> searchMediaByTitle(String keyword, int page) {
        Page<Media> mediaPage = mediaRepository.searchByTitleContainsIgnoreCase(keyword, PageRequest.of(page, 24));
        return pageMapper.pageToPageDto(
                mediaPage,
                mediaMapper.mediaListToMediaSummaryDtoList(mediaPage.getContent())
        );
    }


    public List<Media> findMediaByGenresList(List<Genre> genres) {
        return new ArrayList<>(mediaRepository.findByGenresList(genres).stream().toList());
    }

    public boolean isTableEmpty() {
        return mediaRepository.count() == 0;
    }

    @Transactional
    public Media saveMovie(MovieApiResponse response) {
        Media movie = mediaMapper.movieApiResponseToMedia(response);

        movie.setGenres(genreService.saveMediaGenresFromGenreNames(response.getMovie().getGenres()));

        mediaRepository.save(movie);

        professionalService.saveMediaProfessionals(response.getMovie().getCrew().getDirectors(), movie, JobForMedia.DIRECTOR);
        professionalService.saveMediaProfessionals(response.getMovie().getCrew().getProducers(), movie, JobForMedia.PRODUCER);
        professionalService.saveMediaProfessionals(response.getMovie().getCrew().getWriters(), movie, JobForMedia.WRITER);

        mediaRepository.save(movie);

        return movie;
    }

    @Transactional
    public Media saveShow(ShowApiResponse.Show response) {
        Media show = mediaMapper.showApiResponseShowToMedia(response);

        show.setGenres(genreService.saveMediaGenresFromGenreNames(response.getGenres().values().stream().toList()));

        mediaRepository.save(show);

        professionalService.saveMediaProfessionals(response.getShowrunners(), show, JobForMedia.PRODUCER);

        mediaRepository.save(show);

        return show;
    }

    public void saveActors(ActorsApiResponse response, Media media) {
        List<PersonApiResponse> personsList = response.getCharacters();
        if (!personsList.isEmpty()) {
            professionalService.saveActors(personsList, media);
            mediaRepository.save(media);
        }
    }
}
