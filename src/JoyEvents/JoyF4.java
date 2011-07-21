package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyF4 extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_F4);
                alive = true;
            } catch (Exception ex) {
                System.out.println("F4 Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_F4);
                alive = false;
            } catch (Exception ex) {
                System.out.println("F4 Key Error..");
            }
        }
    }
}
