package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyKEY extends JoyEvent {

    int keycode;

    public JoyKEY(int keycode) {
        this.keycode = keycode;
    }

    public JoyKEY(String keycode) {
        keycode.replaceAll("key", "");
        this.keycode = Integer.parseInt(keycode);
    }

    public void pressed() {
        if (!alive) {
            try {
                (new Robot()).keyPress(keycode);
                alive = true;
            } catch (Exception ex) {
                System.out.println(keycode + " Key Code Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(keycode);
                alive = false;

            } catch (Exception ex) {
                System.out.println(keycode + " Key Code Error..");
            }
        }
    }
}
