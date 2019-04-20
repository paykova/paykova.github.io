package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Sport;
import org.softuni.finalpoject.domain.models.service.SportServiceModel;
import org.softuni.finalpoject.domain.models.view.SportViewModel;
import org.softuni.finalpoject.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportServiceImpl implements SportService {

    private final SportRepository sportRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SportServiceImpl(SportRepository sportRepository, ModelMapper modelMapper) {
        this.sportRepository = sportRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public SportServiceModel addSport(SportServiceModel sportServiceModel) {
        Sport sport = this.modelMapper.map(sportServiceModel, Sport.class);

        return this.modelMapper.map(this.sportRepository.saveAndFlush(sport), SportServiceModel.class);
    }

    @Override
    public List<SportServiceModel> findAllSports() {
        return this.sportRepository.findAll()
                .stream()
                .map(s -> this.modelMapper.map(s, SportServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SportServiceModel findSportById(String id) {

        Sport sport = this.sportRepository.findById(id).orElse(null);
        if(sport == null){
            throw  new IllegalArgumentException(id);
        }
        return this.modelMapper.map(sport, SportServiceModel.class);
    }

    @Override
    public SportServiceModel editSport(String id, SportServiceModel sportServiceModel) {
        Sport sport = this.sportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        sport.setName(sportServiceModel.getName());

        return this.modelMapper.map(this.sportRepository.saveAndFlush(sport), SportServiceModel.class);
    }

    @Override
    public SportServiceModel deleteSport(String id) {
        Sport sport = this.sportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        this.sportRepository.delete(sport);
        return this.modelMapper.map(sport, SportServiceModel.class);
    }

    @Override
    public List<SportViewModel> getSportsNames() {
        List<SportViewModel> result;
        result = findAllSports().stream().map(s -> this.modelMapper.map(s, SportViewModel.class)).collect(Collectors.toList());

        return result;
    }
    @Override
    public List<SportServiceModel> getSportsByIds(List<String> ids) {

        List<SportServiceModel> result = new ArrayList<>();

        for (String id : ids) {
            SportServiceModel model = findSportById(id);
            result.add(model);
        }
        return result;
    }
}
