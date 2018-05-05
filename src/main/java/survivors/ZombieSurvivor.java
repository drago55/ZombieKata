package survivors;

import bag.Bag;
import wounds.Wounds;
import equipment.Equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ZombieSurvivor implements Survivor {
    private String name;
    private int experience;
    private Wounds wounds;
    private static Actions actions;
    private Bag bag;
    private List<Equipment> inHand;


    public ZombieSurvivor() {
        actions = Actions.getInstance();
        inHand = new ArrayList<>();
    }

    @Override
    public void setBag(Bag bag) {
        this.bag = bag;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getExperience() {
        return this.experience;
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
    public void setWounds(Wounds wounds) {
        this.wounds = wounds;
    }

    @Override
    public void receiveWound(Wounds wounds) {
        this.wounds = wounds;
        bag.reduceCapacityAndDropItem();
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
        return bag.getBagFreeSlots();
    }

    @Override
    public void pickUpEquipmentItem(Equipment equipmentItem) {
        this.bag.addEquipment(equipmentItem);

    }

    @Override
    public Set<Equipment> getEquipmentList() {
        return bag.getItems().collect(Collectors.toSet());
    }

    @Override
    public List<Equipment> getEquippedItems() {
        return this.inHand;
    }

    @Override
    public void equip(Equipment item) {
        Optional<Equipment> optionalOfItem = getItemFromEquipmentBag(item);
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
    public Optional<Equipment> getItemFromEquipmentBag(Equipment itemToEquip) {
        return getEquipmentList().stream().filter(item -> item == itemToEquip).findAny();
    }

    @Override
    public void kill() {
        this.wounds.setWound(2);
    }
}
