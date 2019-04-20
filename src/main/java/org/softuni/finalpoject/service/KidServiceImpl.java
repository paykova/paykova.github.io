package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.*;
import org.softuni.finalpoject.domain.models.service.KidServiceModel;
import org.softuni.finalpoject.repository.KidRepository;
import org.softuni.finalpoject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KidServiceImpl implements KidService{

    private final KidRepository kidRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final SportService sportService;
    private final LanguageService languageService;
    private final OtherActivityService otherActivityService;
    private final InstrumentService instrumentService;


    @Autowired
    public KidServiceImpl(KidRepository kidRepository, ModelMapper modelMapper, UserRepository userRepository, SportService sportService, LanguageService languageService, OtherActivityService otherActivityService, InstrumentService instrumentService) {
        this.kidRepository = kidRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.sportService = sportService;
        this.languageService = languageService;
        this.otherActivityService = otherActivityService;
        this.instrumentService = instrumentService;
    }

    @Override
    public KidServiceModel findKidById(String id) {
        Kid kid = this.kidRepository.findById(id).orElse(null);
        if (kid == null) {
            throw new IllegalArgumentException(id);
        }
        return this.modelMapper.map(kid, KidServiceModel.class);

    }

    @Override
    public KidServiceModel addKid(KidServiceModel kidServiceModel, String name) {
        Kid kid = this.kidRepository.findByName(kidServiceModel.getName()).orElse(null);

        if(kid != null){
            throw new IllegalArgumentException("Kid name already exist");
        }

        kid = this.modelMapper.map(kidServiceModel, Kid.class);
        User user = userRepository.findByUsername(name).orElseThrow();
        kid.setParent(user);

        try {
            kid = this.kidRepository.saveAndFlush(kid);
            return this.modelMapper.map(kid, KidServiceModel.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<KidServiceModel> findAllKids() {
        var models = this.kidRepository.findAll()
                .stream()
                .map(u -> this.modelMapper
                        .map(u, KidServiceModel.class))
                .collect(Collectors.toList());
        return models;
    }

    @Override
    public List<KidServiceModel> findKidsByParent(String username) {
        var model = this.kidRepository.findAllKidsByParent_Id(username)
                .stream()
                .map(k -> modelMapper.map(k, KidServiceModel.class))
                .collect(Collectors.toList());
        return model;
    }

    @Override
    public void deleteKid(String id) {
        Kid kid = this.kidRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Kid with the given id was not found!"));

        this.kidRepository.delete(kid);
    }

    @Override
    public KidServiceModel editKid(String id, KidServiceModel kidServiceModel) {
        Kid kid = this.kidRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kid with the given id was not found!"));



        kidServiceModel.setLanguages(
                this.languageService.findAllLanguages()
                        .stream()
                        .filter(l -> kidServiceModel.getLanguages().contains(l.getId()))
                        .collect(Collectors.toList())
        );

        kidServiceModel.setInstruments(
                this.instrumentService.findAllInstruments()
                        .stream()
                        .filter(i -> kidServiceModel.getInstruments().contains(i.getId()))
                        .collect(Collectors.toList())
        );

        kidServiceModel.setSports(
                this.sportService.findAllSports()
                        .stream()
                        .filter(s -> kidServiceModel.getSports().contains(s.getId()))
                        .collect(Collectors.toList())
        );

        kidServiceModel.setOtheractivities(
                this.otherActivityService.findAllOtherActivities()
                        .stream()
                        .filter(o -> kidServiceModel.getOtheractivities().contains(o.getId()))
                        .collect(Collectors.toList())
        );
//
//        User user = new User();
//        user.setId(kidServiceModel.getParent().getId());
//        kidServiceModel.setParent(user);
//        kid.setParent(user);

        kid.setName(kidServiceModel.getName());
        kid.setDescription(kidServiceModel.getDescription());
        kid.setBirthDate(kidServiceModel.getBirthDate());
        kid.setParent(kid.getParent());

        kid.setLanguages(
                kidServiceModel.getLanguages()
                        .stream()
                        .map(l -> this.modelMapper.map(l, Language.class))
                        .collect(Collectors.toList())
        );
        kid.setInstruments(
                kidServiceModel.getInstruments()
                        .stream()
                        .map(i -> this.modelMapper.map(i, Instrument.class))
                        .collect(Collectors.toList())
        );
        kid.setSports(
                kidServiceModel.getSports()
                        .stream()
                        .map(l -> this.modelMapper.map(l, Sport.class))
                        .collect(Collectors.toList())
        );
        kid.setOtherActivities(
                kidServiceModel.getOtheractivities()
                        .stream()
                        .map(o -> this.modelMapper.map(o, OtherActivity.class))
                        .collect(Collectors.toList())
        );


        return this.modelMapper.map(this.kidRepository.saveAndFlush(kid), KidServiceModel.class);
    }
}
