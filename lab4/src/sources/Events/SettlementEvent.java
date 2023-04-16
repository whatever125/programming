package sources.Events;

import sources.Models.Abstracts.Creature;
import sources.Models.Objects.House;

import java.util.List;

public class SettlementEvent extends Event {
    private final House house;
    private final List<Creature> residents;
    public SettlementEvent(House house, List<Creature> residents) {
        super("Заселение по адресу " + house.toString());
        this.house = house;
        this.residents = residents;
    }

    @Override
    public void start() {
        class NamePrinter {
            List<Creature> creatures;

            public String getNames(List<Creature> creatures) {
                String names = "";
                for (int i = 0; i < creatures.size(); i++) {
                    if (i != creatures.size() - 1) {
                        names += creatures.get(i) + ", ";
                    } else {
                        names += creatures.get(i);
                    }
                }
                return names;
            }
        }

        System.out.println("#####");
        System.out.println(getName());
        house.setResidents(residents);
        System.out.println("В " + house + " заселяются: " + new NamePrinter().getNames(residents));
        System.out.println();
    }
}
