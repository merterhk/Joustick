package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyAltF4 extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                System.out.println("alt f4");
                (new Robot()).keyPress(KeyEvent.VK_ALT);
                (new Robot()).keyPress(KeyEvent.VK_F4);
                alive = true;
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_F4);
                (new Robot()).keyRelease(KeyEvent.VK_ALT);
                alive = false;
                
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }
}
