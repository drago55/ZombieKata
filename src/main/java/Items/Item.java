package Items;

import java.util.Objects;

public class Item{

    private String name;
    private boolean isEquipped = false;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void equipItem() {
        this.isEquipped = true;
    }

    public void reserve() {
        this.isEquipped = false;
    }


}
