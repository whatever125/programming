package sources.pokemons;
import ru.ifmo.se.pokemon.*;
import sources.moves.*;


public class Eelektrik extends Pokemon {
    public Eelektrik(String name, int level) {
        super(name, level);
        setType(Type.ELECTRIC);
        setStats(65, 85, 70, 75, 70, 40);
        setMove(new ChargeBeam(), new ThunderWave(), new Spark());
    }
}
