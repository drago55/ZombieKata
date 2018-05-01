public class Actions {

    private int remaining = 3;
    private static Actions action = null;


    private Actions() {
    }

    public static Actions getInstance() {

        if (action == null) {
            action = new Actions();
        }
        return action;
    }

    public int getRemaining() {
        return remaining;
    }

    public void doAction() {
        if (remaining == 0) {
            throw new IllegalStateException("Can't do more than tree actions");
        }
        reduceActions();
    }

    private void reduceActions() {
        if (remaining != 0) {
            remaining -= 1;
        }
    }
}
