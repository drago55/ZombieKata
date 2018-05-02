import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class Survivor {

    private String name;
    private Wounds wounds;
    private static Actions actions;
    private Equipment equipment;
    private List<Item> inHand;


    public Survivor(String name) {
        this.name = name;
        wounds = new Wounds();
        actions = Actions.getInstance();
        equipment = new Equipment();
        inHand = new ArrayList<>();
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
        return equipment.getCurrentCapacity();
    }

    public void pickUpItem(Item item) {
        equipment.addEquipment(item);
    }
}
