import names.Names;
import survivors.Survivor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private boolean gameEnded = false;
    private Survivor zombieSurvivor;
    private Set<Survivor> survivors = new HashSet<>();
    private Names names;

    public Game() {
    }

    public Game(Names names) {
        this.names = names;
    }

    public int getSurvivorsCount() {
        return survivors.size();
    }

    public void addSurvivor(Survivor survivor) {
        this.zombieSurvivor = survivor;
        zombieSurvivor.setName(getNameFromList());
        survivors.add(zombieSurvivor);
    }

    public String getNameFromList() throws IllegalStateException {
        Optional<String> optionalOfName = names.getNameAndUpdateList();
        if (getSurvivorsCount() == 20 && !optionalOfName.isPresent()) {
            throw new IllegalStateException("No more names to assign! can't generate anymore survivors");
        }
        return optionalOfName.get();
    }

    public Set<String> getSurvivorsNames() {
        return survivors.stream().map((Survivor survivor) -> survivor.getName()).collect(Collectors.toSet());
    }

    public void killAllSurvivors() {
        survivors.stream().forEach(survivor -> survivor.kill());
        gameEnded = true;
    }

    public boolean isEnded() {
        return gameEnded;
    }
}
