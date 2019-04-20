package org.softuni.finalpoject.service;

import javassist.NotFoundException;
import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.models.service.InstrumentServiceModel;
import org.softuni.finalpoject.domain.models.view.InstrumentViewModel;

import java.util.List;

public interface InstrumentService {

    InstrumentServiceModel addInstrument(InstrumentServiceModel instrumentServiceModel);

    List<InstrumentServiceModel> findAllInstruments();

    InstrumentServiceModel findInstrumentById(String id);

    InstrumentServiceModel editInstrument(String id, InstrumentServiceModel instrumentServiceModel);

    InstrumentServiceModel deleteInstrument(String id);

    List<InstrumentViewModel> getInstrumentNames();

    List<InstrumentServiceModel> getInstrumentsByIds(List<String> list);

}



