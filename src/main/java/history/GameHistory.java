package history;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameHistory implements History {

    private String logPath;

    public GameHistory(String gameName) {
        logPath = gameName + "log.txt";
    }

    @Override
    public void log(Object o) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logPath, true))) {
            writer.append(o.toString() + "\n");
            writer.append("-----------------------------------------------------------" + "\n");
            writer.flush();
            writer.close();
            logToConsole(o);
        }
    }

    private void logToConsole(Object o) {
        System.out.println("------------------------------------------------------");
        System.out.println(o.toString());
    }

    @Override
    public String getLogLine(String match) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(logPath));
        return reader.lines().filter(s -> s.contains(match)).findAny().orElse("");
    }

    @Override
    public void clearLog() {
        try {
            Files.deleteIfExists(Paths.get(logPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
