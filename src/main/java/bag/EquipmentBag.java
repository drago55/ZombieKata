package bag;

import equipment.Equipment;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EquipmentBag implements Bag {

    public static final int OUT_OF_SPACE = -1;
    private Equipment[] equipments;
    private int capacity = 5;

    public EquipmentBag() {
        equipments = new Equipment[capacity];
    }

    @Override
    public int getBagFreeSlots() {
        int currentCapacity = 0;
        for (Equipment item : equipments) {
            if (item == null) {
                currentCapacity++;
            }
        }
        return currentCapacity;
    }

    @Override
    public void addEquipment(Equipment item) {
        if (getNextAvailableIndex() == OUT_OF_SPACE) {
            throw new IllegalStateException("There is no more space");
        }
        this.equipments[getNextAvailableIndex()] = item;
    }

    private int getNextAvailableIndex() {
        return getBagFreeSlots() == 0 ? OUT_OF_SPACE : getBagFreeSlots() - 1;
    }

    @Override
    public Stream<Equipment> getItems() {
        return Stream.of(equipments).filter((item) -> item != null);
    }

    @Override
    public void reduceCapacityAndDropItem() {
        Set<Equipment> dropOne = getItems().skip(1).collect(Collectors.toSet());
        this.equipments = new Equipment[capacity -= 1];
        dropOne.toArray(equipments);
    }

    @Override
    public void addExtraSlot() {
        Set<Equipment> addExtraSlot = getItems().collect(Collectors.toSet());
        this.equipments = new Equipment[capacity += 1];
        addExtraSlot.toArray(equipments);
    }

}
