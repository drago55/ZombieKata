package zombies;

import survivors.Survivor;
import wounds.Wounds;

public interface Zombie {

    void attack(Survivor survivor);

    void receiveDamage(Wounds wounds);

    boolean isDead();
}
