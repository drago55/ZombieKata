import Items.Item;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Survivor {

    private String name;
    private Wounds wounds;
    private static Actions actions;
    private Equipment equipment;


    public Survivor(String name) {
        this.name = name;
        wounds = new Wounds();
        actions = Actions.getInstance();
        equipment = new Equipment();
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

    public Set<Item> getEquippedItems() {
        return equipment.getEquippedItems().collect(Collectors.toSet());

    }

    public void equip(Item item) {
        Optional<Item> optionalOfItem = getItemFromEquipment(item);
        if (optionalOfItem.isPresent()) {


            optionalOfItem.get().equipItem();
        }
    }

    private Optional<Item> getItemFromEquipment(Item itemToEquip) {
        return equipment.getItems().filter(item -> item == itemToEquip && !itemToEquip.isEquipped()).findAny();
    }
}
