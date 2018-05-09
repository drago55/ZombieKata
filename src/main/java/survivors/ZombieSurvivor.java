package survivors;

import bag.Bag;
import equipment.Equipment;
import game.Game;
import levels.Level;
import levels.LevelSystem;
import levels.Levels;
import wounds.BasicWounds;
import wounds.Wounds;
import zombies.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static names.StringConstants.*;

public class ZombieSurvivor implements Survivor {

    private String name;
    private int experience = 0;
    private int baseDamage = 2;
    private Wounds wounds;
    private static Actions actions;
    private Bag bag;
    private List<Equipment> inHand;
    private Level levelSystem;
    private Levels currentLevel;
    private Game game;

    public ZombieSurvivor() {
        levelSystem = new LevelSystem(this);
        currentLevel = Levels.BLUE;
        actions = Actions.getInstance();
        inHand = new ArrayList<>();
    }

    @Override
    public void setBag(Bag bag) {
        this.bag = bag;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getExperience() {
        return this.experience;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getWounds() {
        return this.wounds.getWounds();
    }

    @Override
    public void setWounds(Wounds wounds) {
        this.wounds = wounds;
    }

    @Override
    public void receiveWound(Wounds wounds) {
        this.wounds = wounds;
        bag.reduceCapacityAndDropItem();
        notifyWoundReceived(wounds);
        notifyIfDead();
    }

    private void notifyWoundReceived(Wounds wounds) {
        this.game.notify(SURVIVOR + this.getName() + RECEIVED_WOUND + wounds.getWounds());
    }

    private void notifyIfDead() {
        if (this.isDead()) {
            this.game.notify(SURVIVOR + this.getName() + IS_DEAD);
            this.game.onKilledSurvivor();
        }
    }

    @Override
    public boolean isDead() {
        return wounds.getWounds() == 2;
    }

    @Override
    public void performAction() {
        this.actions.doAction();
    }

    @Override
    public int getRemainingActions() {
        return actions.getRemaining();
    }

    @Override
    public int getEquipmentRemainingCapacity() {
        return bag.getBagFreeSlots();
    }

    @Override
    public void pickUpEquipmentItem(Equipment equipmentItem) {
        this.bag.addEquipment(equipmentItem);
        game.notify(SURVIVOR + this.getName() + ITEM_PICK_UP + equipmentItem.getName());
    }

    @Override
    public Set<Equipment> getEquipmentList() {
        return bag.getItems().collect(Collectors.toSet());
    }

    @Override
    public List<Equipment> getEquippedItems() {
        return this.inHand;
    }

    @Override
    public void equip(Equipment item) {
        Optional<Equipment> optionalOfItem = getItemFromEquipmentBag(item);
        if (optionalOfItem.isPresent()) {
            if (inHand.size() == 2) {
                unEquipFirst();
            }
            inHand.add(optionalOfItem.get());
        }
    }

    @Override
    public void unEquipFirst() {
        if (!inHand.isEmpty()) {
            inHand.remove(0);
        }
    }

    @Override
    public Optional<Equipment> getItemFromEquipmentBag(Equipment itemToEquip) {
        return getEquipmentList().stream().filter(item -> item == itemToEquip).findAny();
    }

    @Override
    public void killSurvivor() {
        this.wounds.setWounds(2);
    }

    @Override
    public void attack(Zombie zombie) {
        zombie.receiveDamage(new BasicWounds().setWounds(baseDamage));
        if (zombie.isDead()) {
            this.experience++;
            if (levelSystem.isLevelUp(experience)) {
                this.onLevelUp();
            }
        }
    }

    @Override
    public Levels getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }


    @Override
    public void onLevelUp() {
        currentLevel = levelSystem.getLevel(experience);
        this.game.notify(LEVEL_UP + SURVIVOR + this.getName() + IS_LEVEL + currentLevel);
        this.game.onLevelUp();
    }
}
