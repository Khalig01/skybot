package skypro_bot.skybot.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * Модель пользователя.
 */
@Entity(name="users")
@Getter
@Setter

public class User {

    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Long id;
    private Long chatId;

    private String name;
    private String phoneNumber;

    public User() {
    }
}
