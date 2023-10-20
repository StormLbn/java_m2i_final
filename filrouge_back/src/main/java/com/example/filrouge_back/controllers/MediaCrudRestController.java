package com.example.filrouge_back.controllers;

import com.example.filrouge_back.entities.Media;
import com.example.filrouge_back.services.MediaCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/crud/media/")
public class MediaCrudRestController {

    private final MediaCrudService mediaCrudService;

    @Autowired
    public MediaCrudRestController(MediaCrudService mediaCrudService) {
        this.mediaCrudService = mediaCrudService;
    }

    @PostMapping("/create")
    public Media createMedia(@RequestBody Media media) {
        return mediaCrudService.create(media);
    }

    @GetMapping("/{mediaId}")
    public Media getMedia(@PathVariable UUID mediaId) {
        return mediaCrudService.read(mediaId);
    }

    @PatchMapping("/update/{mediaId}")
    public Media updateMedia(@PathVariable UUID mediaId, @RequestBody Media updatedMedia) {
        Media update = mediaCrudService.update(mediaId, updatedMedia);
        return update;
    }

    @DeleteMapping("/delete/{mediaId}")
    public void deleteMedia(@PathVariable UUID mediaId) {
        mediaCrudService.delete(mediaId);
    }

    @GetMapping("/all")
    public List<Media> getAllMedia() {
        return mediaCrudService.getAll();
    }
}
