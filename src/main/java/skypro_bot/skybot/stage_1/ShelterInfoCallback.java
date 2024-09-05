package skypro_bot.skybot.stage_1;


import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

/**
 * Обработчик для обратных звонков связанных с приютами.
 */
public class ShelterInfoCallback implements CallbackChainHandler {

    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика приютов.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.INFO_CALLBACK.getCommand());
    }

    /**
     * Обрабатывает колбэк связанный с приютами и возвращает EditMessageText объект с обновленным текстом сообщения или
     */
    @Override
    public EditMessageText handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        String[] params = callbackQuery.data().split("_");
        Long chatId = callbackQuery.message().chat().id();
        Integer messageId = callbackQuery.message().messageId();
        InlineKeyboardMarkup inlineKeyboard =
                new InlineKeyboardMarkup(
                        new InlineKeyboardButton("Our shelter").callbackData(CommandType.ABOUT_SHELTER_CALLBACK.getCommand()
                                + params[1]),
                        new InlineKeyboardButton("Working time").callbackData(CommandType.WORKING_TIME_CALLBACK.getCommand()
                                + params[1])
                );
        inlineKeyboard.addRow(
                new InlineKeyboardButton("Our address").callbackData(CommandType.ADDRESS_CALLBACK.getCommand()
                        + params[1]),
                new InlineKeyboardButton("How to arrive").callbackData(CommandType.ARRIVE_CALLBACK.getCommand()
                        + params[1])

        );
        inlineKeyboard.addRow(
                new InlineKeyboardButton("Car pass").callbackData(CommandType.CAR_PASS_CALLBACK.getCommand()
                        + params[1]),
                new InlineKeyboardButton("Safety guide").callbackData(CommandType.SAFETY_GUIDE_CALLBACK.getCommand()
                        + params[1])

        );
        inlineKeyboard.addRow(
                new InlineKeyboardButton("Send contact").callbackData(CommandType.SEND_CONTACT_CALLBACK.getCommand()
                        + params[1]),
                new InlineKeyboardButton("Call volunteer").callbackData(CommandType.VOLUNTEER_CALLBACK.getCommand()
                        + callbackQuery.data())
        );

        inlineKeyboard.addRow(
                new InlineKeyboardButton("Back").callbackData(CommandType.START_CALLBACK.getCommand())
        );

        EditMessageText editMessage = new EditMessageText(chatId, messageId, "Updated info shelter for " + params[1])
                .replyMarkup(inlineKeyboard);
        return editMessage;

    }
}