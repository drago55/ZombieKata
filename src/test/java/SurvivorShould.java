import Items.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    public void survivor_can_carry_equipment_with_capacity_of_five() {
        //Given
        Survivor bob = new Survivor("Bob");
        //When

        int remainingCapacity = 5;
        //Then
        Assertions.assertEquals(remainingCapacity, bob.getEquipmentRemainingCapacity());
    }

    @Test
    public void pickup_item_throws_exception() {
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
    public void survivor_can_pickup_item() {
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


}
