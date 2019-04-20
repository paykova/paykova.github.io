package org.softuni.finalpoject.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kids")
public class Kid extends BaseEntity {

    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String imageUrl;
    private String description;
    private List<Language> languages;
    private List<Sport> sports;
    private List<Instrument> instruments;
    private List<OtherActivity> otherActivities;
    private User parent;

    public Kid() {
    }

    @Column(name = "name", nullable = false, unique = false, updatable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date", nullable = false, unique = false, updatable = true)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Enumerated
    @Column(name = "gender", nullable = false, unique = false, updatable = true)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @ManyToMany(targetEntity = Language.class)
    @JoinTable(
            name = "kids_languages",
            joinColumns = @JoinColumn(
                    name = "kid_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "language_id",
                    referencedColumnName = "id"
            )
    )
    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @ManyToMany(targetEntity = Sport.class)
    @JoinTable(
            name = "kids_sports",
            joinColumns = @JoinColumn(
                    name = "kid_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "sport_id",
                    referencedColumnName = "id"
            )
    )
    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    @ManyToMany(targetEntity = Instrument.class)
    @JoinTable(
            name = "kids_instruments",
            joinColumns = @JoinColumn(
                    name = "kid_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "instrument_id",
                    referencedColumnName = "id"
            )
    )
    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    @ManyToMany(targetEntity = OtherActivity.class)
    @JoinTable(
            name = "kids_otheractivities",
            joinColumns = @JoinColumn(
                    name = "kid_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "otheractivity_id",
                    referencedColumnName = "id"
            )
    )
    public List<OtherActivity> getOtherActivities() {
        return otherActivities;
    }

    public void setOtherActivities(List<OtherActivity> otherActivities) {
        this.otherActivities = otherActivities;
    }


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "parent_id",
            referencedColumnName = "id",
            nullable = false
    )
    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }
}