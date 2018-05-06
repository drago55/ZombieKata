import bag.Bag;
import levels.Level;
import levels.LevelSystem;
import levels.Levels;
import names.Names;
import survivors.Survivor;
import wounds.Wounds;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameData implements Game {

    private boolean gameEnded = false;
    private Survivor zombieSurvivor;
    private Set<Survivor> survivors = new HashSet<>();
    private Names names;
    private Level level;
    private static final int NO_EXPERIENCE = 0;

    public GameData(Names names) {
        level = new LevelSystem();
        this.names = names;
    }

    @Override
    public int getSurvivorsCount() {
        return survivors.size();
    }

    @Override
    public void addSurvivor(Survivor survivor) {
        this.zombieSurvivor = survivor;
        zombieSurvivor.setName(getNameFromList());
        survivors.add(zombieSurvivor);
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
            throw new IllegalStateException("No more names to assign! can't generate anymore survivors");
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
    }

    @Override
    public boolean isEnded() {
        return gameEnded;
    }

    @Override
    public Levels getCurrentLevel() {
        int experience = getHighestExperiencedSurvivor();
        return level.getCurrentLevel(experience);
    }

    private int getHighestExperiencedSurvivor() {
        return survivors.stream()
                .filter((survivor -> !survivor.isDead()))
                .mapToInt((survivor) -> survivor.getExperience())
                .max()
                .orElse(NO_EXPERIENCE);
    }
}
