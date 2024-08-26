package skypro_bot.skybot.message;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.entity.PetType;
import skypro_bot.skybot.stage_0.MessageChainHandler;

/**
 * Обработчик для сообщений, для команды /start.

 */
public class StartMessage implements MessageChainHandler {
    /**
     * Проверяет,подходит ли сообщение команде /start.
     */
    @Override
    public boolean check(Update update) {
        Message message = update.message();
        return message != null && message.text() != null && message.text().contains(CommandType.START_MESSAGE.getCommand());
    }

    /**
     * Обрабатывает сообщение с командой /start и возвращает сообщение с клавиатурой выбора приюта.

     */
    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Cats").callbackData(CommandType.SHELTER_CALLBACK.getCommand()
                        + PetType.CAT.getPet()),
                new InlineKeyboardButton("Dogs").callbackData(CommandType.SHELTER_CALLBACK.getCommand()
                        + PetType.DOG.getPet())
        );
        SendMessage sendMessage = new SendMessage(chatId, "Hello, choose your option")
                .replyMarkup(inlineKeyboard);

        return sendMessage;
    }
}