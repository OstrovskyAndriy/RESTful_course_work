package com.coursework.services.serviceImpl;

import com.coursework.models.Photo;
import com.coursework.repository.PhotoRepository;
import com.coursework.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Cacheable(value = "photosCache")
    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @Cacheable(value = "photosCache", key = "#institutionId")
    public List<Photo> getPhotosByInstitution(Long institutionId) {
        return photoRepository.findByInstitutionId(institutionId);
    }

    @CachePut(value = "photosCache", key = "#result.id")
    @Override
    public Photo updatePhoto(Photo photo, Long id) {
        if (photoRepository.existsById(id)) {
            photo.setId(id);
            return photoRepository.save(photo);
        }
        return null;
    }

    @CachePut(value = "photosCache", key = "#result.id")
    @Override
    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Cacheable(value = "photosCache", key = "#id")
    @Override
    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "photosCache", key = "#id")
    @Override
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}

