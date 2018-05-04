import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GameShould {

    @Test
    public void begin_with_zero_survivors() {
        //Given
        Game game = new Game();
        //When
        int containsSurvivors = 0;
        //Then
        Assertions.assertEquals(containsSurvivors, game.getSurvivorsCount());
    }

    @Test
    public void add_survivors() {
        //Given
        Game game = new Game();
        //When
        game.addSurvivor();
        int containsSurvivors = 1;
        //Then
        Assertions.assertEquals(containsSurvivors, game.getSurvivorsCount());
    }

    @Test
    public void survivors_names_are_unique() {
        //Given
        Game game = new Game();
        //When
        for (int i = 0; i < 20; i++) {
            game.addSurvivor();
        }
        Set<String> containsUniqueNames = game.getSurvivorsNames().stream().distinct().collect(Collectors.toSet());
        //Then
        Assertions.assertEquals(containsUniqueNames, game.getSurvivorsNames());
    }

    @Test
    public void game_ends_if_remaining_survivors_are_dead() {
        //Given
        Game game = new Game();
        //When
        game.addSurvivor();
        game.addSurvivor();
        game.addSurvivor();

        game.killAllSurvivors();

        //Then
        Assertions.assertTrue(game.isEnded());

    }
}
