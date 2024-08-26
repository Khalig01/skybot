package skypro_bot.skybot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Типы питомцев.
 */
@AllArgsConstructor
@Getter
public enum PetType {
    CAT("cat"), DOG("dog");

    private final String pet;
}
