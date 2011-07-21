package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyF7 extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_F7);
                alive = true;
            } catch (Exception ex) {
                System.out.println("F7 Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_F7);
                alive = false;
            } catch (Exception ex) {
                System.out.println("F7 Key Error..");
            }
        }
    }
}
