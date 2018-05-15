package survivors;

import actions.Action;
import bag.Bag;
import equipment.Equipment;
import game.Game;
import levels.LevelUp;
import levels.Levels;
import skills.Skill;
import wounds.Wounds;
import zombies.Zombie;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Survivor extends LevelUp{

    String getName();

    int getExperience();

    void setName(String name);

    void setBag(Bag bag);

    int getWounds();

    void setWounds(Wounds wounds);

    void receiveWound(Wounds wounds);

    boolean isDead();

    int getEquipmentRemainingCapacity();

    void pickUpEquipmentItem(Equipment equipmentItem);

    Set<Equipment> getBagItems();

    List<Equipment> getItemsInHand();

    void equip(Equipment item);

    void unEquipFirst();

    Optional<Equipment> getItemFromEquipmentBag(Equipment equipmentItem);

    void killSurvivor();

    void attack(Zombie zombie);

    Levels getCurrentLevel();

    void setGame(Game game);

    Skill getSkillTree();

    Action getAction();

    void setSkillTree(Skill skill);

    void doAction();

    Bag getBag();
}
