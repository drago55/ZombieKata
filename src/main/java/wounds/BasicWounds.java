package wounds;

public class BasicWounds implements Wounds {

    private int wounds = 0;

    public int getWounds() {
        return wounds;
    }


    public BasicWounds() {
    }

    public Wounds setWounds(int wounds) {
        this.wounds = this.wounds + wounds >= 2 ? 2 : this.wounds + wounds;
        return this;
    }
}
