package jun.prospring5.ch8.service;

import jun.prospring5.ch8.entity.Instrument;

import java.util.List;

public interface InstrumentService {

    List<Instrument> findAllByCriteriaQuery();

    List<Instrument> findByCriteriaQuery(String instrumentId);

    Instrument save(Instrument instrument);
}
