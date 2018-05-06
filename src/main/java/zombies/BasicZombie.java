package zombies;

import survivors.Survivor;
import wounds.BasicWounds;
import wounds.Wounds;

public class BasicZombie implements Zombie {

    private Wounds wounds;

    @Override
    public void attack(Survivor survivor) {
        survivor.receiveWound(new BasicWounds().setWounds(1));
    }

    @Override
    public void receiveDamage(Wounds wounds) {
        this.wounds = wounds;
    }

    @Override
    public boolean isDead() {
        return wounds.getWounds() == 2;
    }

}
