package skypro_bot.skybot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skypro_bot.skybot.error.ReportNotFoundException;
import skypro_bot.skybot.model.Photo;
import skypro_bot.skybot.model.Report;
import skypro_bot.skybot.service.ReportService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

 /* Контроллер для обработки HTTP-запросов, связанных с отчётами
 * Включает основные CRUD-запросы
 */
@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;

    /**
     * Обрабатывает GET-запрос для поиска отчёта по идентификатору
     */
    @GetMapping("/{id}")
    public ResponseEntity<Report> findReport(@PathVariable Long id) {
        Optional<Report> reportOptional = reportService.findReport(id);

        if (reportOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(reportOptional.get());
    }
    /**
     * Обрабатывает POST-запрос для добавления нового питомца
     */
    @PostMapping(value = "/{userId}/data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addReport(@PathVariable Long userId, @RequestParam String text, @RequestParam MultipartFile photo)
            throws IOException {
        reportService.uploadReport(userId, text, photo);
        return ResponseEntity.ok().build();
    }
    /**
     * Обрабатывает GET-запрос для выгрузки отчёта по идентификатору
     */
    @GetMapping(value = "/{id}/photo")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Optional<Report> reportOptional = reportService.findReport(id);
        if (reportOptional.isEmpty()) {
            throw new ReportNotFoundException();
        }

        Report report = reportOptional.get();
        Photo photo = report.getPhoto();
        if (photo.getFileSize() == 0) {
            throw new ReportNotFoundException();
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(photo.getMediaType()))
                .body(photo.getData());
    }
    /**
     * Обрабатывает GET-запрос для получения всех отчётов по идентификатору пользователя
     */
    @GetMapping("/{userId}/all-from-user")
    public ResponseEntity<List<Report>> getAllReportsFromOneUser(@PathVariable Long userId) {
        List<Report> reportList = reportService.getAllReportsByOneUser(userId);
        return ResponseEntity.ok().body(reportList);
    }
    /**
     * Обрабатывает GET-запрос для получения количества отчётов переданных пользователем ранее
     */
    @GetMapping("/{userId}/count-from-user")
    public ResponseEntity<Integer> getReportCountFromOneUser(@PathVariable Long userId) {
        Integer countFromUser = reportService.getReportCountFromUser(userId);
        return ResponseEntity.ok().body(countFromUser);
    }

}
