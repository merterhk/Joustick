package JoyEvents;

abstract public class JoyEvent {

    boolean alive = false;
    boolean action = false;

    abstract public void pressed();

    abstract public void released();

    public void apply() {
        if (action) {
            if (!alive) {
                pressed();
            }
        } else {
            if (alive) {
                released();
            }
        }
    }

    public void setAction(boolean action) {
        this.action = action;
        apply();
    }

    public boolean getAction() {
        return action;
    }
}
