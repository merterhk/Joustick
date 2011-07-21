package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyF3 extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_F3);
                alive = true;
            } catch (Exception ex) {
                System.out.println("F3 Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_F3);
                alive = false;
                
            } catch (Exception ex) {
                System.out.println("F3 Key Error..");
            }
        }
    }
}
