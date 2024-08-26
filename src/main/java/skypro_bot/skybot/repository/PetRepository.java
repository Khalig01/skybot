package skypro_bot.skybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skypro_bot.skybot.model.Pet;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByPetType(String type);
}
