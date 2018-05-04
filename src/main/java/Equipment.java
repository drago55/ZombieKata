import Items.Item;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Equipment {

    public static final int OUT_OF_SPACE = -1;
    private Item[] equipments;
    private int capacity = 5;

    public Equipment() {
        equipments = new Item[capacity];
    }

    public int getEquipmentFreeSlots() {
        int currentCapacity = 0;
        for (Item item : equipments) {
            if (item == null) {
                currentCapacity++;
            }
        }
        return currentCapacity;
    }

    public void addEquipment(Item item) {
        if (getNextAvailableIndex() == OUT_OF_SPACE) {
            throw new IllegalStateException("There is no more space");
        }
        this.equipments[getNextAvailableIndex()] = item;
    }

    private int getNextAvailableIndex() {
        return getEquipmentFreeSlots() == 0 ? OUT_OF_SPACE : getEquipmentFreeSlots() - 1;
    }

    public Stream<Item> getItems() {
        return Stream.of(equipments).filter((item) -> item != null);
    }

    public void reduceCapacityAndDropItem() {
        Set<Item> dropOne = getItems().skip(1).collect(Collectors.toSet());
        this.equipments = new Item[capacity -= 1];
        dropOne.toArray(equipments);
    }

}
