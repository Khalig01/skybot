package skypro_bot.skybot.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.MessageChainHandler;
import skypro_bot.skybot.response.Question;
import skypro_bot.skybot.model.Volunteer;
import skypro_bot.skybot.response.Response;
import skypro_bot.skybot.service.ChattingService;

import java.util.Arrays;

@AllArgsConstructor
public class CallVolunteer implements MessageChainHandler {
    private final ChattingService service;
    private final TelegramBot telegramBot;
    @Override
    public boolean check(Update update) {
        Message message = update.message();

        if (message == null || message.text() == null) {
            return false;
        }
        MessageEntity[] messageEntity = message.entities();
        if (messageEntity == null) {
            return false;
        }
        if (Arrays.stream(messageEntity).noneMatch(e -> e.type().equals(MessageEntity.Type.mention))) {
            return false;
        }

        return message.text().contains(CommandType.SEND_QUESTION_MESSAGE.getCommand());

    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();

        MessageEntity commandEntity = Arrays.stream(message.entities())
                .filter(e -> e.type().equals(MessageEntity.Type.bot_command))
                .findFirst()
                .get();

        Long userId = message.chat().id();
        String textReport = message.text().substring(commandEntity.offset() + commandEntity.length());


        // TODO: queue of questions and do uniform distribution for volunteers
        Volunteer volunteer = service.getFreeVolunteer();

        if (volunteer == null) {
            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(
                    new InlineKeyboardButton("Back").callbackData(CommandType.START_CALLBACK.getCommand())
            );
            return new SendMessage(chatId, "Call volunteers doesn't available now. Please try again later.").replyMarkup(keyboardMarkup);
        }

        Question question = new Question(chatId, volunteer.getChatId(), textReport);

        telegramBot.execute(service.getSMFromQuestion(question));

        return new SendMessage(chatId, "Volunteer is called. Please wait his response.");
    }

}