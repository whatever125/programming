package sources.moves;
import ru.ifmo.se.pokemon.*;

public class HammerArm extends PhysicalMove {
    public HammerArm() {
        super(Type.FIGHTING, 100, 90);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.SPEED, -1);
    }

    @Override
    protected String describe() {
        return "ударяет Рукой-Молотом";
    }
}
