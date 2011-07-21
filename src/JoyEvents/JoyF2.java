package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyF2 extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_F2);
                alive = true;
            } catch (Exception ex) {
                System.out.println("F2 Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_F2);
                alive = false;
            } catch (Exception ex) {
                System.out.println("F2 Key Error..");
            }
        }
    }
}
