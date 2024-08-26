package skypro_bot.skybot.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import skypro_bot.skybot.error.ReportNotFoundException;
import skypro_bot.skybot.error.UserNotFoundException;
import skypro_bot.skybot.error.VolunteerNotFoundException;

import java.io.IOException;

@RestControllerAdvice
public class RestException {
    private final static Logger LOGGER = LoggerFactory.getLogger(RestException.class);

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<Object> handleReportNotFoundException(ReportNotFoundException e) {
        LOGGER.error(e.toString());
        return new ResponseEntity<>("Report is not found.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException e) {
        LOGGER.error(e.toString());
        return new ResponseEntity<>("Report data is not found.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        LOGGER.error(e.toString());
        return new ResponseEntity<>("User is not found.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(VolunteerNotFoundException.class)
    public ResponseEntity<Object> handleVolunteerNotFoundException(VolunteerNotFoundException e) {
        LOGGER.error(e.toString());
        return new ResponseEntity<>("Volunteer is not found.", HttpStatus.NOT_FOUND);
    }
}
