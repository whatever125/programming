package sources.moves;
import ru.ifmo.se.pokemon.*;

public class Spark extends PhysicalMove {
    public Spark() {
        super(Type.ELECTRIC, 65, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.3) {
            Effect.paralyze(p);
        }
    }

    @Override
    protected String describe() {
        return "использует Искры";
    }
}
