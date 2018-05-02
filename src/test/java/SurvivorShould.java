import Items.*;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;

        import java.util.HashSet;
        import java.util.Set;

        import static org.junit.jupiter.api.Assertions.assertThrows;

public class SurvivorShould {


    @Test
    public void have_name() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When

        //Then
        Assertions.assertEquals("Bob", bob.getName());
    }

    @Test
    public void have_zero_wounds() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When
        int wounds = 0;
        //Then
        Assertions.assertEquals(wounds, bob.getWounds());
    }

    @Test
    public void received_two_wounds() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When
        bob.receiveWound(3);
        //Then
        Assertions.assertTrue(bob.isDead());
    }

    @Test
    public void additional_wounds_are_ignored() {
        //Given
        Survivor bob = new Survivor("Bob");
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
        Survivor bob = new Survivor("Bob");
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
        Survivor bob = new Survivor("Bob");
        //When

        int remainingCapacity = 5;
        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void pickup_item_method_throws_exception() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When
        bob.pickUpItem(new Sword("Katana"));
        bob.pickUpItem(new BaseballBat("Strong baseball Bat"));
        bob.pickUpItem(new FryingPan("A pan"));
        bob.pickUpItem(new Pistol("9mm"));
        bob.pickUpItem(new BottledWater("Large water"));

        //Then
        assertThrows(IllegalStateException.class, () ->
                bob.pickUpItem(new Molotov("High explosive molotov")), "There is no more space");
    }

    @Test
    public void pickup_item() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When
        bob.pickUpItem(new Sword("Katana"));
        bob.pickUpItem(new BaseballBat("Strong baseball Bat"));
        bob.pickUpItem(new FryingPan("A pan"));
        bob.pickUpItem(new Pistol("9mm"));
        bob.pickUpItem(new BottledWater("Large water"));

        int remainingCapacity = 0;

        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void have_items_in_equipment() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When
        Item sword = new Sword("Katana");
        Item bat = new BaseballBat("Strong baseball Bat");
        Item pan = new FryingPan("A pan");
        Item pistol = new Pistol("9mm");
        Item molotov = new Molotov("High explosive molotov");
        bob.pickUpItem(sword);
        bob.pickUpItem(bat);
        bob.pickUpItem(pan);
        bob.pickUpItem(pistol);
        bob.pickUpItem(molotov);

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
        //Given
        Survivor bob = new Survivor("Bob");
        //When
        Item sword = new Sword("Katana");
        Item bat = new BaseballBat("Strong baseball Bat");
        Item pan = new FryingPan("A pan");
        Item pistol = new Pistol("9mm");
        Item molotov = new Molotov("High explosive molotov");

        bob.pickUpItem(sword);
        bob.pickUpItem(bat);
        bob.pickUpItem(pan);
        bob.pickUpItem(pistol);
        bob.pickUpItem(molotov);
        bob.equip(sword);
        bob.equip(pistol);
        Set<Item> expectedInHand = new HashSet<>();
        expectedInHand.add(sword);
        expectedInHand.add(pistol);

        //Then
        Assertions.assertEquals(expectedInHand, bob.getEquippedItems());
    }

    @Test
    public void equip_only_two_items() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When
        Item sword = new Sword("Katana");
        Item bat = new BaseballBat("Strong baseball Bat");
        Item pan = new FryingPan("A pan");
        Item pistol = new Pistol("9mm");
        Item molotov = new Molotov("High explosive molotov");

        bob.pickUpItem(sword);
        bob.pickUpItem(bat);
        bob.pickUpItem(pan);
        bob.pickUpItem(pistol);
        bob.pickUpItem(molotov);
        bob.equip(sword);
        bob.equip(pistol);
        bob.equip(molotov);

        Set<Item> expectedEquippedItem = new HashSet<>();
        expectedEquippedItem.add(pistol);
        expectedEquippedItem.add(molotov);
        Assertions.assertEquals(expectedEquippedItem, bob.getEquippedItems());
    }

}
