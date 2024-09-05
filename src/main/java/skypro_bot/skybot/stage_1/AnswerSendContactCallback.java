package skypro_bot.skybot.stage_1;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

public class AnswerSendContactCallback implements CallbackChainHandler {
    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика волонтерства.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.SEND_CONTACT_CALLBACK.getCommand());
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
                "Please, write your contact details for communication (enter your contact-phone starting from 9) " +
                        "and we will definitely call you back!"

        );
        String[] params = callbackQuery.data().split("_");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("In previous menu").callbackData(CommandType.INFO_CALLBACK.getCommand()
                        + params[1])
        );
        return editMessage.replyMarkup(keyboard);
    }
}