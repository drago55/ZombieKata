package survivors;

import Items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ZombieSurvivor implements Survivor {
    private String name;
    private Wounds wounds;
    private static Actions actions;
    private Equipment equipment;
    private List<Item> inHand;



    public ZombieSurvivor() {
        wounds = new Wounds();
        actions = Actions.getInstance();
        equipment = new Equipment();
        inHand = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getWounds() {
        return this.wounds.getWounds();
    }

    @Override
    public void receiveWound(int i) {
        wounds.receive(i);
        equipment.reduceCapacityAndDropItem();
    }

    @Override
    public boolean isDead() {
        return wounds.getWounds() == 2;
    }

    @Override
    public void performAction() {
        this.actions.doAction();
    }

    @Override
    public int getRemainingActions() {
        return actions.getRemaining();
    }

    @Override
    public int getEquipmentRemainingCapacity() {
        return equipment.getEquipmentFreeSlots();
    }

    @Override
    public void pickUpItem(Item item) {
        equipment.addEquipment(item);

    }

    @Override
    public Set<Item> getItemList() {
        return equipment.getItems().collect(Collectors.toSet());
    }

    @Override
    public List<Item> getEquippedItems() {
        return this.inHand;
    }

    @Override
    public void equip(Item item) {
        Optional<Item> optionalOfItem = getItemFromEquipment(item);
        if (optionalOfItem.isPresent()) {
            if (inHand.size() == 2) {
                unEquipFirst();
            }
            inHand.add(optionalOfItem.get());
        }
    }

    @Override
    public void unEquipFirst() {
        if (!inHand.isEmpty()) {
            inHand.remove(0);
        }
    }

    @Override
    public Optional<Item> getItemFromEquipment(Item itemToEquip) {
        return getItemList().stream().filter(item -> item == itemToEquip).findAny();
    }

    @Override
    public void kill() {
        this.wounds.receive(2);
    }
}
