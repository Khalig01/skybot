package skypro_bot.skybot.stage_0;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

/**
 * Обработчик для обратных звонков связанных с волонтерством.
 */
public class AnswerVolunteerCallback implements CallbackChainHandler {

    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика волонтерства.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.VOLUNTEER_CALLBACK.getCommand());
    }

    /**
     * Обрабатывает колбэк связанный с волонтерством и возвращает EditMessageText объект с обновленным текстом сообщения
     */
    @Override
    public EditMessageText handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Long chatId = callbackQuery.message().chat().id();
        Integer messageId = callbackQuery.message().messageId();

        String backParam = callbackQuery.data().substring(CommandType.VOLUNTEER_CALLBACK.getCommand().length());

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Form").switchInlineQueryCurrentChat(CommandType.SEND_QUESTION_MESSAGE.getCommand()
                        + "\n"
                        + "[Insert text message here]")
        );
        keyboard.addRow(new InlineKeyboardButton("In previous menu").callbackData(backParam));

        EditMessageText editMessage = new EditMessageText(
                chatId,
                messageId,
                "Write text message in form. Please dont delete commands. You can send only text messages."
        ).replyMarkup(keyboard);

        return editMessage;
    }
}
