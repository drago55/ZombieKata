package bag;

import equipment.Equipment;

import java.util.stream.Stream;

public interface Bag {
    int getBagFreeSlots();

    void addEquipment(Equipment item);

    Stream<Equipment> getItems();

    void reduceCapacityAndDropItem();
}
