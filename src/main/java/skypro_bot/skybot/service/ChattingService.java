package skypro_bot.skybot.service;

import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import skypro_bot.skybot.model.Volunteer;
import skypro_bot.skybot.response.Answer;
import skypro_bot.skybot.response.Question;
import skypro_bot.skybot.response.Response;

import java.util.Optional;
@Service
@AllArgsConstructor
public class ChattingService {
    private final VolunteerService volunteerService;

    public SendMessage getSMFromQuestion(Question response) {
        volunteerService.keepVolunteerBusy(response.getToId(), true);
        return response.formatMessage();
    }
    public SendMessage getSMFromAnswer(Answer response) {
        volunteerService.keepVolunteerBusy(response.getFromId(), false);
        return response.formatMessage();
    }

    public Volunteer getFreeVolunteer() {
        Optional<Volunteer> optionalVolunteer = volunteerService.findFreeVolunteer();
        return optionalVolunteer.orElse(null);

    }
}
