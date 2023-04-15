package sources.pokemons;
import ru.ifmo.se.pokemon.*;
import sources.moves.*;


public class Yanmega extends Pokemon {
    public Yanmega(String name, int level) {
        super(name, level);
        setType(Type.BUG, Type.FLYING);
        setStats(86, 76, 86, 116, 56, 95);
        setMove(new LeechLife(), new SonicBoom(), new Psychic(), new NightSlash());
    }
}
