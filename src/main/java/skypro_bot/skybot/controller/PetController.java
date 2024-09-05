package skypro_bot.skybot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skypro_bot.skybot.model.Pet;
import skypro_bot.skybot.service.PetService;

import java.util.Optional;
/**
        * Контроллер для обработки HTTP-запросов, связанных с питомцами.
        */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;


    /**
     * Конструктор контроллера, который принимает сервис для работы с питомцами.
     */

    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Обрабатывает POST-запрос для добавления нового питомца.
     */
    @PostMapping("/add")
    public Pet addPet(@RequestBody Pet pet) {
        Pet addPet = petService.addPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(addPet).getBody();
    }

    /**
     * Обрабатывает GET-запрос для поиска питомца по идентификатору.
     */
    @GetMapping("{id}")

    public ResponseEntity<Pet> findPetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.findPetById(id);

        if (pet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(pet.get());
    }

    /**
     * Обрабатывает PUT-зарос для редактирования питомца.
     */

    @PutMapping("{id}")
    public ResponseEntity<Pet> editPet(@RequestBody Pet pet, @PathVariable Long id) {
        Pet foundPet = petService.editPet(pet);

        if (foundPet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(foundPet);
    }

    /**
     * Обрабатывает DELETE-запрос для удаления питомца.
     */
    @DeleteMapping("{id}")
    public Pet deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return (Pet) ResponseEntity.status(HttpStatus.OK);
    }


    @GetMapping("{type}")

    public Object getPetType(@PathVariable String typeOfPet) {

        Optional<Pet> pet = petService.findPetByType(typeOfPet);

        if (pet.isEmpty()) {
            return (Pet) ResponseEntity.status(HttpStatus.NOT_FOUND).build().getBody();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(petService.findPetByType(typeOfPet)).getBody();

    }
}
