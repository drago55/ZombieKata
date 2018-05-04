package survivors;

import Items.Item;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Survivor {

    String getName();

    void setName(String name);

    int getWounds();

    void receiveWound(int i);

    boolean isDead();

    void performAction();

    int getRemainingActions();

    int getEquipmentRemainingCapacity();

    void pickUpItem(Item item);

    Set<Item> getItemList();

    List<Item> getEquippedItems();

    void equip(Item item);

    void unEquipFirst();

    Optional<Item> getItemFromEquipment(Item itemToEquip);

    void kill();

}
