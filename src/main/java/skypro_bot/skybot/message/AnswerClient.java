package skypro_bot.skybot.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.MessageChainHandler;
import skypro_bot.skybot.response.Answer;
import skypro_bot.skybot.response.Response;
import skypro_bot.skybot.service.ChattingService;

import java.util.Arrays;

@AllArgsConstructor
public class AnswerClient implements MessageChainHandler {
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
        if (Arrays.stream(messageEntity).noneMatch(e-> e.type().equals(MessageEntity.Type.mention))) {
            return false;
        }

        return message.text().contains(CommandType.ANSWER_QUESTION_MESSAGE.getCommand());
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();

        MessageEntity commandEntity = Arrays.stream(message.entities())
                .filter(e -> e.type().equals(MessageEntity.Type.bot_command))
                .findFirst()
                .get();

        int offset = commandEntity.offset() + CommandType.ANSWER_QUESTION_MESSAGE.getCommand().length();

        String idParam = message.text().substring(offset, commandEntity.offset() + commandEntity.length());
        Long toId = Long.valueOf(idParam);
        String textReport = message.text().substring(commandEntity.offset() + commandEntity.length());


        SendMessage sendMessage = new SendMessage(chatId, "Answer is sent. Nice work.");

        Answer answer = new Answer(chatId, toId, textReport);

        telegramBot.execute(service.getSMFromAnswer(answer));

        return sendMessage;
    }
}