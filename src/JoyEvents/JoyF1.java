package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyF1 extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(KeyEvent.VK_F1);
                alive = true;
            } catch (Exception ex) {
                System.out.println("F1 Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_F1);
                alive = false;
                
            } catch (Exception ex) {
                System.out.println("F1 Key Error..");
            }
        }
    }
}
