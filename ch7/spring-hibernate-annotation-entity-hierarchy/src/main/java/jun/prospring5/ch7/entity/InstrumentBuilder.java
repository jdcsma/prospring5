package jun.prospring5.ch7.entity;

import java.util.Objects;

public final class InstrumentBuilder {

    private String instrumentId;

    public InstrumentBuilder setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
        return this;
    }

    public Instrument build() {
        Instrument instrument = new Instrument();
        instrument.setInstrumentId(Objects.requireNonNull(instrumentId));
        return instrument;
    }
}
