import bag.EquipmentBag;
import equipment.*;
import game.Game;
import game.GameData;
import history.History;
import levels.Levels;
import names.BasicNames;
import names.Names;
import names.SurvivorNames;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skills.Skill;
import skills.SkillAttributes;
import skills.SkillTree;
import survivors.Survivor;
import survivors.ZombieSurvivor;
import wounds.BasicWounds;
import zombies.BasicZombie;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SkillTreeShould {

    private Survivor survivor;
    private Game game;
    private Equipment sword;
    private Equipment bat;
    private Equipment pan;
    private Equipment pistol;
    private Equipment molotov;
    private Equipment water;
    private Names names;
    private History history;

    @BeforeEach
    public void init() {
        //Given
        names = new BasicNames(new SurvivorNames());
        game = new GameData(names);
        survivor = new ZombieSurvivor();
        game.addSurvivor(survivor);
        survivor.setGame(game);
        survivor.setSkillTree(new SkillTree(survivor, game));
        survivor.setBag(new EquipmentBag());
        survivor.setWounds(new BasicWounds());

        //Given items
        sword = new Sword("Katana");
        bat = new BaseballBat("Strong baseball Bat");
        pan = new FryingPan("A pan");
        pistol = new Pistol("9mm");
        molotov = new Molotov("High explosive molotov");
        water = new BottledWater("Large water");

    }

    @AfterEach
    public void cleanUp() {
        history = game.getGameHistory();
        history.clearLog();
    }

    @Test
    public void have_potential_skills() {
        //We have available skills but they are not unlocked
        Assertions.assertEquals(Arrays.stream(SkillAttributes.values()).collect(Collectors.toSet()), survivor.getSkillTree().getListOfSkills());
    }

    @Test
    public void have_unlocked_skill_action() {

        for (int i = 0; i < 7; i++) {
            survivor.attack(new BasicZombie());
        }
        Assertions.assertEquals(SkillAttributes.ACTION, survivor.getSkillTree().getLastSkill());
    }

    @Test
    public void have_one_potential_skill_at_level_yellow() {

        for (int i = 0; i < 7; i++) {
            survivor.attack(new BasicZombie());
        }

        int expectedNumberOfSkills = 1;
        Assertions.assertEquals(expectedNumberOfSkills, survivor.getSkillTree().getUnlockedSkills().size());

    }

    @Test
    public void have_two_potential_skill_at_level_orange() {
        for (int i = 0; i < 19; i++) {
            survivor.attack(new BasicZombie());
        }
        int expectedNumberOfSkills = 2;
        Assertions.assertEquals(expectedNumberOfSkills, survivor.getSkillTree().getUnlockedSkills().size());
    }

    @Test
    public void have_tree_potential_skill_at_level_red() {
        for (int i = 0; i < 129; i++) {
            survivor.attack(new BasicZombie());
        }
        Assertions.assertTrue(survivor.getSkillTree().getUnlockedSkills().contains(SkillAttributes.SNIPER));
        Assertions.assertTrue(survivor.getSkillTree().getUnlockedSkills().contains(SkillAttributes.TOUGH));
        Assertions.assertTrue(survivor.getSkillTree().getUnlockedSkills().contains(SkillAttributes.ONE_MELEE));
    }


    @Test
    public void have_one_action_skill_at_level_yellow_after_skill_tree_restart() {
        for (int i = 0; i < 50; i++) {
            survivor.attack(new BasicZombie());
        }
        int expectedNumberOfSkills = 1;
        Assertions.assertEquals(expectedNumberOfSkills, survivor.getSkillTree().getUnlockedSkills().stream()
                .filter((skillAttributes) -> skillAttributes.equals(SkillAttributes.ACTION))
                .count());
    }

    @Test
    public void start_without_skills() {
        Assert.assertEquals(SkillAttributes.EMPTY, survivor.getSkillTree().getLastSkill());
    }

    @Test
    public void update_survivor_number_of_actions() {
        //+1 Action" should have one additional Action (a total of 4).
        for (int i = 0; i < 50; i++) {
            survivor.attack(new BasicZombie());
        }
        int expectedNumberOfActions = 4;
        Assert.assertEquals(expectedNumberOfActions, survivor.getAction().getAvailableActions());
    }

    @Test
    public void unlock_skill_hoard() {
        //skill can carry one additional piece of Equipment
        //Hoard is unlocked on ORANGE level one
        for (int i = 0; i < 19; i++) {
            survivor.attack(new BasicZombie());
        }
        Assert.assertTrue(survivor.getSkillTree().getUnlockedSkills().contains(SkillAttributes.HOARD));
    }

    @Test
    public void not_delete_equipment_while_increasing_slots() {
        Equipment sword = new Sword("Katana");
        survivor.pickUpEquipmentItem(sword);
        for (int i = 0; i < 19; i++) {
            survivor.attack(new BasicZombie());
        }
        Assert.assertTrue(survivor.getBagItems().contains(sword));
    }

    @Test
    public void unlock_one_additional_equipment_slot() {
        //skill can carry one additional piece of Equipment
        //Hoard is unlocked on ORANGE level one
        for (int i = 0; i < 19; i++) {
            survivor.attack(new BasicZombie());
        }
        int expectedCarryingCapacity = 6;
        Assert.assertEquals(expectedCarryingCapacity, survivor.getEquipmentRemainingCapacity());
    }

    @Test
    public void survivor_restarts_skill_tree() {
        //beyond 43 experience skill tree starts from beginning but remain current unlocked skills
        for (int i = 0; i < 44; i++) {
            survivor.attack(new BasicZombie());
        }
        Assertions.assertEquals(Levels.BLUE, survivor.getSkillTree().getCurrentLevel());
    }

    @Test
    public void reaching_yellow_again() {
        //43+7=50 experience no more potential skills at second yellow
        for (int i = 0; i < 51; i++) {
            survivor.attack(new BasicZombie());
        }
        //Should have skill action
        Assert.assertTrue(survivor.getSkillTree().getUnlockedSkills().contains(SkillAttributes.ACTION));
        //Have only one additional action total of 4
        Assertions.assertEquals(4, survivor.getAction().getAvailableActions());
        //total 3 skills
        Assertions.assertEquals(3, survivor.getSkillTree().getUnlockedSkills().size());
    }

    @Test
    public void unlocked_orange_again() {
        //43+18=61 experience second orange skill is unlocked
        for (int i = 0; i < 62; i++) {
            survivor.attack(new BasicZombie());
        }
        Assert.assertTrue(survivor.getSkillTree()
                .getUnlockedSkills().contains(SkillAttributes.FREE_MOVE));
    }

    @Test
    public void unlocked_red_first_time() {
        //43  = 43 total experience
        for (int i = 0; i < 43; i++) {
            survivor.attack(new BasicZombie());
        }
        Assert.assertTrue(survivor.getSkillTree().getUnlockedSkills().contains(SkillAttributes.ONE_MELEE));
    }

    @Test
    public void unlocked_red_second_time() {
        //43 + 43 = 86 total
        for (int i = 1; i < 87; i++) {
            survivor.attack(new BasicZombie());
        }
        survivor.getSkillTree().getUnlockedSkills().stream().forEach(System.out::println);
        Assert.assertTrue(survivor.getSkillTree().getUnlockedSkills().contains(SkillAttributes.SNIPER));
    }

    @Test
    public void unlocked_red_last_time() {
        //43 + 43 + 43 = 129 total
        for (int i = 0; i < 129; i++) {
            survivor.attack(new BasicZombie());
        }
        Assert.assertTrue(survivor.getSkillTree().getUnlockedSkills().contains(SkillAttributes.TOUGH));
    }
}
