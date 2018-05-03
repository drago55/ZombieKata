import Items.Item;

import java.util.*;
import java.util.stream.Collectors;

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

    public Set<Item> getItemList() {
        return equipment.getItems().collect(Collectors.toSet());
    }

    public List<Item> getEquippedItems() {
        return this.inHand;
    }

    public void equip(Item item) {
        Optional<Item> optionalOfItem = getItemFromEquipment(item);
        if (optionalOfItem.isPresent()) {
            if (inHand.size() == 2) {
                unEquipFirst();
            }
            inHand.add(optionalOfItem.get());
        }
    }

    private void unEquipFirst() {
        if (!inHand.isEmpty()) {
            inHand.remove(0);
        }
    }

    public void unEquipAll() {
        inHand.clear();
    }

    private void unEquipLast() {
        if (!inHand.isEmpty()) {
            inHand.remove(1);
        }
    }

    private Optional<Item> getItemFromEquipment(Item itemToEquip) {
        return getItemList().stream().filter(item -> item == itemToEquip).findAny();
    }
}
