package org.softuni.finalpoject.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "other_activities")
public class OtherActivity extends BaseEntity {

    private String name;

    public OtherActivity() {
    }

    @Column(name = "category_name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
