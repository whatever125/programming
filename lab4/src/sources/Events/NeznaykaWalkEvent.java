package sources.Events;

import sources.Enums.Place;
import sources.Models.Objects.Malysh;

public class NeznaykaWalkEvent extends Event {
    private final Malysh neznayka;
    private final Malysh gunka;

    public NeznaykaWalkEvent(Malysh neznayka, Malysh gunka) {
        super("Незнайка проводит свободное время");
        this.neznayka = neznayka;
        this.gunka = gunka;
    }

    @Override
    public void start() {
        System.out.println("#####");
        System.out.println(getName());
        neznayka.act(Place.CITY);
        neznayka.talk("* Рассказываю небылицы *");
        neznayka.act("обижать малышек");
        neznayka.act("болтать с Гунькой");
        neznayka.act("ссориться и мириться с Гунькой");
    }
}
