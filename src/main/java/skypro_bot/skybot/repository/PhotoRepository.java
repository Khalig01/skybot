package skypro_bot.skybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skypro_bot.skybot.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}

