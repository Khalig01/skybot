package skypro_bot.skybot.stage_2;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

import static skypro_bot.skybot.constants.Constants.RECOMMENDATIONS;

/**
 * Обработчик для колбэков, связанных с транспортировкой животных.
 */
public class AnswerRecommendationsForTransportingAnimal implements CallbackChainHandler {

    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика для рекомендаций по транспортировке животных.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.TRANSPORTING_CALLBACK.getCommand());
    }

    /**
     * Обрабатывает колбэк, связанный с рекомендациями по транспортировке животных, и возвращает
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




        EditMessageText editMessage = new EditMessageText(chatId, messageId, RECOMMENDATIONS)
                .replyMarkup(keyboard);

        return editMessage;
    }
}

