package skypro_bot.skybot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro_bot.skybot.model.Pet;
import skypro_bot.skybot.model.User;
import skypro_bot.skybot.service.PetService;
import skypro_bot.skybot.service.UserService;

import java.util.Optional;

/**
 * Контроллер для обработки HTTP-запросов, связанных с пользователем.
 * Включает основные CRUD-запросы.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Конструктор контроллера, который принимает сервис для работы с пользователем.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обрабатывает POST-запрос для добавления нового пользователя.
     */
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        User addUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(addUser).getBody();
    }

    /**
     * Обрабатывает GET-запрос для поиска пользователя по идентификатору.
     */
    @GetMapping("{id}")
    public User findUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);

        if (user.isEmpty()) {
            return (User) ResponseEntity.status(HttpStatus.NOT_FOUND).build().getBody();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(findUserById(id)).getBody();
    }

    /**
     * Обрабатывает PUT-зарос для редактирования пользователя.
     */
    @PutMapping("{id}")
    public User editUser(@RequestBody User user, @PathVariable Long id) {
        User foundUser = userService.editUser(user);

        if (foundUser == null) {
            return (User) ResponseEntity.status(HttpStatus.NOT_FOUND).build().getBody();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(findUserById(id)).getBody();
    }

    /**
     * Обрабатывает DELETE-запрос для удаления пользователя.
     */
    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return (User) ResponseEntity.status(HttpStatus.OK);
    }

}
