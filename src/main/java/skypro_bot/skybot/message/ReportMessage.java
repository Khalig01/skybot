package skypro_bot.skybot.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import skypro_bot.skybot.entity.CommandType;
import skypro_bot.skybot.stage_0.MessageChainHandler;
import skypro_bot.skybot.model.Photo;
import skypro_bot.skybot.model.Report;
import skypro_bot.skybot.model.User;
import skypro_bot.skybot.service.PhotoService;
import skypro_bot.skybot.service.ReportService;
import skypro_bot.skybot.service.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
/**
 * Обработчик для сообщений, содержащих команду <i>bot-mention</i> /form.
 * Возвращает результат отправки отчёта.
 */

@AllArgsConstructor
public class ReportMessage implements MessageChainHandler {
    private final UserService userService;
    private final PhotoService photoService;
    private final ReportService reportService;
    private final TelegramBot telegramBot;


    /**
     * Проверяет, соответствует ли сообщение команде
     */
    @Override
    public boolean check(Update update) {
        Message message = update.message();
        if (message == null || message.caption() == null) {
            return false;
        }
        MessageEntity[] messageEntity = message.captionEntities();
        if (messageEntity == null) {
            return false;
        }
        if (Arrays.stream(messageEntity).noneMatch(e-> e.type().equals(MessageEntity.Type.mention))) {
            return false;
        }
        return message.caption().contains(CommandType.FORM_MESSAGE.getCommand());
    }
    /**
     * Обрабатывает сообщение с команды

     */
    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();

        MessageEntity commandEntity = Arrays.stream(message.captionEntities())
                .filter(e -> e.type().equals(MessageEntity.Type.bot_command))
                .findFirst()
                .get();

        int offset = commandEntity.offset() + CommandType.FORM_MESSAGE.getCommand().length();

        String petParam = message.caption().substring(offset, commandEntity.offset() + commandEntity.length());
        String textReport = message.caption().substring(commandEntity.offset() + commandEntity.length());

        Optional<User> userOptional = userService.findUserByChatId(chatId);

        if (userOptional.isEmpty()) {
            return new SendMessage(chatId, "You are not allowed to send reports.");
        }

        User user = userOptional.get();

        PhotoSize[] photoSize = update.message().photo();
        Photo photo = null;
        try {
            photo = downloadPhoto(photoSize);
            photoService.addPhoto(photo);
        } catch (IOException e) {
            return new SendMessage(chatId, "Error downloading photo. Some went wrong.");
        }

        Report report = new Report();
        report.setUser(user);
        report.setPhoto(photo);
        report.setNumber(reportService.getReportCountFromUser(user.getChatId())+1);
        report.setPetType(petParam);
        report.setText(textReport);

        reportService.addReport(report);

        return new SendMessage(chatId, "Thanks for sending reports");
    }
    /**
     * Вспомогательный метод
     */
    private Photo downloadPhoto(PhotoSize[] photoSize) throws IOException {
        GetFile getFile = new GetFile(photoSize[photoSize.length - 1].fileId());
        GetFileResponse response = telegramBot.execute(getFile);
        File file = response.file();

        String urlPath = telegramBot.getFullFilePath(file);

        WebClient webClient = WebClient.create(urlPath);
        MediaType mediaType = MediaType.IMAGE_JPEG;

        byte[] bytes = webClient
                .get()
                .accept(mediaType)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        Photo photo = new Photo();
        photo.setFileSize(file.fileSize());
        photo.setData(bytes);
        photo.setMediaType(mediaType.toString());

        return photo;
    }

}

