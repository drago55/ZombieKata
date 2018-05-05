package wounds;

public class BasicWounds implements Wounds {

    private int wounds = 0;

    public int getWounds() {
        return wounds;
    }

    public BasicWounds(int damage) {
        setWound(damage);
    }

    public BasicWounds() {
    }

    public void setWound(int damage) {
        wounds = wounds + damage >= 2 ? 2 : wounds + damage;
    }
}
