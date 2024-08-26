package skypro_bot.skybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import skypro_bot.skybot.model.Report;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(
            value = "SELECT count(*) " +
                    "FROM reports " +
                    "INNER JOIN users ON users.id = reports.user_id " +
                    "WHERE users.chat_id = :id",
            nativeQuery = true
    )
    Integer getCountFromUser(@Param("id") Long chatId);
    @Query(
            value = "SELECT * " +
                    "FROM reports " +
                    "INNER JOIN users ON users.id = reports.user_id " +
                    "WHERE users.chat_id = :id",
            nativeQuery = true
    )
    List<Report> getAllFromUser(@Param("id") Long chatId);
}
