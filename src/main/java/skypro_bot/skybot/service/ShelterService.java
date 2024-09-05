package skypro_bot.skybot.service;

import org.springframework.stereotype.Service;



import skypro_bot.skybot.model.Shelter;

import skypro_bot.skybot.repository.ShelterRepository;

import java.util.Optional;


@Service
public class ShelterService {

    private final ShelterRepository shelterRepository;


    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public Shelter addShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public Optional<Shelter> findShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    public Shelter editShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }
    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id);
    }
}
