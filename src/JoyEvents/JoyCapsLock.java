package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyCapsLock extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_CAPS_LOCK);
                alive = true;
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_CAPS_LOCK);
                alive = false;
                
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }
}
