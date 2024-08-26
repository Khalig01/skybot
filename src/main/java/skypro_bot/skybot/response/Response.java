package skypro_bot.skybot.response;

import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Response {
    protected Long fromId;
    protected Long toId;
    protected String textMessage;

    public abstract SendMessage formatMessage();

    public abstract String toStringMessageStructure();
}
