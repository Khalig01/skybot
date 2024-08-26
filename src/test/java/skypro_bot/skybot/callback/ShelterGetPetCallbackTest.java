package skypro_bot.skybot.callback;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import org.testng.annotations.Test;
import org.mockito.Mockito;
import skypro_bot.skybot.stage_2.ShelterGetPetCallback;
import skypro_bot.skybot.stage_2.ShelterGetPetCallback;

import static org.junit.Assert.*;

public class ShelterGetPetCallbackTest  {

    @Test
    public void testCheckMethod() {

        ShelterGetPetCallback handler = new ShelterGetPetCallback();
        Update update = Mockito.mock(Update.class);
        CallbackQuery callbackQuery = Mockito.mock(CallbackQuery.class);
        Mockito.when(callbackQuery.data()).thenReturn("get_124");
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);

        assertTrue(handler.check(update));

        Mockito.when(callbackQuery.data()).thenReturn("other_550");
        assertFalse(handler.check(update));
    }

    @Test
    public void testHandleMethod() {

        ShelterGetPetCallback handler = new ShelterGetPetCallback();
        Update update = Mockito.mock(Update.class);
        CallbackQuery callbackQuery = Mockito.mock(CallbackQuery.class);
        Mockito.when(update.callbackQuery()).thenReturn(callbackQuery);
        Mockito.when(callbackQuery.data()).thenReturn("get_124");

        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);
        Mockito.when(callbackQuery.message()).thenReturn(message);
        Mockito.when(message.chat()).thenReturn(chat);
        Mockito.when(chat.id()).thenReturn(550L);
        Mockito.when(message.messageId()).thenReturn(989);

        EditMessageText result = handler.handle(update);
        assertNotNull(result);
        assertEquals(550L, chat.id().longValue());
        assertEquals(989, message.messageId().intValue());
        assertNotNull(result.replyMarkup(new InlineKeyboardMarkup()));
    }
}

