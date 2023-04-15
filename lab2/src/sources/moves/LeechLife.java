package sources.moves;
import ru.ifmo.se.pokemon.*;

public class LeechLife extends PhysicalMove {
    double damage;
    public LeechLife() {
        super(Type.BUG, 80, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon p, double damage) {
        super.applyOppDamage(p, damage);
        this.damage = damage;
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.HP, (int) (-0.5 * this.damage));
    }

    @Override
    protected String describe() {
        return "использует атаку Кровосос";
    }
}
