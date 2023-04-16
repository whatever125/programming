package sources.Models.Interfaces;

public interface DamageInterface {
    void getDamage(String damage);
    void applyDamage(DamageInterface target, String damage);
    void getSightDamage(String damage);
    void applySightDamage(DamageInterface target, String damage);
}
