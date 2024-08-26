package skypro_bot.skybot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro_bot.skybot.error.VolunteerNotFoundException;
import skypro_bot.skybot.model.Pet;
import skypro_bot.skybot.model.Volunteer;
import skypro_bot.skybot.repository.PetRepository;
import skypro_bot.skybot.repository.VolunteerRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Метод добавляет волонтера в базу данных.
     */
    public Volunteer addVolunteer(Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

    /**
     * Метод выводит весь список волонтеров.
     */
    public Collection<Volunteer> getAllVolunteer(){
        return volunteerRepository.findAll();
    }
    public Optional<Volunteer> findVolunteerById(Long id) {
        return volunteerRepository.findById(id);
    }
    public Optional<Volunteer> findVolunteerByChatId(Long chatId) {
        return volunteerRepository.findByChatId(chatId);
    }
    public Optional<Volunteer> findFreeVolunteer() {
        return volunteerRepository.findFirstByIsBusy(false);
    }
    public Volunteer keepVolunteerBusy(Long chatId, Boolean isBusy) {
        Optional<Volunteer> volunteerOptional = findVolunteerByChatId(chatId);
        if (volunteerOptional.isEmpty()) {
            throw new VolunteerNotFoundException();
        }
        Volunteer volunteer = volunteerOptional.get();
        volunteer.setBusy(isBusy);

        return editVolunteer(volunteer);
    }

    public Volunteer editVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }
}
