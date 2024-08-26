package skypro_bot.skybot.conficuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import skypro_bot.skybot.message.*;
import skypro_bot.skybot.stage_0.ShelterCallback;
import skypro_bot.skybot.stage_0.AnswerGetCallback;
import skypro_bot.skybot.stage_0.AnswerReportCallback;
import skypro_bot.skybot.stage_0.AnswerStartCallback;
import skypro_bot.skybot.stage_0.AnswerVolunteerCallback;
import skypro_bot.skybot.stage_0.CallbackChainHandler;
import skypro_bot.skybot.stage_0.*;
import skypro_bot.skybot.stage_1.*;
import skypro_bot.skybot.stage_0.MessageChainHandler;

import skypro_bot.skybot.stage_2.*;


import skypro_bot.skybot.service.*;


import java.util.List;

@Configuration
public class TelegramBotConfiguration {

    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private ChattingService chattingService;

    @Value("${telegram.bot.token}")
    private String token;


    /**
     * Создает и настраивает экземпляр Telegram бота.

     */
    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }

    /**
     * Создает список обработчиков цепочки сообщений.
     */
    @Bean
    public List<MessageChainHandler> messageChainHandlers() {
        return List.of(
                new StartMessage(),
                new StartMessagePhone(),
                new ReportMessage(userService, photoService, reportService, telegramBot()),
                new CallVolunteer(chattingService, telegramBot()),
                new AnswerClient(chattingService, telegramBot())

        );
    }

    /**
     * Создает список обработчиков цепочки обратных звонков.
     */
    @Bean
    public List<CallbackChainHandler> callbackChainHandlers() {
        return List.of(
                new ShelterCallback(),

                new AnswerVolunteerCallback(),
                new AnswerReportCallback(),
                new AnswerGetCallback(),
                new AnswerStartCallback(),

                new ShelterInfoCallback(),

                new AnswerAddressCallback(),
                new AnswerWorkingTimeCallback(),
                new AnswerAddressCallback(),
                new AnswerArriveCallback(),
                new AnswerPassCallback(),
                new AnswerSafetyGuideCallback(),
                new AnswerSendContactCallback(),

                new AnswerAboutShelterCallback(),

                new ShelterGetPetCallback(),

                new AnswerRulesForMeetingAnimal(),
                new AnswerDocumentsForAdoptAnimal(),
                new AnswerRecommendationsForTransportingAnimal(),
                new AnswerArrangeForAnAdultAnimal(),
                new AnswerArrangeForPuppy(),
                new AnswerArrangeForKitten(),
                new AnswerProvenDogHandlers(),
                new AnswerAdviceFromDog(),
                new AnswerArrangeForAnAnimalWithDisabilities(),
                new AnswerReasonForRefusal()
        );
    }
}
