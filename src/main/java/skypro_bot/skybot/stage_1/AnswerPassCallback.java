package skypro_bot.skybot.stage_1;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.entity.PetType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

import static skypro_bot.skybot.constants.Constants.*;

public class AnswerPassCallback implements CallbackChainHandler {

    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика волонтерства.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.CAR_PASS_CALLBACK.getCommand());
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
//TODO подтягивать текст

    private EditMessageText processAnimal(String petType, Long chatId,  Integer messageId) {

        if (petType.equals(PetType.CAT.getPet())) {
            EditMessageText editMessage = new EditMessageText(
                    chatId,
                    messageId,
                    TEXTCATPASS);
            return editMessage;

        } else if (petType.equals(PetType.DOG.getPet())) {
            EditMessageText editMessage = new EditMessageText(
                    chatId,
                    messageId,
                    TEXTDOGPASS);
            return editMessage;
        }
        return  null;
    }
}

