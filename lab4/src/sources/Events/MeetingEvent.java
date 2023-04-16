package sources.Events;

import sources.Enums.Place;
import sources.Exceptions.BeetleFlyAwayException;
import sources.Models.Objects.Beetle;
import sources.Models.Objects.Malysh;
import sources.Models.Abstracts.Thing;

import java.net.URISyntaxException;

public class MeetingEvent extends Event {
    private final Malysh malysh;
    private final Beetle beetle;

    public MeetingEvent(Malysh malysh, Beetle beetle) {
        super("Встреча " + malysh.getName() + " и " + beetle);
        this.malysh = malysh;
        this.beetle = beetle;
    }

    @Override
    public void start() {
        System.out.println("#####");
        System.out.println(getName());
        malysh.act(Place.CITY);
        beetle.act("летит");
        beetle.lookAround();
        boolean beetleCanSee = beetle.canSee();
        try {
            if (!beetleCanSee) {
                beetle.flyOver(malysh);
                beetle.applySightDamage(malysh, "удар по затылку");
                beetle.flyAway();
                malysh.act(Place.GROUND);
                malysh.lookAround();
                malysh.canSee();
                malysh.think("Кто же это меня ударил?");
                malysh.think("Может быть, сверху упало что-нибудь?");
                malysh.act(Place.UP);
                Thing sun = new Thing("Солнце") {
                    @Override
                    public void action(String name) {
                        System.out.println(getName() + " ярко сияет");
                    }
                };
                sun.action("ярко сиять");
                malysh.setPopularity(1);
            } else {
                throw new BeetleFlyAwayException();
            }
        } catch (BeetleFlyAwayException | URISyntaxException e) {
            System.out.println(e.getMessage());
            beetle.flyAway();
            malysh.setPopularity(0.5);
        }
        System.out.println();
    }
}
