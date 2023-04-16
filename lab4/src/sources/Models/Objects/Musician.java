package sources.Models.Objects;

public class Musician extends Malysh {
    String instrument;

    public Musician(String name) {
        super(name);
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
