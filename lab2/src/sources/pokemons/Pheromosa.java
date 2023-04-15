package sources.pokemons;
import ru.ifmo.se.pokemon.*;
import sources.moves.*;


public class Pheromosa extends Pokemon {
    public Pheromosa(String name, int level) {
        super(name, level);
        setType(Type.BUG, Type.FIGHTING);
        setStats(71, 137, 37, 137, 37, 151);
        setMove(new IcePunch(), new HammerArm(), new LowSweep(), new FocusEnergy());
    }
}
