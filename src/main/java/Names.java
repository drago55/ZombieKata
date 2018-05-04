import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Names {

    private static final String[] names = {"Spencer", "Maximo", "Denyse", "Wes", "Shemeka", "Ester", "Larry", "Annabell", "Bret", "Yoshiko", "Vashti",
            "Selma", "Erick", "Chiquita", "Juan", "Tyler", "Leesa", "Erlinda", "Anita", "Delena"};

    public static Optional<String> getName() {
        return Stream.of(names).skip(ThreadLocalRandom.current().nextInt(names.length - 1)).findFirst();
    }
}
