package skypro_bot.skybot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro_bot.skybot.stage_0.CallbackChainHandler;
import skypro_bot.skybot.stage_0.MessageChainHandler;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Слушатель обновлений Telegram бота.
 * Обрабатывает полученные обновления, передавая их соответствующим обработчикам цепочки сообщений и колбэков.
 */
@Service
public class TelegramBotListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotListener.class);
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private List<MessageChainHandler> messageChainHandlers;
    @Autowired
    private List<CallbackChainHandler> callbackChainHandlers;

    /**
     * Инициализирует слушателя обновлений Telegram бота.
     * Устанавливает этот объект в качестве слушателя для Telegram бота после завершения создания службы.
     */
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Обрабатывает полученные обновления Telegram бота.
     * Передает каждое обновление соответствующим обработчикам цепочки сообщений и колбэков для дальнейшей обработки.
     *
     * @param updates Список обновлений от Telegram API
     * @return число, определяющее, какие обновления Telegram API были успешно обработаны
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            messageChainHandlers.stream()
                    .filter(h -> h.check(update))
                    .forEach(h -> {
                        SendMessage message = h.handle(update);
                        telegramBot.execute(message);
                    });
            callbackChainHandlers.stream()
                    .filter(h -> h.check(update))
                    .forEach(h -> {
                        EditMessageText message = h.handle(update);
                        telegramBot.execute(message);
                    });


        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
