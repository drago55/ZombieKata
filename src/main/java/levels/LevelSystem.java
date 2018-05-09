package levels;

import game.Game;
import survivors.Survivor;

public class LevelSystem implements Level {

    private Survivor survivor;
    private Game game;

    public LevelSystem() {
    }

    public LevelSystem(Survivor survivor) {
        this.survivor = survivor;
    }

    public LevelSystem(Game game) {
        this.game = game;
    }

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
