package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.models.service.InstrumentServiceModel;
import org.softuni.finalpoject.domain.models.view.InstrumentViewModel;
import org.softuni.finalpoject.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InstrumentServiceImpl(InstrumentRepository instrumentRepository, ModelMapper modelMapper) {
        this.instrumentRepository = instrumentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public InstrumentServiceModel addInstrument(InstrumentServiceModel instrumentServiceModel) {
       Instrument instrument = this.modelMapper.map(instrumentServiceModel, Instrument.class);

        return this.modelMapper.map(this.instrumentRepository.saveAndFlush(instrument), InstrumentServiceModel.class);
    }

    @Override
    public List<InstrumentServiceModel> findAllInstruments() {

        return this.instrumentRepository.findAll()
                .stream().map(i -> this.modelMapper.map(i, InstrumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public InstrumentServiceModel findInstrumentById(String id) {
        Instrument instrument = this.instrumentRepository.findById(id).orElse(null);
        if (instrument == null){
            throw new IllegalArgumentException(id);
        }
        return this.modelMapper.map(instrument, InstrumentServiceModel.class);
    }

    @Override
    public InstrumentServiceModel editInstrument(String id, InstrumentServiceModel instrumentServiceModel) {
        Instrument instrument = this.instrumentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        instrument.setName(instrumentServiceModel.getName());
        return this.modelMapper.map(this.instrumentRepository.saveAndFlush(instrument), InstrumentServiceModel.class);
    }

    @Override
    public InstrumentServiceModel deleteInstrument(String id) {
        Instrument instrument = this.instrumentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        this.instrumentRepository.delete(instrument);
        return this.modelMapper.map(instrument, InstrumentServiceModel.class);
    }

    @Override
    public List<InstrumentViewModel> getInstrumentNames() {
        List<InstrumentViewModel> result;
        result = findAllInstruments().stream().map(i -> this.modelMapper.map(i, InstrumentViewModel.class)).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<InstrumentServiceModel> getInstrumentsByIds(List<String> ids) {

        List<InstrumentServiceModel> result = new ArrayList<>();

        for (String id : ids) {
            InstrumentServiceModel model = findInstrumentById(id);
            result.add(model);
        }
        return result;
    }
}
