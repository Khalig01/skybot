package skypro_bot.skybot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import skypro_bot.skybot.error.ReportNotFoundException;
import skypro_bot.skybot.model.Photo;
import skypro_bot.skybot.model.Report;
import skypro_bot.skybot.model.User;
import skypro_bot.skybot.repository.PhotoRepository;
import skypro_bot.skybot.repository.ReportRepository;
import skypro_bot.skybot.repository.UserRepository;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public Report addReport(Report report) {
        return reportRepository.save(report);
    }
    public void uploadReport(Long userId, String text, MultipartFile reportFile) throws IOException {

        Optional<User> userOptional = userRepository.findByChatId(userId);

        if (userOptional.isEmpty()) {
            //TODO: UserNotFoundException
            throw new ReportNotFoundException();
        }
        User user = userOptional.get();


        Photo photo = new Photo();
        photo.setData(reportFile.getBytes());
        photo.setFileSize(reportFile.getSize());
        photo.setMediaType(reportFile.getContentType());

        photoRepository.save(photo);

        Report report = new Report();
        report.setUser(user);
        report.setText(text);
        report.setPhoto(photo);
        report.setNumber(reportRepository.getCountFromUser(user.getChatId()) + 1);

        reportRepository.save(report);
    }
    public Optional<Report> findReport(long id) {
        return reportRepository.findById(id);
    }
    // TO DO: find by user full name
    public void deleteReport(long id) {
        reportRepository.deleteById(id);
    }
    public Integer getReportCountFromUser(long userId) {
        return reportRepository.getCountFromUser(userId);
    }
    public List<Report> getAllReportsByOneUser(long userId) {
        return reportRepository.getAllFromUser(userId);
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
