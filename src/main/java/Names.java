import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Names {

    private static List<String> listOfAvailableNames;

    public Names() {
        this.listOfAvailableNames = initListOfAvailableNames();
    }

    public static Optional<String> getNameAndUpdateList() {
        Optional<String> name = listOfAvailableNames.stream().skip(ThreadLocalRandom.current().nextInt(listOfAvailableNames.size())).findFirst();
        listOfAvailableNames.removeIf((availableName) -> availableName.matches(name.get()));
        return name;
    }

    private static List<String> initListOfAvailableNames() {
        listOfAvailableNames = new ArrayList<>();
        listOfAvailableNames.add("Spencer");
        listOfAvailableNames.add("Maximo");
        listOfAvailableNames.add("Denyse");
        listOfAvailableNames.add("Wes");
        listOfAvailableNames.add("Shemeka");
        listOfAvailableNames.add("Ester");
        listOfAvailableNames.add("Larry");
        listOfAvailableNames.add("Annabell");
        listOfAvailableNames.add("Bret");
        listOfAvailableNames.add("Yoshiko");
        listOfAvailableNames.add("Vashti");
        listOfAvailableNames.add("Selma");
        listOfAvailableNames.add("Erick");
        listOfAvailableNames.add("Chiquita");
        listOfAvailableNames.add("Juan");
        listOfAvailableNames.add("Tyler");
        listOfAvailableNames.add("Leesa");
        listOfAvailableNames.add("Erlinda");
        listOfAvailableNames.add("Anita");
        listOfAvailableNames.add("Delena");
        return listOfAvailableNames;
    }
}
