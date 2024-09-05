package skypro_bot.skybot.stage_2;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

import static skypro_bot.skybot.constants.Constants.PROVENDOGHANDLERS;

/**
 * Обработчик для колбэков, связанных с рекомендациями по проверенным кинологам для дальнейшего обращения к ним.
 */
public class AnswerProvenDogHandlers implements CallbackChainHandler {

    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика связанного с рекомендациями по проверенным
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.PROVEN_DOG_HANDLER.getCommand());
    }

    /**
     * Обрабатывает колбэк, связанный с рекомендациями по проверенным кинологам для дальнейшего обращения к ним, и возвращает
     */
    @Override
    public EditMessageText handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Long chatId = callbackQuery.message().chat().id();
        Integer messageId = callbackQuery.message().messageId();
        String[] params = callbackQuery.data().split("_");

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("In previous menu").callbackData(CommandType.GET_CALLBACK.getCommand()
                        + params[1])
        );

        //TODO: из БД


        EditMessageText editMessage = new EditMessageText(chatId, messageId, PROVENDOGHANDLERS
        )
                .replyMarkup(keyboard);

        return editMessage;
    }
}

