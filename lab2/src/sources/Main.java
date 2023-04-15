package sources;
import ru.ifmo.se.pokemon.*;
import sources.pokemons.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Eelektrik("Электрик", 1);
        Pokemon p2 = new Eelektross("Электрос", 2);
        Pokemon p3 = new Pheromosa("Феромоза", 3);
        Pokemon p4 = new Tynamo("Тинамо", 1);
        Pokemon p5 = new Yanma("Янма", 2);
        Pokemon p6 = new Yanmega("Янмега", 3);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();

    }
}