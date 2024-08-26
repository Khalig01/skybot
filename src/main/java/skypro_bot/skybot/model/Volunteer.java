package skypro_bot.skybot.model;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Objects;

/**
 * Модель волонтера.
 */
@Entity(name = "volunteers")
public class Volunteer {

    @Id
    @GeneratedValue
    private Long id;

    private Long chatId;
    private String name;
    private Boolean isBusy;

    public Volunteer() {
    }

    /**
     * Конструктор класса Volunteer.
     *
     * @param name       Имя
     * @param id         id
     * @param chatId   id волонтера в ТГ

     */
    public Volunteer(Long id, Long chatId, String name) {
        this.id = id;
        this.chatId = chatId;
        this.name = name;
    }

    /**
     * Устанавливает идентификатор волонтера.
     *
     * @param id Идентификатор волонтера
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает идентификатор волонтера.
     *
     * @return Идентификатор волонтера
     */
    public Long getId() {
        return id;
    }

    /**
     * Возвращает идентификатор чата волонтера.
     *
     * @return Идентификатор чата волонтера
     */
    public Long getChatId() {
        return chatId;
    }

    /**
     * Устанавливает идентификатор чата волонтера.
     *
     * @param chatId Идентификатор чата волонтера
     */
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    /**
     * Возвращает имя волонтера.
     *
     * @return Имя волонтера
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя волонтера.
     *
     * @param name Имя волонтера
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(id, volunteer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Boolean getBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }

    /**
     * Возвращает строковое представление объекта Volunteer.
     *
     * @return Строковое представление объекта Volunteer.
     */

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", name='" + name + '\'' +
                '}';
    }
}
