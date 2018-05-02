import Items.Item;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Equipment {

    public static final int OUT_OF_SPACE = -1;
    private Item[] equipments;
    private int capacity = 5;

    public Equipment() {
        equipments = new Item[capacity];
    }

    public int getCurrentCapacity() {
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
        return getCurrentCapacity() == 0 ? OUT_OF_SPACE : getCurrentCapacity() - 1;
    }

    public Set<Item> getItems() {
        return Arrays.asList(equipments).stream().filter((item) -> item != null).collect(Collectors.toSet());
    }
}
