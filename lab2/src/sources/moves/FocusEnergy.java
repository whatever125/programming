package sources.moves;
import ru.ifmo.se.pokemon.*;

public class FocusEnergy extends StatusMove {
    private boolean used = false;

    public FocusEnergy() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        if (!this.used) {
            p.setMod(Stat.ACCURACY, 2);
            this.used = true;
        }
    }

    @Override
    protected String describe() {
        if (this.used) {
            return "не может использовать Фокусировку";
        }
        return "использует Фокусировку";
    }
}
