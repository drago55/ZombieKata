package game;

import bag.Bag;
import levels.Levels;
import survivors.Survivor;
import wounds.Wounds;

import java.util.Set;

public interface Game extends GameNotification {

    int getSurvivorsCount();

    void addSurvivor(Survivor survivor);

    void addSurvivor(Survivor survivor, Bag bag, Wounds wounds);

    String getNameFromList() throws IllegalStateException;

    Set<String> getSurvivorsNames();

    void killAllSurvivors();

    boolean isEnded();

    Levels getCurrentGameLevel();


}
