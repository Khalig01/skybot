package skypro_bot.skybot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "shelter")
public class Shelter {

    @Id
    @GeneratedValue
    private Long id;

    private String descriptionOfCatShelter;
    private String descriptionOfDogShelter;


    private String workingTimeOfCatShelter;
    private String workingTimeOfDogShelter;

    private String addressOfCatShelter;
    private String addressOfDogShelter;

    private String arriveOfCatShelter;
    private String arriveOfDogShelter;


    private String passOfCatShelter;
    private String passOfDogShelter;

    private String safetyGuideOfCatShelter;
    private String safetyGuideOfDogShelter;


    private String sendContact;

    private String volunteerCall;


    public Shelter() {

    }

    public Shelter(Long id, String descriptionOfCatShelter, String descriptionOfDogShelter,
                   String workingTimeOfCatShelter, String workingTimeOfDogShelter, String
                           addressOfCatShelter, String addressOfDogShelter, String arriveOfCatShelter,
                   String arriveOfDogShelter, String passOfCatShelter, String passOfDogShelter, String safetyGuideOfCatShelter,
                   String safetyGuideOfDogShelter, String sendContact, String volunteerCall) {
        this.id = id;
        this.descriptionOfCatShelter = descriptionOfCatShelter;
        this.descriptionOfDogShelter = descriptionOfDogShelter;
        this.workingTimeOfCatShelter = workingTimeOfCatShelter;
        this.workingTimeOfDogShelter = workingTimeOfDogShelter;
        this.addressOfCatShelter = addressOfCatShelter;
        this.addressOfDogShelter = addressOfDogShelter;
        this.arriveOfCatShelter = arriveOfCatShelter;
        this.arriveOfDogShelter = arriveOfDogShelter;
        this.passOfCatShelter = passOfCatShelter;
        this.passOfDogShelter = passOfDogShelter;
        this.safetyGuideOfCatShelter = safetyGuideOfCatShelter;
        this.safetyGuideOfDogShelter = safetyGuideOfDogShelter;
        this.sendContact = sendContact;
        this.volunteerCall = volunteerCall;
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", descriptionOfCatShelter='" + descriptionOfCatShelter + '\'' +
                ", descriptionOfDogShelter='" + descriptionOfDogShelter + '\'' +
                ", workingTimeOfCatShelter='" + workingTimeOfCatShelter + '\'' +
                ", workingTimeOfDogShelter='" + workingTimeOfDogShelter + '\'' +
                ", addressOfCatShelter='" + addressOfCatShelter + '\'' +
                ", addressOfDogShelter='" + addressOfDogShelter + '\'' +
                ", arriveOfCatShelter='" + arriveOfCatShelter + '\'' +
                ", arriveOfDogShelter='" + arriveOfDogShelter + '\'' +
                ", passOfCatShelter='" + passOfCatShelter + '\'' +
                ", passOfDogShelter='" + passOfDogShelter + '\'' +
                ", safetyGuideOfCatShelter='" + safetyGuideOfCatShelter + '\'' +
                ", safetyGuideOfDogShelter='" + safetyGuideOfDogShelter + '\'' +
                ", sendContact='" + sendContact + '\'' +
                ", volunteerCall='" + volunteerCall + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionOfCatShelter() {
        return descriptionOfCatShelter;
    }

    public void setDescriptionOfCatShelter(String descriptionOfCatShelter) {
        this.descriptionOfCatShelter = descriptionOfCatShelter;
    }

    public String getDescriptionOfDogShelter() {
        return descriptionOfDogShelter;
    }

    public void setDescriptionOfDogShelter(String descriptionOfDogShelter) {
        this.descriptionOfDogShelter = descriptionOfDogShelter;
    }

    public String getWorkingTimeOfCatShelter() {
        return workingTimeOfCatShelter;
    }

    public void setWorkingTimeOfCatShelter(String workingTimeOfCatShelter) {
        this.workingTimeOfCatShelter = workingTimeOfCatShelter;
    }

    public String getWorkingTimeOfDogShelter() {
        return workingTimeOfDogShelter;
    }

    public void setWorkingTimeOfDogShelter(String workingTimeOfDogShelter) {
        this.workingTimeOfDogShelter = workingTimeOfDogShelter;
    }

    public String getAddressOfCatShelter() {
        return addressOfCatShelter;
    }

    public void setAddressOfCatShelter(String addressOfCatShelter) {
        this.addressOfCatShelter = addressOfCatShelter;
    }

    public String getAddressOfDogShelter() {
        return addressOfDogShelter;
    }

    public void setAddressOfDogShelter(String addressOfDogShelter) {
        this.addressOfDogShelter = addressOfDogShelter;
    }

    public String getArriveOfCatShelter() {
        return arriveOfCatShelter;
    }

    public void setArriveOfCatShelter(String arriveOfCatShelter) {
        this.arriveOfCatShelter = arriveOfCatShelter;
    }

    public String getArriveOfDogShelter() {
        return arriveOfDogShelter;
    }

    public void setArriveOfDogShelter(String arriveOfDogShelter) {
        this.arriveOfDogShelter = arriveOfDogShelter;
    }

    public String getPassOfCatShelter() {
        return passOfCatShelter;
    }

    public void setPassOfCatShelter(String passOfCatShelter) {
        this.passOfCatShelter = passOfCatShelter;
    }

    public String getPassOfDogShelter() {
        return passOfDogShelter;
    }

    public void setPassOfDogShelter(String passOfDogShelter) {
        this.passOfDogShelter = passOfDogShelter;
    }

    public String getSafetyGuideOfCatShelter() {
        return safetyGuideOfCatShelter;
    }

    public void setSafetyGuideOfCatShelter(String safetyGuideOfCatShelter) {
        this.safetyGuideOfCatShelter = safetyGuideOfCatShelter;
    }

    public String getSafetyGuideOfDogShelter() {
        return safetyGuideOfDogShelter;
    }

    public void setSafetyGuideOfDogShelter(String safetyGuideOfDogShelter) {
        this.safetyGuideOfDogShelter = safetyGuideOfDogShelter;
    }

    public String getSendContact() {
        return sendContact;
    }

    public void setSendContact(String sendContact) {
        this.sendContact = sendContact;
    }

    public String getVolunteerCall() {
        return volunteerCall;
    }

    public void setVolunteerCall(String volunteerCall) {
        this.volunteerCall = volunteerCall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(id, shelter.id) && Objects.equals(descriptionOfCatShelter,
                shelter.descriptionOfCatShelter) && Objects.equals(descriptionOfDogShelter,
                shelter.descriptionOfDogShelter) && Objects.equals(workingTimeOfCatShelter,
                shelter.workingTimeOfCatShelter) && Objects.equals(workingTimeOfDogShelter,
                shelter.workingTimeOfDogShelter) && Objects.equals(addressOfCatShelter,
                shelter.addressOfCatShelter) && Objects.equals(addressOfDogShelter,
                shelter.addressOfDogShelter) && Objects.equals(arriveOfCatShelter,
                shelter.arriveOfCatShelter) && Objects.equals(arriveOfDogShelter,
                shelter.arriveOfDogShelter) && Objects.equals(passOfCatShelter,
                shelter.passOfCatShelter) && Objects.equals(passOfDogShelter,
                shelter.passOfDogShelter) && Objects.equals(safetyGuideOfCatShelter,
                shelter.safetyGuideOfCatShelter) && Objects.equals(safetyGuideOfDogShelter,
                shelter.safetyGuideOfDogShelter) && Objects.equals(sendContact,
                shelter.sendContact) && Objects.equals(volunteerCall, shelter.volunteerCall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descriptionOfCatShelter, descriptionOfDogShelter,
                workingTimeOfCatShelter, workingTimeOfDogShelter, addressOfCatShelter,
                addressOfDogShelter, arriveOfCatShelter, arriveOfDogShelter, passOfCatShelter,
                passOfDogShelter, safetyGuideOfCatShelter, safetyGuideOfDogShelter, sendContact,
                volunteerCall);
    }
}
