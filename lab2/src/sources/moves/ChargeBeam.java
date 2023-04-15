package sources.moves;
import ru.ifmo.se.pokemon.*;

public class ChargeBeam extends SpecialMove {
    public ChargeBeam() {
        super(Type.ELECTRIC, 50, 90);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        if (Math.random() < 0.7) {
            p.setMod(Stat.SPECIAL_ATTACK, 1);
        }
    }

    @Override
    protected String describe() {
        return "использует Лазер";
    }
}
