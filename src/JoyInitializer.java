
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Merter
 */
public class JoyInitializer {

    public void doit() {
        try {
            Properties p = new Properties();
            p.load(new FileInputStream("user.ini"));
            System.out.println("user = " + p.getProperty("DBuser"));
            System.out.println("password = " + p.getProperty("DBpassword"));
            System.out.println("location = " + p.getProperty("DBlocation"));
            p.list(System.out);
            /*
            p.setProperty("DBuser", "dbuzer");
            p.setProperty("DBpassword", "dpass");
            p.setProperty("DBlocation", "locat");
            p.store(new FileOutputStream("user.ini"), null);*/
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Merhaba");
        }
    }

    public static void main(String[] args) {
        JoyInitializer jint = new JoyInitializer();
        jint.doit();
    }
}
