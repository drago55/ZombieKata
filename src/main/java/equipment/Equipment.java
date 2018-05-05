package equipment;

import java.util.Objects;

public abstract class Equipment {

    private String name;

    public Equipment(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment item = (Equipment) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

}
