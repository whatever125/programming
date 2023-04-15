package sources.moves;
import ru.ifmo.se.pokemon.*;

public class NightSlash extends PhysicalMove {
    public NightSlash() {
        super(Type.DARK, 70, 100);
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        if (att.getStat(Stat.SPEED) / 256.0 > Math.random()) {
            System.out.println("Critical hit!");
            return 2.0;
        } else {
            return 1.0;
        }
    }

    @Override
    protected String describe() {
        return "использует Лезвие Ночи";
    }
}
