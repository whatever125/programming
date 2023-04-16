package sources;

import sources.Events.*;
import sources.Exceptions.NoSuchEventException;
import sources.Models.Abstracts.Creature;
import sources.Models.Abstracts.Thing;
import sources.Models.Objects.*;

import java.util.ArrayList;
import java.util.List;

public class Story {
    private final List<EventInterface> events = new ArrayList<>();

    public void start() {
        Doctor pilyulkin = new Doctor("Пилюлькин");
        pilyulkin.setPopularity(0.9);
        Malysh shpuntik = new Malysh("Шпунтик");
        Mechanic vintik = new Mechanic("Винтик");
        vintik.setAssistant(shpuntik);
        vintik.setPopularity(0.83);
        Malysh avoska = new Malysh("Авоська");
        Malysh neboska = new Malysh("Небоська");
        avoska.makeBrothers(neboska);
        Artist tyubik = new Artist("Тюбик");
        Musician guslya = new Musician("Гусля");
        Hunter pulka = new Hunter("Пулька", "пробковое");
        Dog gulka = new Dog("Гулька", pulka);
        pulka.setDog(gulka);
        Malysh toropyzhka = new Malysh("Торопыжка");
        Malysh vorchun = new Malysh("Ворчун");
        Malysh molchun = new Malysh("Молчун");
        Malysh ponchik = new Malysh("Пончик");
        Malysh rasteryaika = new Malysh("Растеряйка");
        Malysh syropchik = new Malysh("Сахарин Сахариныч Сиропчик");
        syropchik.act("*Любить газированную воду с сиропом*");
        syropchik.act("*Быть вежливым*");
        syropchik.setPopularity(0.65);
        Malysh znayka = new Malysh("Знайка");
        znayka.setClothes(new Thing("черный костюм") {
            @Override
            public void action(String name) {
                super.action(getName() + " надевается");
            }
        });
        Malysh neznayka = new Malysh("Незнайка");
        neznayka.setIntelligence(0);
        neznayka.setHat(new Thing("яркая голубая шляпа") {
            @Override
            public void action(String name) {
                super.action(getName() + " надевается");
            }
        });
        neznayka.setPants(new Thing("желтые канареечные брюки") {
            @Override
            public void action(String name) {
                super.action(getName() + " надеваются");
            }
        });
        neznayka.setClothes(new Thing("оранжевая рубашка с зеленым галстуком") {
            @Override
            public void action(String name) {
                super.action(getName() + " надевается");
            }
        });

        List<Creature> residents1 = List.of(pilyulkin, shpuntik, vintik, avoska, neboska, tyubik, guslya, pulka,
                toropyzhka, vorchun, molchun, ponchik, rasteryaika, znayka, neznayka);
        House house1 = new House("улица Колокольчиков");
        SettlementEvent settlementEvent1 = new SettlementEvent(house1, residents1);
        addEvent(settlementEvent1);

        Malysh gunka = new Malysh("Гунька");
        gunka.makeFriends(neznayka);

        List<Creature> residents2 = List.of(gunka);
        House house2 = new House("улица Маргариток");
        SettlementEvent settlementEvent2 = new SettlementEvent(house2, residents2);
        addEvent(settlementEvent2);
        house2.addRoom(znayka);

        BecomingCleverEvent znaykaBecomingCleverEvent= new BecomingCleverEvent(znayka);
        addEvent(znaykaBecomingCleverEvent);

        Beetle beetle = new Beetle("майский");

        MeetingEvent meetingEvent = new MeetingEvent(neznayka, beetle);
        addEvent(meetingEvent);

        NeznaykaWalkEvent neznaykaWalkEvent = new NeznaykaWalkEvent(neznayka, gunka);
        addEvent(neznaykaWalkEvent);
    }

    public void tell() {
        System.out.println("-----");
        System.out.println("EVENTS");
        System.out.println("-----");
        for (EventInterface day_event : this.events) {
            day_event.start();
        }
    }

    private void addEvent(EventInterface eventInterface) {
        events.add(eventInterface);
    }

    private void removeEvent(EventInterface eventInterface) {
        if (!events.contains(eventInterface)) {
            throw new NoSuchEventException();
        } else {
            events.add(eventInterface);
        }
    }
}
