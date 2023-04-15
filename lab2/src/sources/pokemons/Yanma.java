package sources.pokemons;
import ru.ifmo.se.pokemon.*;
import sources.moves.*;


public class Yanma extends Pokemon {
    public Yanma(String name, int level) {
        super(name, level);
        setType(Type.BUG, Type.FLYING);
        setStats(65, 65, 45, 75, 45, 95);
        setMove(new LeechLife(), new SonicBoom(), new Psychic());
    }
}
