package com.example.filrouge_back.services;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.repositories.MediaRepository;
import com.example.filrouge_back.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class MediaCrudService implements CrudService<Media> {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaCrudService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public Media create(Media entity) {
        return mediaRepository.save(entity);
    }

    @Override
    public Media read(UUID id) {
        return mediaRepository.findById(id).orElse(null);
    }

    public Media update(UUID id, Media updatedEntity) {
        Media existingMedia = mediaRepository.findById(id).orElse(null);

        if (existingMedia == null) {
            return null;
        }


        if (updatedEntity.getTitle() != null) {
            existingMedia.setTitle(updatedEntity.getTitle());
        }
        if (updatedEntity.getPlot() != null) {
            existingMedia.setPlot(updatedEntity.getPlot());
        }
        if (updatedEntity.getImageUrl() != null) {
            existingMedia.setImageUrl(updatedEntity.getImageUrl());
        }

        return mediaRepository.save(existingMedia);
    }


    @Override
    public void delete(UUID id) {
        mediaRepository.deleteById(id);
    }

    @Override
    public List<Media> getAll() {
        return mediaRepository.findAll();
    }
}
