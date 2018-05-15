package actions;

public interface Action {

    void doAction();

    int getAvailableActions();

    void addAdditionalAction();
}
