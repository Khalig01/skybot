package skypro_bot.skybot.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * Kоманды для обработки сообщений и обратных звонков.
 */
@AllArgsConstructor
@Getter
public enum CommandType {
    // CALLBACK
    START_CALLBACK("start_"), SHELTER_CALLBACK("shelter_"), INFO_CALLBACK("info_"),
    GET_CALLBACK("get_"), REPORT_CALLBACK("report_"), VOLUNTEER_CALLBACK("volunteer_"),
    SEND_CONTACT_CALLBACK("sendContact_"), ADDRESS_CALLBACK("address_"), CAR_PASS_CALLBACK("pass_"),
    ARRIVE_CALLBACK("arrive_"), SAFETY_GUIDE_CALLBACK("guide_"),
    WORKING_TIME_CALLBACK("workingTime_"), ABOUT_SHELTER_CALLBACK("about_"),

    // callback_2_level

    RULES_CALLBACK("Rules_"), DOCUMENTS_CALLBACK("documents_"),
    TRANSPORTING_CALLBACK("transporting_"), ARRANGE_FOR_ADULT("adultAnimal_"),
    ARRANGE_FOR_PUPPY("forPuppy_"), ARRANGE_FOR_KITTEN("forKitten_"),
    ARRANGE_FOR_DISABILITIES("disabilities_"),
    ADVICE_FROM_DOG_HANDLER("dogHandler_"), PROVEN_DOG_HANDLER("provenDogHandlers_"),
    REASON_FOR_REFUSAL("reasonsForRefusal_"),

    //MESSAGE
    START_MESSAGE("/start"), FORM_MESSAGE("/form_"),
    SEND_QUESTION_MESSAGE("/sendQ"), ANSWER_QUESTION_MESSAGE("/answerQ_");
    private final String command;


}
