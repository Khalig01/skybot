package skypro_bot.skybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import skypro_bot.skybot.model.Volunteer;

import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Optional<Volunteer> findByChatId(Long id);
    //TODO: something better then emul random Volunteer, temporary solution
    @Query(
            value = "SELECT * " +
                    "FROM volunteers " +
                    "WHERE id IS NOT NULL " +
                    "LIMIT 1",
            nativeQuery = true
    )
    Optional<Volunteer> findFirst();
    Optional<Volunteer> findFirstByIsBusy(boolean bool);
}
