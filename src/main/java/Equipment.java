import java.util.Vector;

public class Equipment {

    private Vector<Item> equipments;

    public Equipment() {
        equipments = new Vector<>(5);
    }

    public int getCapacity() throws ClassCastException {
        return equipments.capacity();
    }
}
