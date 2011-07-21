package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyF5 extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_F5);
                alive = true;
            } catch (Exception ex) {
                System.out.println("F5 Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_F5);
                alive = false;
            } catch (Exception ex) {
                System.out.println("F5 Key Error..");
            }
        }
    }
}
