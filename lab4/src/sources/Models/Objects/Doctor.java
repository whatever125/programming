package sources.Models.Objects;

import sources.Models.Abstracts.Thing;
import sources.Models.Interfaces.TreatInterface;

public class Doctor extends Malysh implements TreatInterface {
    public Doctor(String name) {
        super(name);
        setClothes(new Thing("белый халат") {
            @Override
            public void action(String name) {
                super.action(getName() + " надевается");
            }
        });
        setHat(new Thing("белый колпак с кисточкой") {
            @Override
            public void action(String name) {
                super.action(getName() + " надевается");
            }
        });
    }

    public void treat(Malysh malysh) {
        System.out.println("Малыш-коротыш " + malysh.getName() + " вылечился с " + malysh.getHealth() * 100 + "% до 100%");
        malysh.setHealth(1);
    }
}
