package actions;

public class Actions implements Action {
    //TODO implement turn after all actions are spent
    private int availableActions = 3;

    public Actions() {
    }

    private void reduceActions() {
        if (availableActions != 0) {
            availableActions -= 1;
        }
    }

    @Override
    public void doAction() {
        if (availableActions == 0) {
            throw new IllegalStateException("Can't do more than " + availableActions + " actions per turn!");
        }
        reduceActions();
    }

    @Override
    public int getAvailableActions() {
        return availableActions;
    }

    @Override
    public void addAdditionalAction() {
        this.availableActions += 1;
    }

}
