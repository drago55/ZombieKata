import levels.Level;
import levels.LevelSystem;
import levels.Levels;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LevelsShould {

    @Test
    public void have_condition_blue() {
        //When
        Level level = new LevelSystem();

        //Then
        Assertions.assertEquals(Levels.BLUE, level.getLevel(6));
        Assertions.assertEquals(Levels.BLUE, level.getLevel(0));
        Assertions.assertNotEquals(Levels.BLUE, level.getLevel(7));

    }

    @Test
    public void have_condition_yellow() {
        //When
        Level level = new LevelSystem();

        //Then
        Assertions.assertEquals(Levels.YELLOW, level.getLevel(7));
        Assertions.assertEquals(Levels.YELLOW, level.getLevel(18));
        Assertions.assertEquals(Levels.YELLOW, level.getLevel(12));
        Assertions.assertNotEquals(Levels.YELLOW, level.getLevel(19));

    }

    @Test
    public void have_condition_orange() {
        //When
        Level level = new LevelSystem();

        //Then
        Assertions.assertEquals(Levels.ORANGE, level.getLevel(19));
        Assertions.assertEquals(Levels.ORANGE, level.getLevel(33));
        Assertions.assertEquals(Levels.ORANGE, level.getLevel(42));
        Assertions.assertNotEquals(Levels.ORANGE, level.getLevel(18));
        Assertions.assertNotEquals(Levels.ORANGE, level.getLevel(43));

    }

    @Test
    public void have_condition_red() {
        //When
        Level level = new LevelSystem();

        //Then
        Assertions.assertEquals(Levels.RED, level.getLevel(43));
        Assertions.assertNotEquals(Levels.RED, level.getLevel(42));
        Assertions.assertNotEquals(Levels.RED, level.getLevel(33));

    }

}
