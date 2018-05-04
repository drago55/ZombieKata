import java.util.HashSet;
import java.util.Optional;
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
        survivor = new Survivor(generateUniqueName());
        survivors.add(survivor);
    }

    private String generateUniqueName() throws IllegalStateException {
        Optional<String> optionalOfName = Names.getName();
        String name = optionalOfName.get();
        System.out.println("survivors " + getSurvivorsCount());
        System.out.println("names " + name);
        System.out.println("IS name unique " + isNameUnique(name));
        if (getSurvivorsCount() == 30) {
            throw new IllegalStateException("No more names to assign! can't generate anymore survivors");
        }
        if (isNameUnique(name)) {
            return name;
        }
        return generateUniqueName();
    }

    private boolean isNameUnique(String name) {
        return !getSurvivorsNames().contains(name);
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
