package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JoyTaskManager extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                System.out.println("task manager");
                (new Robot()).keyPress(KeyEvent.VK_CONTROL);
                (new Robot()).keyPress(KeyEvent.VK_ALT);
                (new Robot()).keyPress(KeyEvent.VK_DELETE);
                //released();
                alive = true;
            } catch (Exception ex) {
                System.out.println("TaskManager Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_DELETE);
                (new Robot()).keyRelease(KeyEvent.VK_ALT);
                (new Robot()).keyRelease(KeyEvent.VK_CONTROL);
                alive = false;
            } catch (Exception ex) {
                System.out.println("TaskManager Error..");
            }
        }
    }
}
