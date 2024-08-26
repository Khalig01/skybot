package skypro_bot.skybot.service;

import org.springframework.stereotype.Service;

import skypro_bot.skybot.model.Pet;
import skypro_bot.skybot.repository.PetRepository;

import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> findPetById(Long id) {
        return petRepository.findById(id);
    }

    public Pet editPet(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public Optional<Pet> findPetByType(String petOfType) {
        return petRepository.findByPetType(petOfType);
    }

}
