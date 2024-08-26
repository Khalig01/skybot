package skypro_bot.skybot.stage_2;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.entity.PetType;
import skypro_bot.skybot.stage_0.CallbackChainHandler;

/**
 * Обработчик для колбэков связанных с приютами.
 */
public class ShelterGetPetCallback implements CallbackChainHandler {

    /**
     * Проверяет, соответствует ли колбэк необходимым условиям обработчика приютов.
     */
    @Override
    public boolean check(Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        return callbackQuery != null && callbackQuery.data().startsWith(CommandType.GET_CALLBACK.getCommand());
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
                        new InlineKeyboardButton("Rules")
                                .callbackData(CommandType.RULES_CALLBACK.getCommand()
                                        + params[1]),
                        new InlineKeyboardButton("Documents for adopt")
                                .callbackData(CommandType.DOCUMENTS_CALLBACK.getCommand()
                                        + params[1])


                );

        inlineKeyboard.addRow(
                new InlineKeyboardButton("Transporting")
                        .callbackData(CommandType.TRANSPORTING_CALLBACK.getCommand()
                                + params[1]),
                new InlineKeyboardButton("Arrange for an \n" + "adult animal")
                        .callbackData(CommandType.ARRANGE_FOR_ADULT.getCommand()
                                + params[1])

        );

        if (params[1].equalsIgnoreCase(PetType.DOG.getPet())) {
            inlineKeyboard.addRow(
                    new InlineKeyboardButton("Arrange for puppy")
                            .callbackData(CommandType.ARRANGE_FOR_PUPPY.getCommand()
                                    + params[1])
            );
        }

        if (params[1].equalsIgnoreCase(PetType.CAT.getPet())) {
            inlineKeyboard.addRow(
                    new InlineKeyboardButton("Arrange for kitten")
                            .callbackData(CommandType.ARRANGE_FOR_KITTEN.getCommand()
                                    + params[1])
            );
        }

        if (params[1].equalsIgnoreCase(PetType.DOG.getPet())) {
            inlineKeyboard.addRow(
                    new InlineKeyboardButton("Proven dog handlers").callbackData(CommandType
                            .PROVEN_DOG_HANDLER.getCommand()
                            + params[1]),
                    new InlineKeyboardButton("Advice from a dog handler").callbackData(CommandType
                            .ADVICE_FROM_DOG_HANDLER.getCommand()
                            + params[1])
            );
        }

        inlineKeyboard.addRow(
                new InlineKeyboardButton("For an animal with disabilities")
                        .callbackData(CommandType.ARRANGE_FOR_DISABILITIES.getCommand()
                                + params[1]),
                new InlineKeyboardButton("Reasons for refusal").callbackData(CommandType
                        .REASON_FOR_REFUSAL.getCommand()
                        + params[1])
        );

        inlineKeyboard.addRow(
                new InlineKeyboardButton("Send contact").callbackData(CommandType
                        .SEND_CONTACT_CALLBACK.getCommand()
                        + params[1]),
                new InlineKeyboardButton("Call volunteer").callbackData(CommandType
                        .VOLUNTEER_CALLBACK.getCommand()
                        + callbackQuery.data())
        );

        inlineKeyboard.addRow(
                new InlineKeyboardButton("Back")
                        .callbackData(CommandType.START_CALLBACK.getCommand())
        );

        EditMessageText editMessage = new EditMessageText(chatId, messageId, "Updated info shelter for " + params[1])
                .replyMarkup(inlineKeyboard);
        return editMessage;
    }
}
