package org.softuni.finalpoject.domain.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LanguageAddBindingModel {

    private String name;

    public LanguageAddBindingModel() {
    }

    @NotNull(message = "Language Name cannot be null")
    @NotEmpty
    @Length(min = 2, message = "Language Name must be at least 2 characters long.")
    @Length(max = 20, message = "Language Name must be maximum 20 characters long.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
