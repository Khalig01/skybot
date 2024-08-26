package skypro_bot.skybot.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  skypro_bot.skybot.model.Shelter;
import skypro_bot.skybot.service.ShelterService;

import java.util.Optional;

/**
 * Контроллер для обработки HTTP-запросов, связанных с приютами.
 * Включает основные CRUD-запросы.
 */
@RestController
@RequestMapping("/shelters")
public class SheltersController {

    private final ShelterService shelterService;

    public SheltersController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    /**
     * Конструктор контроллера, который принимает сервис для работы с приютами.
     *
     * @param sheltersService Сервис для работы с приютами.
     */


    /**
     * Обрабатывает POST-запрос для добавления нового приюта.
     */
    @PostMapping("/add")
    public Shelter addShelter(@RequestBody Shelter shelter) {
        Shelter addShelter = shelterService.addShelter(shelter);
        return ResponseEntity.status(HttpStatus.CREATED).body(addShelter).getBody();
    }

    /**
     * Обрабатывает GET-запрос для поиска приюта  по идентификатору.
     */
    @GetMapping("{id}")
    public Shelter findShelterById(@PathVariable Long id) {
        Optional<Shelter> shelter = shelterService.findShelterById(id);

        if (shelter.isEmpty()) {
            return (Shelter) ResponseEntity.status(HttpStatus.NOT_FOUND).build().getBody();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(findShelterById(id)).getBody();
    }

    /**
     * Обрабатывает PUT-зарос для редактирования приюта.
     */
    @PutMapping("{id}")
    public Shelter editShelter(@RequestBody Shelter shelter, @PathVariable Long id) {
        Shelter foundShelter = shelterService.editShelter(shelter);

        if (foundShelter == null) {
            return (Shelter) ResponseEntity.status(HttpStatus.NOT_FOUND).build().getBody();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(findShelterById(id)).getBody();
    }

    /**
     * Обрабатывает DELETE-запрос для удаления приюта.
     */
    @DeleteMapping("{id}")
    public Shelter deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
        return (Shelter) ResponseEntity.status(HttpStatus.OK);
    }
}
