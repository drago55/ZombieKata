import Items.Item;

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
        System.out.println("------------------------------");
        System.out.println("Capacity of array is " + capacity);
        System.out.println("Capacity is " + getCurrentCapacity());
        System.out.println("Next available index " + getNextAvailableIndex());
        System.out.println("------------------------------");

        if (getNextAvailableIndex() == OUT_OF_SPACE) {
            throw new IllegalStateException("There is no more space");
        }
        this.equipments[getNextAvailableIndex()] = item;
    }

    private int getNextAvailableIndex() {
        return getCurrentCapacity() == 0 ? OUT_OF_SPACE : getCurrentCapacity() - 1;
    }
}
