package jun.prospring5.ch7.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instrument")
public class Instrument implements Serializable {

    private String instrumentId;
    private Set<Singer> singers = new HashSet<>();

    public Instrument() {
        super();
    }

    public Instrument(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    @Id
    @Column(name = "instrument_id")
    public String getInstrumentId() {
        return instrumentId;
    }

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "instrument_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_id"))
    public Set<Singer> getSingers() {
        return singers;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public void setSingers(Set<Singer> singers) {
        this.singers = singers;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentId='" + instrumentId + '\'' +
//                ", singers=" + singers +
                '}';
    }
}
