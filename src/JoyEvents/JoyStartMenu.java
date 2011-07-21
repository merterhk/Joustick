package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyStartMenu extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_WINDOWS);
                alive = true;
            } catch (Exception ex) {
                System.out.println("BackSpace Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_WINDOWS);
                alive = false;
            } catch (Exception ex) {
                System.out.println("BackSpace Error..");
            }
        }
    }
}
