package skypro_bot.skybot.stage_1;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.entity.PetType;

import skypro_bot.skybot.constants.Constants;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

import static skypro_bot.skybot.constants.Constants.TEXTCATSHELTER;
import static skypro_bot.skybot.constants.Constants.TEXTDOGSHELTER;

public class AnswerAboutShelterCallback implements CallbackChainHandler {

    private PetType petType;
    private Constants constants;

    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика волонтерства.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.ABOUT_SHELTER_CALLBACK.getCommand());
    }

    /**
     * Обрабатывает колбэк связанный с волонтерством и возвращает EditMessageText объект с обновленным текстом сообщения

     */
    @Override
    public EditMessageText handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Long chatId = callbackQuery.message().chat().id();
        Integer messageId = callbackQuery.message().messageId();
        String[] params = callbackQuery.data().split("_");

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("In previous menu").callbackData(CommandType.INFO_CALLBACK.getCommand()
                        + params[1])
        );

        return processAnimal(params[1], chatId, messageId).replyMarkup(keyboard);
    }
    private EditMessageText processAnimal(String petType, Long chatId, Integer messageId )  {

        if (petType.equals(PetType.CAT.getPet())) {
            EditMessageText editMessage = new EditMessageText(
                    chatId,
                    messageId,
                    TEXTCATSHELTER);
            return editMessage;

        } else if (petType.equals(PetType.DOG.getPet())) {
            EditMessageText editMessage = new EditMessageText(
                    chatId,
                    messageId,
                    TEXTDOGSHELTER);
            return editMessage;
        }
        return  null ;
    }
}
