package skypro_bot.skybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skypro_bot.skybot.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByChatId(Long chatId);
}