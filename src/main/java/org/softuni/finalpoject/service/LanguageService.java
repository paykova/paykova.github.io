package org.softuni.finalpoject.service;

import javassist.NotFoundException;
import org.softuni.finalpoject.domain.entities.Language;
import org.softuni.finalpoject.domain.models.service.LanguageServiceModel;
import org.softuni.finalpoject.domain.models.view.LanguageViewModel;

import java.util.List;

public interface LanguageService {

    LanguageServiceModel addLanguage(LanguageServiceModel languageServiceModel);

    List<LanguageServiceModel> findAllLanguages();

    LanguageServiceModel findLanguageById(String id) throws NotFoundException;

    LanguageServiceModel editLanguage(String id, LanguageServiceModel languageServiceModel);

    LanguageServiceModel deleteLanguage(String id);

    List<LanguageViewModel> getLanguagesNames();

    List<LanguageServiceModel> getLanguagesByIds(List<String> list);
}
