package skypro_bot.skybot.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import skypro_bot.skybot.model.Photo;
import skypro_bot.skybot.repository.PhotoRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }
    public Photo updatePhoto(Photo photo) {
        return photoRepository.save(photo);
    }
    public Optional<Photo> findPhoto(Long id) {
        return photoRepository.findById(id);
    }
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
