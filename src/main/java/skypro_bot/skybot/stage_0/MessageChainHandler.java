package skypro_bot.skybot.stage_0;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface MessageChainHandler {
    boolean check(Update update);
    SendMessage handle(Update update);
}
