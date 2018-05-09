package history;

import java.io.IOException;

public interface History<T> {

    void log(T t) throws IOException;

    String getLogLine(String match) throws IOException;

    void clearLog();

}
