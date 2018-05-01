import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
