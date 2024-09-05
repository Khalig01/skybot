package skypro_bot.skybot.response;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Getter;
import skypro_bot.skybot.entity.CommandType;
public class Question extends Response {
    public Question(Long fromId, Long toId, String textMessage) {
        super(fromId, toId, textMessage);
    }

    //TODO: add some parameters like questionTime for more details


    @Override
    public SendMessage formatMessage() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("Answer Form").switchInlineQueryCurrentChat(
                        CommandType.ANSWER_QUESTION_MESSAGE.getCommand()
                                + this.fromId + "\n" +
                                "[Write answer for question]"
                )
        );
        SendMessage sendMessage = new SendMessage(this.toId, toStringMessageStructure())
                .replyMarkup(keyboardMarkup);

        return sendMessage;
    }

    @Override
    public String toStringMessageStructure() {
        return "Question from user:\n" +
                textMessage;
    }
}
