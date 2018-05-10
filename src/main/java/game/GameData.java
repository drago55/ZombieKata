package game;

import bag.Bag;
import history.GameHistory;
import history.History;
import levels.Level;
import levels.LevelSystem;
import levels.Levels;
import names.Names;
import survivors.Survivor;
import wounds.Wounds;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static names.StringConstants.*;

public class GameData implements Game {

    private boolean gameEnded = false;
    private Survivor zombieSurvivor;
    private Set<Survivor> survivors = new HashSet<>();
    private Names names;
    private Level levelSystem;
    private Levels currentLevel;
    private History<String> history;
    private int maxSurvivorExperience = 0;

    public GameData(Names names) {
        this.currentLevel = Levels.BLUE;
        this.history = new GameHistory("Game " + this.hashCode());
        this.levelSystem = new LevelSystem(this);
        this.names = names;
        this.notify(GAME_STARTED + LocalTime.now().withNano(0));
    }

    @Override
    public int getSurvivorsCount() {
        return survivors.size();
    }

    @Override
    public void addSurvivor(Survivor survivor) {
        this.zombieSurvivor = survivor;
        this.zombieSurvivor.setGame(this);
        zombieSurvivor.setName(getNameFromList());
        survivors.add(zombieSurvivor);
        this.notify(ADDED_SURVIVOR + survivor.getName());
    }

    @Override
    public void addSurvivor(Survivor survivor, Bag bag, Wounds wounds) {
        this.addSurvivor(survivor);
        this.zombieSurvivor.setBag(bag);
        this.zombieSurvivor.setWounds(wounds);
    }

    @Override
    public String getNameFromList() throws IllegalStateException {
        Optional<String> optionalOfName = names.getNameAndUpdateList();
        if (getSurvivorsCount() == 20 && !optionalOfName.isPresent()) {
            throw new IllegalStateException(NO_MORE_NAMES_TO_ASSIGN);
        }
        return optionalOfName.get();
    }

    @Override
    public Set<String> getSurvivorsNames() {
        return survivors.stream().map((Survivor survivor) -> survivor.getName()).collect(Collectors.toSet());
    }

    @Override
    public void killAllSurvivors() {
        survivors.stream().forEach(survivor -> survivor.killSurvivor());
        gameEnded = true;
        this.notify(GAME_OVER);
    }

    @Override
    public boolean isEnded() {
        return gameEnded;
    }

    @Override
    public Levels getCurrentLevel() {
        return currentLevel;
    }

    private int getMaxSurvivorExperience() {
        return survivors.stream()
                .filter((survivor -> !survivor.isDead()))
                .mapToInt((survivor) -> survivor.getExperience())
                .max()
                .orElse(maxSurvivorExperience);
    }

    @Override
    public void notify(Object o) {
        try {
            history.log(o.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void onLevelUp() {
        this.maxSurvivorExperience = getMaxSurvivorExperience();
        currentLevel = levelSystem.getLevel(maxSurvivorExperience);
        this.notify(GAME_INCREASED_DIFFICULTY + currentLevel);
    }

    @Override
    public void onKilledSurvivor() {
        if (isThereNoMoreSurvivors()) {
            gameEnded = true;
            this.notify(GAME_OVER);
        }
    }

    @Override
    public History getGameHistory() {
        return history;
    }

    private boolean isThereNoMoreSurvivors() {
        return survivors.stream().filter(survivor -> !survivor.isDead()).count() == 0;
    }
}

