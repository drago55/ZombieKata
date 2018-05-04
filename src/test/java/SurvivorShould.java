import Items.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SurvivorShould {

    private Survivor bob;
    private Item sword;
    private Item bat;
    private Item pan;
    private Item pistol;
    private Item molotov;
    private Item water;

    @BeforeEach
    public void init() {
        //Given
        bob = new Survivor("Bob");
        //When
        sword = new Sword("Katana");
        bat = new BaseballBat("Strong baseball Bat");
        pan = new FryingPan("A pan");
        pistol = new Pistol("9mm");
        molotov = new Molotov("High explosive molotov");
        water = new BottledWater("Large water");
        bob.pickUpItem(sword);
        bob.pickUpItem(bat);
        bob.pickUpItem(pan);
        bob.pickUpItem(pistol);
        bob.pickUpItem(molotov);

    }


    @Test
    public void have_name() {
        //Given
        bob = new Survivor("Bob");
        //Then
        Assertions.assertEquals("Bob", bob.getName());
    }

    @Test
    public void have_zero_wounds() {
        //Given
        bob = new Survivor("Bob");
        //When
        int wounds = 0;
        //Then
        Assertions.assertEquals(wounds, bob.getWounds());
    }

    @Test
    public void received_two_wounds() {
        //Given
        bob = new Survivor("Bob");
        //When
        bob.receiveWound(3);
        //Then
        Assertions.assertTrue(bob.isDead());
    }

    @Test
    public void additional_wounds_are_ignored() {
        //Given
        bob = new Survivor("Bob");
        //When
        bob.receiveWound(2);
        bob.receiveWound(1);
        int wound = 2;
        //Then
        Assertions.assertEquals(wound, bob.getWounds());
    }

    @Test
    public void perform_three_action_per_turn() {
        //Given
        bob = new Survivor("Bob");
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
        bob = new Survivor("Bob");
        //When

        int remainingCapacity = 5;
        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void pickup_item_method_throws_exception() {
        //Given
        bob = new Survivor("Bob");
        //When
        bob.pickUpItem(sword);
        bob.pickUpItem(bat);
        bob.pickUpItem(pan);
        bob.pickUpItem(pistol);
        bob.pickUpItem(water);

        //Then
        assertThrows(IllegalStateException.class, () ->
                bob.pickUpItem(new Molotov("High explosive molotov")), "There is no more space");
    }

    @Test
    public void pickup_item() {
        //Given
        bob = new Survivor("Bob");
        //When
        bob.pickUpItem(sword);
        bob.pickUpItem(bat);
        bob.pickUpItem(pan);
        bob.pickUpItem(pistol);
        bob.pickUpItem(water);

        int remainingCapacity = 0;

        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void have_items_in_equipment() {

        Set<Item> expected = new HashSet<>();
        expected.add(sword);
        expected.add(bat);
        expected.add(pan);
        expected.add(pistol);
        expected.add(molotov);
        Assertions.assertEquals(expected, bob.getItemList());
    }

    @Test
    public void equip_in_hand_available_item() {

        bob.equip(sword);
        bob.equip(pistol);
        List<Item> expectedInHand = new ArrayList<>();
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
        List<Item> expectedEquippedItem = new ArrayList<>();
        expectedEquippedItem.add(bat);
        expectedEquippedItem.add(sword);
        Assertions.assertEquals(expectedEquippedItem, bob.getEquippedItems());

    }

    @Test
    public void wound_reduce_carrying_capacity() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When
        bob.receiveWound(1);
        int remainingCapacity = 4;
        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void reduced_capacity_cause_to_drop_item() {

        Set<Item> expectedItems = new HashSet<>();
        expectedItems.add(sword);
        expectedItems.add(bat);
        expectedItems.add(pan);
        expectedItems.add(pistol);

        bob.receiveWound(1);

        //Then
        Assertions.assertEquals(expectedItems, bob.getItemList());

    }

}
