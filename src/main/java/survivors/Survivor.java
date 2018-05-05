package survivors;

import bag.Bag;
import equipment.Equipment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Survivor {

    String getName();

    void setName(String name);

    void setBag(Bag bag);

    int getWounds();

    void receiveWound(int i);

    boolean isDead();

    void performAction();

    int getRemainingActions();

    int getEquipmentRemainingCapacity();

    void pickUpEquipmentItem(Equipment equipmentItem);

    Set<Equipment> getEquipmentList();

    List<Equipment> getEquippedItems();

    void equip(Equipment item);

    void unEquipFirst();

    Optional<Equipment> getItemFromEquipmentBag(Equipment equipmentItem);

    void kill();

}
