package JoyEvents;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class JoyRightClick extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).mousePress(InputEvent.BUTTON3_MASK);
                alive = true;
            } catch (Exception ex) {
                System.out.println("Right Click Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).mouseRelease(InputEvent.BUTTON3_MASK);
                alive = false;
                
            } catch (Exception ex) {
                System.out.println("Right Click Error..");
            }
        }
    }
}
