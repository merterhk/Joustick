
import JoyEvents.*;
import com.centralnexus.input.Joystick;
import com.centralnexus.input.JoystickListener;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class JoyThread extends Thread implements JoystickListener {

    private Joystick joy;
    TrayIcon trayIcon;
    /* Mouse Control */
    boolean[] mouseDir = {false, false, false, false}; // Yukarı,Aşağı,Sağ,Sol
    boolean mouseTurbo = false;
    byte mouseSpeed = 5; // max:8 (10-8=2ms en az uyutma payı)
    int mousePixel = 0; // Pixel Counter
    int axisX=0,axisY=0;
    /* Wheel Control */
    byte wheelValue = 0;
    /* Button Control */
    // Üçgen,Yuvarlak,Çarpı,Kare * 16 32 64 128 256 512 1024 2048
    JoyEvent[] buttons = new JoyEvent[12]; // 12 tuş desteği :D

    /* Modes */
    boolean pause = true;
    /* Properties */
    Properties settings = new Properties();
    String s = "Merter Hami KARACAN";

    /* About */
    JoyAbout about = new JoyAbout();
    JoySettings ayarlar = new JoySettings(this);

    public JoyThread() {
        super();
        s.replace(' ', '_');
        //resetSettings();
        loadSettings();
        //setButtons();
        systemTray();

        try {
            String Lf = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(Lf);
        } catch (Exception e) {
            System.out.println("UI err.");
        }

        this.joy = null;
//        this.numDevices = Joystick.getNumDevices();

        if (Joystick.isPluggedIn(0)) {
            //if (this.numDevices > 0) {
            try {
                System.out.println(Joystick.isPluggedIn(0));

                this.joy = Joystick.createInstance();
                this.joy.addJoystickListener(this);

            } catch (IOException e) {
                System.out.println("ilk hata");
                trayIcon.displayMessage("Joustick - Hata",
                        "Takılı Joystick bulunamadı..",
                        TrayIcon.MessageType.INFO);

                e.printStackTrace();
                this.joy = null;

                // pause = true;
            }
        } else {
            System.out.println("hello");
        }
    }

    public void loadSettings() {
        try {
            settings.load(new FileInputStream("settings.jms"));
            buttons[0] = loadJoyEvent(settings.getProperty("button0"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button0")).newInstance();
            buttons[1] = loadJoyEvent(settings.getProperty("button1"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button1")).newInstance();
            buttons[2] = loadJoyEvent(settings.getProperty("button2"));//(JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button2")).newInstance();
            buttons[3] = loadJoyEvent(settings.getProperty("button3"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button3")).newInstance();
            buttons[4] = loadJoyEvent(settings.getProperty("button4"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button4")).newInstance();
            buttons[5] = loadJoyEvent(settings.getProperty("button5"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button5")).newInstance();
            buttons[6] = loadJoyEvent(settings.getProperty("button6"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button6")).newInstance();
            buttons[7] = loadJoyEvent(settings.getProperty("button7"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button7")).newInstance();
            buttons[8] = loadJoyEvent(settings.getProperty("button8"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button8")).newInstance();
            buttons[9] = loadJoyEvent(settings.getProperty("button9"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button9")).newInstance();
            buttons[10] = loadJoyEvent(settings.getProperty("button10"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button10")).newInstance();
            buttons[11] = loadJoyEvent(settings.getProperty("button11"));// (JoyEvent) Class.forName("JoyEvents." + settings.getProperty("button11")).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ayarlar yüklenirken sorun oluştu. Varsayılan ayarlar yüklenecek.");
            resetSettings();
        }
    }

    public JoyEvent loadJoyEvent(String name) throws Exception {
        if (name.indexOf('/') > 0) {
            JoyEvent je1, je2;
            System.out.println("name:" + name.split("/")[0] + "  " + name.split("/")[1]);
            je1 = (name.split("/")[0].startsWith("key")) ? new JoyKEY(name.split("/")[0]) : (JoyEvent) Class.forName("JoyEvents." + name.split("/")[0]).newInstance();
            je2 = (name.split("/")[1].startsWith("key")) ? new JoyKEY(name.split("/")[1]) : (JoyEvent) Class.forName("JoyEvents." + name.split("/")[1]).newInstance();
            return new JoyTwiceButton(je1, je2);
        } else {
            return (name.startsWith("key")) ? new JoyKEY(name) : (JoyEvent) Class.forName("JoyEvents." + name).newInstance();
        }
    }

    public void resetSettings() {
        try {
            settings.setProperty("button0", "JoyEnter");// Üçgen
            settings.setProperty("button1", "JoyNothing");// Yuvarlak
            settings.setProperty("button2", "JoyAlt/JoyF4");// Çarpı
            settings.setProperty("button3", "JoyLeftClick");// Kare
            settings.setProperty("button4", "JoyNothing");// sol üst
            settings.setProperty("button5", "JoyNothing");// sağ üst
            settings.setProperty("button6", "JoyNothing");// sol alt
            settings.setProperty("button7", "JoyNothing");// sağ alt
            settings.setProperty("button8", "JoyNothing");// select
            settings.setProperty("button9", "JoyNothing");//
            settings.setProperty("button10", "JoyNothing");// joystick sol
            settings.setProperty("button11", "JoyNothing");// joystick sağ
            //settings.setProperty("mousespeed", "10");// joystick sağ
            settings.store(new FileOutputStream("settings.jms"), "Joustick Button Settings");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Varsayılan ayarlar yüklenirken sorun oluştu. Lütfen uygulamayı baştan yüklemeyi deneyin.");

        }
    }

    public void systemTray() {
        if (SystemTray.isSupported()) {
            try {
                SystemTray tray = SystemTray.getSystemTray();

                Image image = ImageIO.read(this.getClass().getResource("joyico.png")); // Toolkit.getDefaultToolkit().getImage("j256.png");

                MouseListener mouseListener = new MouseListener() {

                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Tray Icon - Mouse clicked!");
                    }

                    public void mouseEntered(MouseEvent e) {
                        System.out.println("Tray Icon - Mouse entered!");
                    }

                    public void mouseExited(MouseEvent e) {
                        System.out.println("Tray Icon - Mouse exited!");
                    }

                    public void mousePressed(MouseEvent e) {
                        System.out.println("Tray Icon - Mouse pressed!");
                    }

                    public void mouseReleased(MouseEvent e) {
                        System.out.println("Tray Icon - Mouse released!");
                    }
                };

                final MenuItem ayarlarItem = new MenuItem("Ayarlar");
                final MenuItem aboutItem = new MenuItem("Hakkında");
                final MenuItem startItem = new MenuItem("Başlat");
                final MenuItem pauseItem = new MenuItem("Durdur");
                final MenuItem exitItem = new MenuItem("Kapat");

                ActionListener ayarlarListener =  new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        ayarlar.setVisible(true);
                    }
                };
                ActionListener aboutListener = new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Hakkında...");
                        about.setVisible(true);
                    }
                };
                ActionListener exitListener = new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                };
                ActionListener startListener = new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        startItem.setEnabled(false);
                        pauseItem.setEnabled(true);
                        pause = false;
                        trayIcon.displayMessage("Joustick başladı",
                                "Joystick'inizi mouse olarak kullanabilirsiniz..",
                                TrayIcon.MessageType.INFO);

                    }
                };
                ActionListener pauseListener = new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        pauseItem.setEnabled(false);
                        startItem.setEnabled(true);
                        pause = true;
                        trayIcon.displayMessage("Joustick duraklatıldı",
                                "Joystick'iniz mouse'unuzu etkileyemeyecek..",
                                TrayIcon.MessageType.INFO);

                    }
                };

                PopupMenu popup = new PopupMenu();

                ayarlarItem.addActionListener(ayarlarListener);
                popup.add(ayarlarItem);



                startItem.addActionListener(startListener);
                popup.add(startItem);

                pauseItem.addActionListener(pauseListener);
                popup.add(pauseItem);

                popup.addSeparator();

                aboutItem.addActionListener(aboutListener);
                popup.add(aboutItem);

                exitItem.addActionListener(exitListener);
                popup.add(exitItem);

                trayIcon = new TrayIcon(image, "Joustick v.1.0 Beta", popup);

                ActionListener actionListener = new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        trayIcon.displayMessage("Joustick v.1.0 ßeta",
                                "2011 - Merter Hami KARACAN",
                                TrayIcon.MessageType.INFO);
                    }
                };

                trayIcon.setImageAutoSize(true);
                trayIcon.addActionListener(actionListener);
                //trayIcon.addMouseListener(mouseListener);


                tray.add(trayIcon);
            } catch (Exception e) {
                System.err.println("TrayIcon could not be added.");
            }

        } else {
            System.out.println("System tray is not supported..");
            //  System Tray is not supported
        }

    }

    public void setButtons() {
        /* Tuş Ataması */
        buttons[0] = new JoyLeftClick(); // Üçgen
        buttons[1] = new JoyRightClick(); // Yuvarlak
        buttons[2] = new JoyEnter(); // Çarpı
        buttons[3] = new JoyBackSpace(); // Kare

        buttons[4] = new JoyCtrl(); // sol üst
        buttons[5] = new JoyNothing(); // sağ üst
        buttons[6] = new JoyShift(); // sol alt
        buttons[7] = new JoyTaskManager(); // sağ alt

        buttons[8] = new JoyStartMenu(); // select
        buttons[9] = new JoyNothing(); //
        buttons[10] = new JoyNothing(); // joystick sol
        buttons[11] = new JoyNothing(); // joystick sağ
    }

    public void mouseControl() {
        try {
            Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
            if (mouseDir[0] || mouseDir[1] || mouseDir[2] || mouseDir[3]) {
                if (mousePixel < 500) {
                    if (mouseTurbo) {
                        mousePixel += (8 - mouseSpeed);
                    } else {
                        mousePixel += 1;
                    }
                }
            } else {
                mousePixel = 0;
            }
            mouseSpeed = (byte) ((mousePixel / 100));

            //(new Robot()).mouseMove(mouseLoc.x + ((mouseDir[2]) ? 1 : ((mouseDir[3]) ? -1 : 0)), mouseLoc.y + ((mouseDir[0]) ? -1 : ((mouseDir[1]) ? 1 : 0)));
            (new Robot()).mouseMove(mouseLoc.x + axisX/2, mouseLoc.y + axisY/2);
            Thread.sleep(13 - mouseSpeed);
        } catch (Exception e) {
            System.out.println("Mouse Control Error..");
        }

    }

    public void wheelControl() {
        try {
            if (wheelValue != 0) {
                (new Robot()).mouseWheel((wheelValue / 10) / 2);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println("Wheel Control Error");
        }
    }

    @Override
    public void run() {
        try {
            //resetSettings();
            trayIcon.displayMessage("Joustick başladı",
                    "Joystick'inizi mouse olarak kullanabilirsiniz..",
                    TrayIcon.MessageType.INFO);

            pause = false;
            while (true) {
                //joy.poll();
                if (!Joystick.isPluggedIn(0)) {
                    if (!pause) {
                        trayIcon.displayMessage("Joystick çıkarıldı..",
                                "Kullanmak için tekrar takıp, başlat seçeneğine tıklamalısınız..",
                                TrayIcon.MessageType.INFO);
                        pause = true;
                    }
                    Thread.sleep(2000);
                    continue;
                }
                if (!pause) {
                    mouseControl();
                    wheelControl();
                }
            }
        } catch (Exception e) {
            trayIcon.displayMessage("Joustick - Hata",
                    "Sorun oluştu lütfen programı tekrar başlatın...",
                    TrayIcon.MessageType.INFO);

            System.out.println("Thread err..");
        }
    }

    public static void main(String[] args) {
        new JoyThread().start();
    }

    public void joystickAxisChanged(Joystick jstck) {

        int mX = (int) Math.floor(joy.getX() * 100) + 1;
        int mY = (int) Math.floor(joy.getY() * 100) + 1;
        int mZ = (int) Math.floor(joy.getZ() * 100) + 1;

        int mR = (int) Math.floor(joy.getR() * 100) + 1; // soldaki Z: sağa sola R: yukarı aşağı
        int mU = (int) Math.floor(joy.getU() * 100) + 1;
        int mV = (int) Math.floor(joy.getV() * 100) + 1;

        mouseDir[0] = mY < 0;//(joy.getY() == -1.0); // Yukarı
        mouseDir[1] = mY > 0; //(joy.getY() == 1.0); // Aşağı
        mouseDir[2] = mX > 0;//(joy.getX() == 1.0); // Sağa
        mouseDir[3] = mX < 0;//(joy.getX() == -1.0); // Sola

        mouseTurbo = (Math.abs(mY) > 90 || Math.abs(mX) > 90);

        axisX=mX/10;
        axisY=mY/10;
        //System.out.println("Turbo : "+(mY > 90 || mX > 90));

        //System.out.println("mY:" + mY + " mX:" + mX);
        //System.out.println("X Axis : " + d);
        wheelValue = (byte) mR;
        //System.out.println("mZ:" + mZ);

        //System.out.println("Y:"+joy.getZ());
    }

    public void joystickButtonChanged(Joystick joy) {
        buttons[0].setAction((joy.getButtons() & 1) == 1); // Üçgen
        buttons[1].setAction((joy.getButtons() & 2) == 2); // Yuvarlak
        buttons[2].setAction((joy.getButtons() & 4) == 4); // Çarpı
        buttons[3].setAction((joy.getButtons() & 8) == 8); // Kare

        buttons[4].setAction((joy.getButtons() & 16) == 16); // Arka Üst Sol
        buttons[5].setAction((joy.getButtons() & 32) == 32); // Arka Üst Sağ
        buttons[6].setAction((joy.getButtons() & 64) == 64); // Arka Alt Sol
        buttons[7].setAction((joy.getButtons() & 128) == 128); // Arka Alt Sağ

        buttons[8].setAction((joy.getButtons() & 256) == 256); // select
        buttons[9].setAction((joy.getButtons() & 512) == 512); // start
        buttons[10].setAction((joy.getButtons() & 1024) == 1024); // Joystick sol
        buttons[11].setAction((joy.getButtons() & 2048) == 2048); // Joystick sağ

        // 1 2 4 8 16 32 64 128 256 512 1024 2048
        System.out.println("Buttons : " + joy.getButtons());

        joy.poll();

    }
}
