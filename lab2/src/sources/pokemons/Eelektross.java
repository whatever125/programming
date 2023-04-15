package sources.pokemons;
import ru.ifmo.se.pokemon.*;
import sources.moves.*;


public class Eelektross extends Pokemon {
    public Eelektross(String name, int level) {
        super(name, level);
        setType(Type.ELECTRIC);
        setStats(85, 115, 80, 105, 80, 50);
        setMove(new ChargeBeam(), new ThunderWave(), new Spark(), new Flamethrower());
    }
}
