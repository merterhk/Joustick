package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyChar extends JoyEvent {
char c;
    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_ALT);
                alive = true;
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_ALT);
                alive = false;
                
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }
}
