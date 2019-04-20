package org.softuni.finalpoject.domain.models.service;

import org.softuni.finalpoject.domain.entities.Gender;
import org.softuni.finalpoject.domain.entities.User;

import java.time.LocalDate;
import java.util.List;

public class KidEditServiceModel extends BaseServiceModel {

    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String imageUrl;
    private String description;
    private List<LanguageServiceModel> language;
    private List<InstrumentServiceModel> instrument;
    private List<SportServiceModel> sport;
    private List<OtherActivityServiceModel> otheractivity;
    private User parent;

    public KidEditServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LanguageServiceModel> getLanguage() {
        return language;
    }

    public void setLanguage(List<LanguageServiceModel> language) {
        this.language = language;
    }

    public List<InstrumentServiceModel> getInstrument() {
        return instrument;
    }

    public void setInstrument(List<InstrumentServiceModel> instrument) {
        this.instrument = instrument;
    }

    public List<SportServiceModel> getSport() {
        return sport;
    }

    public void setSport(List<SportServiceModel> sport) {
        this.sport = sport;
    }

    public List<OtherActivityServiceModel> getOtheractivity() {
        return otheractivity;
    }

    public void setOtheractivity(List<OtherActivityServiceModel> otheractivity) {
        this.otheractivity = otheractivity;
    }

    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }
}
