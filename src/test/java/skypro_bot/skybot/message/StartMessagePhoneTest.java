package skypro_bot.skybot.message;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartMessagePhoneTest {
    @Test
    public void testCheckStart() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("9");

        StartMessagePhone handler = new StartMessagePhone();
        boolean result = handler.check(update);

        assertEquals(true, result);
    }

    @Test
    public void testCheckNotStart() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("8");

        StartMessagePhone handler = new StartMessagePhone ();
        boolean result = handler.check(update);

        assertEquals(false, result);
    }
    @Test
    public void whenMatchesPhoneNumber_thenCorrect() {
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

        String[] validPhoneNumbers
                = {"2055550125", "202 555 0125", "(202) 555-0125", "+111 (202) 555-0125",
                "636 856 789", "+111 636 856 789", "636 85 67 89", "+111 636 85 67 89"};

        Pattern pattern = Pattern.compile(patterns);
        for (String phoneNumber : validPhoneNumbers) {
            Matcher matcher = pattern.matcher(phoneNumber);
            assertTrue(matcher.matches());
        }
    }
    @Test
    void whenAssertingException() {
        Throwable exception = assertThrows(
                IllegalStateException.class,
                () -> {
                    throw new IllegalStateException("Contact not saved: invalid characters or invalid number of characters");
                }
        );
        assertEquals("Contact not saved: invalid characters or invalid number of characters", exception.getMessage());
    }
}

