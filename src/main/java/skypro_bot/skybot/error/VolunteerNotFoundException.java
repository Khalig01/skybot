package skypro_bot.skybot.error;

public class VolunteerNotFoundException extends RuntimeException{
    public VolunteerNotFoundException() {
    }

    public VolunteerNotFoundException(String message) {
        super(message);
    }
}
