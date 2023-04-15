package sources.pokemons;
import ru.ifmo.se.pokemon.*;
import sources.moves.*;


public class Tynamo extends Pokemon {
    public Tynamo(String name, int level) {
        super(name, level);
        setType(Type.ELECTRIC);
        setStats(35, 55, 40, 45, 40, 60);
        setMove(new ChargeBeam(), new ThunderWave());
    }
}
