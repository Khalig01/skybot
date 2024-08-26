package skypro_bot.skybot.stage_0;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.entity.PetType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

public class AnswerStartCallback implements CallbackChainHandler {
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.START_CALLBACK.getCommand());
    }

    @Override
    public EditMessageText handle(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Long chatId = callbackQuery.message().chat().id();
        Integer messageId = callbackQuery.message().messageId();

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Cats").callbackData(CommandType.SHELTER_CALLBACK.getCommand()
                        + PetType.CAT.getPet()),
                new InlineKeyboardButton("Dogs").callbackData(CommandType.SHELTER_CALLBACK.getCommand()
                        + PetType.DOG.getPet())
        );
        EditMessageText editMessageText = new EditMessageText(chatId, messageId, "Choose your option")
                .replyMarkup(inlineKeyboard);

        return editMessageText;
    }
}