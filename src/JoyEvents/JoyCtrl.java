package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyCtrl extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_CONTROL);
                alive = true;
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_CONTROL);
                alive = false;
                
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }
}
