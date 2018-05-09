package game;

import history.History;

public interface GameNotification<T> {

    void notify(T t);

    void onLevelUp();

    void onKilledSurvivor();

    History getGameHistory();
}
