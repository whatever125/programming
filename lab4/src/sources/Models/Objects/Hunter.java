package sources.Models.Objects;

import sources.Models.Abstracts.Creature;
import sources.Models.Abstracts.Thing;

public class Hunter extends Malysh {
    private Gun gun;
    private Dog dog;

    public Hunter(String name, String gun_type) {
        super(name);
        gun = new Gun();
        gun.setBullet_type(gun_type);
        gun.setOwner(this);
    }

    public static class Gun extends Thing {
        private Creature owner;
        private String bullet_type;

        public Gun() {
            super("Ружье");
        }

        public Creature getOwner() {
            return owner;
        }

        public void setOwner(Creature owner) {
            this.owner = owner;
        }

        public String getBullet_type() {
            return bullet_type;
        }

        public void setBullet_type(String bullet_type) {
            this.bullet_type = bullet_type;
        }

        @Override
        public void action(String name) {
            System.out.println("Ружье стреляет");
        }
    }

    public Gun getGun() {
        return gun;
    }

    public void setGun(String gun_type) {
        this.gun = new Gun();
        this.gun.setBullet_type(gun_type);
        this.gun.setOwner(this);
        System.out.println("Охотник " + getName() + " получил новое ружье");
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
        System.out.println("Охотник " + getName() + " получил новую собаку " + dog.getName());
    }
}
