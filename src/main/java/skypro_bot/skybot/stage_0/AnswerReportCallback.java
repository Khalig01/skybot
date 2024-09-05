package skypro_bot.skybot.stage_0;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

public class AnswerReportCallback implements CallbackChainHandler {

    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика волонтерства.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery  = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.REPORT_CALLBACK.getCommand());
    }

    /**
     * Обрабатывает колбэк связанный с волонтерством и возвращает EditMessageText объект с обновленным текстом сообщения
     */
    @Override
    public EditMessageText handle(Update update) {
        CallbackQuery callbackQuery  = update.callbackQuery();
        Long chatId = callbackQuery.message().chat().id();
        Integer messageId = callbackQuery.message().messageId();
        String petParam = callbackQuery.data().substring(CommandType.REPORT_CALLBACK.getCommand().length());

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Form").switchInlineQueryCurrentChat(CommandType.FORM_MESSAGE.getCommand()
                        + petParam
                        + "\n"
                        + "[Enter text and upload with this message one photo]")
        );
        keyboard.addRow(
                new InlineKeyboardButton("Back").callbackData(CommandType.START_CALLBACK.getCommand())
        );
        EditMessageText editMessage = new EditMessageText(
                chatId,
                messageId,
                "Report..."
        ).replyMarkup(keyboard);

        return editMessage;
    }
}
