package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyF6 extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_F6);
                alive = true;
            } catch (Exception ex) {
                System.out.println("F6 Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_F6);
                alive = false;
                
            } catch (Exception ex) {
                System.out.println("F6 Key Error..");
            }
        }
    }
}
