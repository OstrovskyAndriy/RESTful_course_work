package com.coursework.services.serviceImpl;

import com.coursework.models.Photo;
import com.coursework.repository.PhotoRepository;
import com.coursework.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }





    public List<Photo> getPhotosByInstitution(Long institutionId) {
        return photoRepository.findByInstitutionId(institutionId);
    }

    @Override
    public Photo updatePhoto(Photo photo, Long id) {
        if (photoRepository.existsById(id)) {
            photo.setId(id);
            return photoRepository.save(photo);
        }
        return null;
    }

    @Override
    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
