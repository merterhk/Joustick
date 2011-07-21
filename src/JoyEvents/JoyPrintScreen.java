package JoyEvents;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class JoyPrintScreen extends JoyEvent {

    public void pressed() {
        if (!alive) {
            try {
                System.out.println("task manager");
                (new Robot()).keyPress(KeyEvent.VK_PRINTSCREEN);
                //released();
                alive = true;
            } catch (Exception ex) {
                System.out.println("PRINTSCREEN Key Error..");
            }
        }
    }

    public void released() {
        if (alive) {
            try {
                (new Robot()).keyRelease(KeyEvent.VK_PRINTSCREEN);
                JOptionPane.showMessageDialog(null, "Ekran görüntüsü panoya kopyalandı.", "Screen Capture Başarılı", JOptionPane.INFORMATION_MESSAGE);
                alive = false;
            } catch (Exception ex) {
                System.out.println("PRINTSCREEN Key Error..");
            }
        }
    }
}
