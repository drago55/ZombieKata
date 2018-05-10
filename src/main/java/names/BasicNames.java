package names;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class BasicNames implements Names {

    private static List<String> listOfAvailableNames;

    public BasicNames(NameConstants names) {
        this.listOfAvailableNames = names.initListOfAvailableNames();
    }

    @Override
    public Optional<String> getNameAndUpdateList() {
        Optional<String> name = listOfAvailableNames.stream().skip(ThreadLocalRandom.current().nextInt(listOfAvailableNames.size())).findFirst();
        listOfAvailableNames.removeIf((availableName) -> availableName.matches(name.get()));
        return name;
    }

}
