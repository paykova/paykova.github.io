package org.softuni.finalpoject.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sports")
public class Sport extends BaseEntity {

    private String name;

    public Sport() {
    }

    @Column(name = "sport", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
