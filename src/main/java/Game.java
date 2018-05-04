import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private boolean gameEnded = false;
    private Survivor survivor;
    private Set<Survivor> survivors = new HashSet<>();

    public Game() {
    }

    public int getSurvivorsCount() {
        return survivors.size();
    }

    public void addSurvivor() {
        survivor = new Survivor(generateRandomName());
        survivors.add(survivor);
    }

    private String generateRandomName() {
        return "";
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
