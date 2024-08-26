package skypro_bot.skybot.stage_0;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.EditMessageText;

public interface CallbackChainHandler {
    boolean check(Update update);
    EditMessageText handle(Update update);
}
