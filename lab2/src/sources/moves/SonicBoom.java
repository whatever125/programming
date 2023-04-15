package sources.moves;
import ru.ifmo.se.pokemon.*;

public class SonicBoom extends SpecialMove {
    public SonicBoom() {
        super(Type.NORMAL, 0, 90);
    }

    @Override
    protected void applyOppDamage(Pokemon p, double damage) {
        super.applyOppDamage(p, 20);
    }

    @Override
    protected String describe() {
        return "использует Волну Звука";
    }
}
