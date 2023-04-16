package sources.Models.Objects;

import sources.Models.Abstracts.Building;
import sources.Models.Abstracts.Creature;

import java.util.List;

public class House extends Building {
    private List<Creature> residents;

    public House(String street) {
        super(street);
    }

    public House(String street, int number) {
        super(street, number);
    }

    public List<Creature> getResidents() {
        return residents;
    }

    public void setResidents(List<Creature> residents) {
        this.residents = residents;
    }
}
