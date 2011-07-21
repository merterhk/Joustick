package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyBackSpace extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_BACK_SPACE);
                alive = true;
            } catch (Exception ex) {
                System.out.println("BackSpace Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_BACK_SPACE);
                alive = false;
            } catch (Exception ex) {
                System.out.println("BackSpace Error..");
            }
        }
    }
}
