import bag.EquipmentBag;
import equipment.*;
import game.Game;
import game.GameData;
import levels.Levels;
import names.BasicNames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wounds.BasicWounds;
import survivors.Survivor;
import survivors.ZombieSurvivor;
import wounds.Wounds;
import zombies.BasicZombie;
import zombies.Zombie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SurvivorShould {

    private Survivor survivor;
    private Game game;
    private Equipment sword;
    private Equipment bat;
    private Equipment pan;
    private Equipment pistol;
    private Equipment molotov;
    private Equipment water;

    @BeforeEach
    public void init() {
        //Given
        setSurvivor();
        setGame();

        //When
        sword = new Sword("Katana");
        bat = new BaseballBat("Strong baseball Bat");
        pan = new FryingPan("A pan");
        pistol = new Pistol("9mm");
        molotov = new Molotov("High explosive molotov");
        water = new BottledWater("Large water");
        survivor.pickUpEquipmentItem(sword);
        survivor.pickUpEquipmentItem(bat);
        survivor.pickUpEquipmentItem(pan);
        survivor.pickUpEquipmentItem(pistol);
        survivor.pickUpEquipmentItem(molotov);
    }

    private void setGame() {
        game = new GameData(new BasicNames());
        game.addSurvivor(survivor);
        survivor.setGame(game);
    }

    @Test
    public void have_name() {
        //Given
        Game game = new GameData(new BasicNames());
        survivor = new ZombieSurvivor();

        game.addSurvivor(survivor);


        //Then
        Assertions.assertEquals(survivor.getName(), survivor.getName());
    }

    @Test
    public void have_zero_wounds() {
        //Given
        setSurvivor();
        survivor.setGame(game);
        survivor.receiveWound(new BasicWounds());
        //When
        int wounds = 0;
        //Then
        Assertions.assertEquals(wounds, survivor.getWounds());
    }

    @Test
    public void received_two_wounds() {
        //Given
        setSurvivor();
        game.addSurvivor(survivor);
        survivor.setGame(game);
        //When
        survivor.receiveWound(new BasicWounds().setWounds(3));
        //Then
        System.out.println("survivor has amount of wounds " + survivor.getWounds());
        Assertions.assertTrue(survivor.isDead());
    }

    @Test
    public void additional_wounds_are_ignored() {
        //Given
        Wounds wound = new BasicWounds();
        wound.setWounds(2);
        //When
        survivor.receiveWound(wound);
        wound.setWounds(1);
        survivor.receiveWound(wound);
        int expectedWound = 2;
        //Then
        Assertions.assertEquals(expectedWound, survivor.getWounds());
    }

    @Test
    public void perform_three_action_per_turn() {
        //Given

        survivor.performAction();
        survivor.performAction();
        survivor.performAction();

        int remainingActions = 0;
        //Then
        Assertions.assertEquals(remainingActions, survivor.getRemainingActions());
    }

    @Test
    public void carry_equipment_with_capacity_of_five() {
        //Given
        setSurvivor();
        //When

        int remainingCapacity = 5;
        //Then
        Assertions.assertEquals(remainingCapacity, survivor.getEquipmentRemainingCapacity());
    }

    @Test
    public void pickup_item_method_throws_exception() {
        //Given
        setSurvivor();
        game.addSurvivor(survivor);
        //When
        survivor.pickUpEquipmentItem(sword);
        survivor.pickUpEquipmentItem(bat);
        survivor.pickUpEquipmentItem(pan);
        survivor.pickUpEquipmentItem(pistol);
        survivor.pickUpEquipmentItem(water);

        //Then
        assertThrows(IllegalStateException.class, () ->
                survivor.pickUpEquipmentItem(new Molotov("High explosive molotov")), "There is no more space");
    }

    @Test
    public void pickup_item() {
        //Given
        setSurvivor();
        setGame();
        //When
        survivor.pickUpEquipmentItem(sword);
        survivor.pickUpEquipmentItem(bat);
        survivor.pickUpEquipmentItem(pan);
        survivor.pickUpEquipmentItem(pistol);
        survivor.pickUpEquipmentItem(water);

        int remainingCapacity = 0;

        //Then
        Assertions.assertEquals(remainingCapacity, survivor.getEquipmentRemainingCapacity());
    }

    @Test
    public void have_items_in_equipment() {

        Set<Equipment> expected = new HashSet<>();
        expected.add(sword);
        expected.add(bat);
        expected.add(pan);
        expected.add(pistol);
        expected.add(molotov);
        Assertions.assertEquals(expected, survivor.getEquipmentList());
    }

    @Test
    public void equip_in_hand_available_item() {

        survivor.equip(sword);
        survivor.equip(pistol);
        List<Equipment> expectedInHand = new ArrayList<>();
        expectedInHand.add(sword);
        expectedInHand.add(pistol);
        //Then
        Assertions.assertEquals(expectedInHand, survivor.getEquippedItems());
    }

    @Test
    public void equip_only_two_items() {

        survivor.equip(pistol);
        survivor.equip(molotov);
        survivor.equip(molotov);
        survivor.equip(pistol);
        survivor.equip(bat);
        survivor.equip(sword);
        List<Equipment> expectedEquippedItem = new ArrayList<>();
        expectedEquippedItem.add(bat);
        expectedEquippedItem.add(sword);
        Assertions.assertEquals(expectedEquippedItem, survivor.getEquippedItems());

    }

    @Test
    public void wound_reduce_carrying_capacity() {
        //Given
        setSurvivor();
        game.addSurvivor(survivor);
        survivor.setGame(game);
        //When
        survivor.receiveWound(new BasicWounds().setWounds(1));
        int remainingCapacity = 4;
        //Then
        Assertions.assertEquals(remainingCapacity, survivor.getEquipmentRemainingCapacity());
    }

    @Test
    public void reduced_capacity_cause_to_drop_item() {

        Set<Equipment> expectedItems = new HashSet<>();
        expectedItems.add(sword);
        expectedItems.add(bat);
        expectedItems.add(pan);
        expectedItems.add(pistol);

        survivor.receiveWound(new BasicWounds().setWounds(1));

        //Then
        Assertions.assertEquals(expectedItems, survivor.getEquipmentList());
    }

    @Test
    public void start_with_zero_experience() {
        //Given
        setSurvivor();
        //When
        int expectedExperience = 0;
        //Then
        Assertions.assertEquals(expectedExperience, survivor.getExperience());
    }

    @Test
    public void have_current_level() {
        //Given
        Wounds wound = new BasicWounds();

        setSurvivor();
        setGame();
        survivor.setWounds(wound);
        //When
        for (int i = 0; i < 43; i++) {
            survivor.attack(new BasicZombie());
        }
        //Then
        Assertions.assertEquals(Levels.RED, survivor.getCurrentLevel());

    }

    @Test
    public void starts_with_level_blue() {
        //Given
        setSurvivor();
        game.addSurvivor(survivor);
        survivor.setGame(game);
        //When

        //Then
        Assertions.assertEquals(Levels.BLUE, survivor.getCurrentLevel());
    }

    @Test
    public void gain_one_experience_on_killed_zombie() {
        //Given
        setSurvivor();
        Zombie zombie = new BasicZombie();
        //When
        survivor.attack(zombie);
        int expectedExperience = 1;
        //Then
        Assertions.assertEquals(expectedExperience, survivor.getExperience());
    }

    private void setSurvivor() {
        survivor = new ZombieSurvivor();
        survivor.setBag(new EquipmentBag());
    }

}
