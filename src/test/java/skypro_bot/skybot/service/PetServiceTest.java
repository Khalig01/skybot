package skypro_bot.skybot.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import skypro_bot.skybot.model.Pet;
import skypro_bot.skybot.repository.PetRepository;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    private final PetService petService;

    public PetServiceTest() {
        MockitoAnnotations.openMocks(this);
        petService = new PetService(petRepository);
    }

    @Test
    public void testAddPet() {
        Pet pet = new Pet();
        pet.setName("Вася");

        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet addedPet = petService.addPet(pet);

        assertEquals("Вася", addedPet.getName());

        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    public void testFindPetById() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Вася");

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Optional<Pet> foundPet = petService.findPetById(1L);

        assertEquals("Вася", foundPet.get().getName());

        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    public void testEditPet() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Вася");

        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet editedPet = petService.editPet(pet);

        assertEquals("Вася", editedPet.getName());

        verify(petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    public void testDeletePet() {
        petService.deletePet(1L);

        verify(petRepository, times(1)).deleteById(1L);
    }
}