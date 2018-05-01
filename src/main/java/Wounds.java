public class Wounds {

    private int wounds = 0;

    public int getWounds() {
        return wounds;
    }

    public void receive(int damage) {
        wounds = wounds+damage >= 2 ? 2 : wounds + damage;
    }
}
