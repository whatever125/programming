package sources.moves;
import ru.ifmo.se.pokemon.*;

public class IcePunch extends PhysicalMove {
    public IcePunch() {
        super(Type.ICE, 75, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) {
            Effect.freeze(p);
        }
    }

    @Override
    protected String describe() {
        return "ударяет Кулаком Льда";
    }
}
