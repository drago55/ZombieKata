import bag.EquipmentBag;
import game.Game;
import game.GameData;
import levels.Levels;
import names.BasicNames;
import names.Names;
import names.SurvivorNames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import survivors.Survivor;
import survivors.ZombieSurvivor;
import wounds.BasicWounds;
import zombies.BasicZombie;

import java.util.Set;
import java.util.stream.Collectors;

public class GameShould {

    @Test
    public void begin_with_zero_survivors() {
        //Given
        Names names = new BasicNames(new SurvivorNames());
        Game game = new GameData(names);
        //When
        int containsSurvivors = 0;
        //Then
        Assertions.assertEquals(containsSurvivors, game.getSurvivorsCount());
    }

    @Test
    public void add_survivors() {
        //Given
        Names names = new BasicNames(new SurvivorNames());
        Game game = new GameData(names);
        //When
        game.addSurvivor(new ZombieSurvivor());
        int containsSurvivors = 1;
        //Then
        Assertions.assertEquals(containsSurvivors, game.getSurvivorsCount());
    }

    @Test
    public void survivors_names_are_unique() {
        //Given
        Names names = new BasicNames(new SurvivorNames());
        Game game = new GameData(names);
        //When
        for (int i = 0; i < 20; i++) {
            game.addSurvivor(new ZombieSurvivor());
        }
        Set<String> containsUniqueNames = game.getSurvivorsNames().stream().distinct().collect(Collectors.toSet());
        //Then
        Assertions.assertEquals(containsUniqueNames, game.getSurvivorsNames());
    }

    @Test
    public void game_ends_if_remaining_survivors_are_dead() {
        //Given
        Names names = new BasicNames(new SurvivorNames());
        Game game = new GameData(names);
        //When
        game.addSurvivor(new ZombieSurvivor(), new EquipmentBag(), new BasicWounds());
        game.addSurvivor(new ZombieSurvivor(), new EquipmentBag(), new BasicWounds());
        game.addSurvivor(new ZombieSurvivor(), new EquipmentBag(), new BasicWounds());

        game.killAllSurvivors();

        //Then
        Assertions.assertTrue(game.isEnded());
    }

    @Test
    public void begin_with_level_blue() {
        //Given
        Names names = new BasicNames(new SurvivorNames());
        Game game = new GameData(names);
        //Then
        Assertions.assertEquals(Levels.BLUE, game.getCurrentLevel());
    }

    @Test
    public void have_level_of_highest_living_survivor() {
        //Given
        Names names = new BasicNames(new SurvivorNames());
        Game game = new GameData(names);
        //When
        Survivor survivor = new ZombieSurvivor();
        survivor.setBag(new EquipmentBag());
        survivor.setWounds(new BasicWounds());
        game.addSurvivor(survivor);
        game.addSurvivor(new ZombieSurvivor(), new EquipmentBag(), new BasicWounds());
        for (int i = 0; i < 19; i++) {
            survivor.attack(new BasicZombie());
        }
        //Then
        Assertions.assertEquals(Levels.ORANGE, game.getCurrentLevel());

    }

}
