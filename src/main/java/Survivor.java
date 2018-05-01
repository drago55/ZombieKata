public class Survivor {

    private String name;
    private Wounds wounds;


    public Survivor(String name) {
        this.name = name;
        wounds = new Wounds();
    }

    public String getName() {
        return name;
    }

    public int getWounds() {
        return this.wounds.getWounds();
    }

    public void receiveWound(int i) {
        wounds.receive(i);
    }

    public boolean isDead() {
        return wounds.getWounds() == 2;
    }
}
