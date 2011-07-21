package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyTwiceButton extends JoyEvent {

    JoyEvent je1, je2;

    public JoyTwiceButton(JoyEvent je1, JoyEvent je2) {
        this.je1 = je1;
        this.je2 = je2;
    }

    public void pressed() {
        if (!alive) {
            try {
                je1.pressed();
                je2.pressed();
                alive = true;
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                je1.released();
                je2.released();
                alive = false;
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }
}
