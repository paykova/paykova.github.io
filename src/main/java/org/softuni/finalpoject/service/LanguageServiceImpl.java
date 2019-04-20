package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Language;
import org.softuni.finalpoject.domain.models.view.LanguageViewModel;
import org.softuni.finalpoject.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.softuni.finalpoject.domain.models.service.LanguageServiceModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository, ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LanguageServiceModel addLanguage(LanguageServiceModel languageServiceModel) {
        Language language = this.modelMapper.map(languageServiceModel, Language.class);

        return this.modelMapper.map(this.languageRepository.saveAndFlush(language), LanguageServiceModel.class);
    }

    @Override
    public List<LanguageServiceModel> findAllLanguages() {
        return this.languageRepository.findAll()
                .stream()
                .map(l -> this.modelMapper.map(l, LanguageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public LanguageServiceModel findLanguageById(String id) {
        Language language = this.languageRepository.findById(id).orElse(null);
        if (language == null) {
            throw new IllegalArgumentException(id);
        }
        return this.modelMapper.map(language, LanguageServiceModel.class);
    }

    @Override
    public LanguageServiceModel editLanguage(String id, LanguageServiceModel languageServiceModel) {
        Language language = this.languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        language.setName(languageServiceModel.getName());
        return this.modelMapper.map(this.languageRepository.saveAndFlush(language), LanguageServiceModel.class);
    }

    @Override
    public LanguageServiceModel deleteLanguage(String id) {
        Language language = this.languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        this.languageRepository.delete(language);
        return this.modelMapper.map(language, LanguageServiceModel.class);
    }

    @Override
    public List<LanguageViewModel> getLanguagesNames() {

        List<LanguageViewModel> result;
        result = findAllLanguages().stream().map(l -> this.modelMapper.map(l, LanguageViewModel.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<LanguageServiceModel> getLanguagesByIds(List<String> ids) {

        List<LanguageServiceModel> result = new ArrayList<>();

        for (String id : ids) {
            LanguageServiceModel model = findLanguageById(id);
            result.add(model);
        }
        return result;
    }
}
