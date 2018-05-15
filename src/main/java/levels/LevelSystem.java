package levels;

public class LevelSystem implements Level {

    @Override
    public Levels getLevel(int experience) {
        if (experience > 6 && experience <= 18) {
            return Levels.YELLOW;
        } else if (experience > 18 && experience <= 42) {
            return Levels.ORANGE;
        } else if (experience > 42) {
            return Levels.RED;
        }
        return Levels.BLUE;
    }

    @Override
    public boolean isLevelUp(int experience) {
        switch (experience) {
            case 7:
                return true;
            case 19:
                return true;
            case 43:
                return true;
            default:
                return false;
        }
    }
}
