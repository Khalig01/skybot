package skypro_bot.skybot.stage_0;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

public class AnswerGetCallback implements CallbackChainHandler {


    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика волонтерства.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery  = update.callbackQuery();
        return callbackQuery  != null && callbackQuery.data().startsWith(CommandType.GET_CALLBACK.getCommand());
    }

    /**
     * Обрабатывает колбэк связанный с волонтерством и возвращает EditMessageText объект с обновленным текстом сообщения

     */
    @Override
    public EditMessageText handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Long chatId = callbackQuery.message().chat().id();
        Integer messageId = callbackQuery.message().messageId();

        EditMessageText editMessage = new EditMessageText(
                chatId,
                messageId,
                "Get"
        );

        return editMessage;
    }
}

