package levels;

public interface Level {

    Levels getLevel(int experience);

    boolean isLevelUp(int experience);

}
