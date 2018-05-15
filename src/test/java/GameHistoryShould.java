import bag.EquipmentBag;
import equipment.Sword;
import game.Game;
import game.GameData;
import history.History;
import names.BasicNames;
import names.Names;
import names.SurvivorNames;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skills.SkillTree;
import survivors.Survivor;
import survivors.ZombieSurvivor;
import wounds.BasicWounds;
import zombies.BasicZombie;

import java.io.IOException;
import java.time.LocalTime;

import static names.StringConstants.*;

public class GameHistoryShould {

    private Game game;
    private Survivor survivor;
    private History<String> history;
    private Names names;

    @BeforeEach
    public void init() {
        names = new BasicNames(new SurvivorNames());
        game = new GameData(names);
        survivor = new ZombieSurvivor();
        game.addSurvivor(survivor);
        survivor.setGame(game);
        survivor.setSkillTree(new SkillTree(survivor, game));
        history = game.getGameHistory();
        survivor.setBag(new EquipmentBag());
        survivor.setWounds(new BasicWounds());
    }

    @AfterEach
    public void close() {
        history.clearLog();
    }

    @Test
    public void note_time_game_began() throws IOException {
        String expected = GAME_STARTED + LocalTime.now().withNano(0);
        Assert.assertEquals(expected, history.getLogLine(GAME_STARTED));
    }

    @Test
    public void note_that_survivor_has_added_to_game() throws IOException {
        String expected = ADDED_SURVIVOR + survivor.getName();
        Assert.assertEquals(expected, history.getLogLine(ADDED_SURVIVOR));
    }

    @Test
    public void note_survivor_acquired_equipment() throws IOException {
        survivor.pickUpEquipmentItem(new Sword("Katana"));
        String expected = SURVIVOR + survivor.getName() + ITEM_PICK_UP + "Katana";
        Assert.assertEquals(expected, history.getLogLine(ITEM_PICK_UP));
    }

    @Test
    public void note_survivor_is_wounded() throws IOException {
        survivor.receiveWound(new BasicWounds().setWounds(1));
        String expected = SURVIVOR + survivor.getName() + RECEIVED_WOUND + survivor.getWounds();
        Assert.assertEquals(expected, history.getLogLine(RECEIVED_WOUND));
    }

    @Test
    public void note_survivor_died() throws IOException {
        survivor.receiveWound(new BasicWounds().setWounds(2));
        String expected = SURVIVOR + survivor.getName() + IS_DEAD;
        Assert.assertEquals(expected, history.getLogLine(IS_DEAD));
    }

    @Test
    public void note_survivor_levels_up() throws IOException {

        for (int i = 0; i < 7; i++) {
            survivor.attack(new BasicZombie());
        }
        String expected = LEVEL_UP + SURVIVOR + survivor.getName() + IS_LEVEL + survivor.getCurrentLevel();
        Assertions.assertEquals(expected, history.getLogLine(LEVEL_UP));
    }

    @Test
    public void note_game_level_changes() throws IOException {
        for (int i = 0; i < 19; i++) {
            survivor.attack(new BasicZombie());
        }
        String expected = GAME_INCREASED_DIFFICULTY + game.getCurrentGameLevel();
        Assert.assertEquals(expected, history.getLogLine(GAME_INCREASED_DIFFICULTY + game.getCurrentGameLevel()));
    }

    @Test
    public void note_game_has_ended() throws IOException {
        game.killAllSurvivors();
        String expected = GAME_OVER;
        Assert.assertEquals(expected, history.getLogLine(GAME_OVER));
    }

    @Test
    public void note_survivor_has_acquired_new_skill() throws IOException {
        for (int i = 0; i < 19; i++) {
            survivor.attack(new BasicZombie());
        }
        String expected = survivor.getName() + NEW_SKILL + survivor.getSkillTree().getLastSkill();
        Assert.assertEquals(expected, history.getLogLine(survivor.getName() + NEW_SKILL + survivor.getSkillTree().getLastSkill()));
    }
}
