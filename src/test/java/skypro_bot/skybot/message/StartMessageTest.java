package skypro_bot.skybot.message;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartMessageTest {

    @Test
    public void testCheckStart() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("/start");

        StartMessage handler = new StartMessage();
        boolean result = handler.check(update);

        assertEquals(true, result);
    }

    @Test
    public void testCheckNotStart() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("Hello");

        StartMessage handler = new StartMessage();
        boolean result = handler.check(update);

        assertEquals(false, result);
    }
}
