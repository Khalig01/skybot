package skypro_bot.skybot.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import skypro_bot.skybot.model.Shelter;
import skypro_bot.skybot.repository.ShelterRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShelterServiceTest {

    @Mock
    private ShelterRepository shelterRepository;

    private final ShelterService shelterService;

    public ShelterServiceTest() {
        MockitoAnnotations.openMocks(this);
        shelterService = new ShelterService(shelterRepository);
    }

    @Test
    public void testAddShelter() {
        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setDescriptionOfCatShelter("cats");

        when(shelterRepository.save(any(Shelter.class))).thenReturn(shelter);

        Shelter addedShelter = shelterService.addShelter(shelter);

        assertEquals("cats", addedShelter.getDescriptionOfCatShelter());

        verify(shelterRepository, times(1)).save(any(Shelter.class));
    }

    @Test
    public void testFindShelterById() {
        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setDescriptionOfDogShelter("dogs");

        when(shelterRepository.findById(1L)).thenReturn(Optional.of(shelter));

        Optional<Shelter> foundShelter = shelterService.findShelterById(1L);

        assertEquals("dogs", foundShelter.get().getDescriptionOfDogShelter());

        verify(shelterRepository, times(1)).findById(1L);
    }

    @Test
    public void testEditPet() {
        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setDescriptionOfDogShelter("dogs");

        when(shelterRepository.save(any(Shelter.class))).thenReturn(shelter);

        Shelter editedShelter = shelterService.editShelter(shelter);

        assertEquals("dogs", editedShelter.getDescriptionOfDogShelter());

        verify(shelterRepository, times(1)).save(any(Shelter.class));
    }

    @Test
    public void testDeleteShelter() {
        shelterService.deleteShelter(1L);

        verify(shelterRepository, times(1)).deleteById(1L);
    }
}
