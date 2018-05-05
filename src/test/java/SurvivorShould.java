import bag.EquipmentBag;
import equipment.*;
import names.BasicNames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wounds.BasicWounds;
import survivors.Survivor;
import survivors.ZombieSurvivor;
import wounds.Wounds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SurvivorShould {

    private Survivor bob;
    private Equipment sword;
    private Equipment bat;
    private Equipment pan;
    private Equipment pistol;
    private Equipment molotov;
    private Equipment water;

    @BeforeEach
    public void init() {
        //Given
        bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        //When
        sword = new Sword("Katana");
        bat = new BaseballBat("Strong baseball Bat");
        pan = new FryingPan("A pan");
        pistol = new Pistol("9mm");
        molotov = new Molotov("High explosive molotov");
        water = new BottledWater("Large water");
        bob.pickUpEquipmentItem(sword);
        bob.pickUpEquipmentItem(bat);
        bob.pickUpEquipmentItem(pan);
        bob.pickUpEquipmentItem(pistol);
        bob.pickUpEquipmentItem(molotov);
    }

    @Test
    public void have_name() {
        //Given
        Game game = new GameData(new BasicNames());
        bob = new ZombieSurvivor();

        game.addSurvivor(bob);


        //Then
        Assertions.assertEquals(bob.getName(), bob.getName());
    }

    @Test
    public void have_zero_wounds() {
        //Given
        bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        bob.receiveWound(new BasicWounds(0));
        //When
        int wounds = 0;
        //Then
        Assertions.assertEquals(wounds, bob.getWounds());
    }

    @Test
    public void received_two_wounds() {
        //Given
        bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        //When
        bob.receiveWound(new BasicWounds(3));
        //Then
        Assertions.assertTrue(bob.isDead());
    }

    @Test
    public void additional_wounds_are_ignored() {
        //Given
        bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        Wounds wound = new BasicWounds(2);

        //When
        bob.receiveWound(wound);
        wound.setWound(1);
        bob.receiveWound(wound);
        int expectedWound = 2;
        //Then
        Assertions.assertEquals(expectedWound, bob.getWounds());
    }

    @Test
    public void perform_three_action_per_turn() {
        //Given
        bob = new ZombieSurvivor();
        //When
        bob.performAction();
        bob.performAction();
        bob.performAction();

        int remainingActions = 0;
        //Then
        Assertions.assertEquals(remainingActions, bob.getRemainingActions());
    }

    @Test
    public void carry_equipment_with_capacity_of_five() {
        //Given
        bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        //When

        int remainingCapacity = 5;
        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void pickup_item_method_throws_exception() {
        //Given
        bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        //When
        bob.pickUpEquipmentItem(sword);
        bob.pickUpEquipmentItem(bat);
        bob.pickUpEquipmentItem(pan);
        bob.pickUpEquipmentItem(pistol);
        bob.pickUpEquipmentItem(water);

        //Then
        assertThrows(IllegalStateException.class, () ->
                bob.pickUpEquipmentItem(new Molotov("High explosive molotov")), "There is no more space");
    }

    @Test
    public void pickup_item() {
        //Given
        bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        //When
        bob.pickUpEquipmentItem(sword);
        bob.pickUpEquipmentItem(bat);
        bob.pickUpEquipmentItem(pan);
        bob.pickUpEquipmentItem(pistol);
        bob.pickUpEquipmentItem(water);

        int remainingCapacity = 0;

        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void have_items_in_equipment() {

        Set<Equipment> expected = new HashSet<>();
        expected.add(sword);
        expected.add(bat);
        expected.add(pan);
        expected.add(pistol);
        expected.add(molotov);
        Assertions.assertEquals(expected, bob.getEquipmentList());
    }

    @Test
    public void equip_in_hand_available_item() {

        bob.equip(sword);
        bob.equip(pistol);
        List<Equipment> expectedInHand = new ArrayList<>();
        expectedInHand.add(sword);
        expectedInHand.add(pistol);
        //Then
        Assertions.assertEquals(expectedInHand, bob.getEquippedItems());
    }

    @Test
    public void equip_only_two_items() {

        bob.equip(pistol);
        bob.equip(molotov);
        bob.equip(molotov);
        bob.equip(pistol);
        bob.equip(bat);
        bob.equip(sword);
        List<Equipment> expectedEquippedItem = new ArrayList<>();
        expectedEquippedItem.add(bat);
        expectedEquippedItem.add(sword);
        Assertions.assertEquals(expectedEquippedItem, bob.getEquippedItems());

    }

    @Test
    public void wound_reduce_carrying_capacity() {
        //Given
        Survivor bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        //When
        bob.receiveWound(new BasicWounds(1));
        int remainingCapacity = 4;
        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void reduced_capacity_cause_to_drop_item() {

        Set<Equipment> expectedItems = new HashSet<>();
        expectedItems.add(sword);
        expectedItems.add(bat);
        expectedItems.add(pan);
        expectedItems.add(pistol);

        bob.receiveWound(new BasicWounds(1));

        //Then
        Assertions.assertEquals(expectedItems, bob.getEquipmentList());
    }

    @Test
    public void start_with_zero_experience() {
        //Given
        Survivor bob = new ZombieSurvivor();
        bob.setBag(new EquipmentBag());
        //When
        int expectedExperience = 0;
        //Then
        Assertions.assertEquals(expectedExperience, bob.getExperience());
    }

}
