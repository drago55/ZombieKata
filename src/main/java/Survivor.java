public class Survivor {

    private String name;
    private Wounds wounds;
    private static Actions actions;


    public Survivor(String name) {
        this.name = name;
        wounds = new Wounds();
        actions = Actions.getInstance();
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

    public void performAction() {
        this.actions.doAction();
    }

    public int getRemainingActions() {
        return actions.getRemaining();
    }

    public int getEquipmentRemainingCapacity() {
        return equipmentRemainingCapacity;
    }
}
