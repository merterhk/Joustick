
import java.io.IOException;

import com.centralnexus.input.Joystick;
import com.centralnexus.input.JoystickListener;

//---------------------------------------------------------------
// JoystickControl, uses the Joystick library
// http://sourceforge.net/projects/javajoystick/
//---------------------------------------------------------------
// Needs the Joystick.jar, jjoystick.dll and libjjstick.*
//---------------------------------------------------------------
// Works in Windows and Linux (I supossed)
//---------------------------------------------------------------
// Create an instance in the begining of the program and use it
// Calling getAxis, getAxisAlt, getButton and getButtonAlt you
// will obtain the joystick's state.
// If you call and no joystick available, doesn't raises exceptions
// and returns FALSE like no using the joystick.
//---------------------------------------------------------------
// Look into the Joystick library to attend other buttons and
// more than one device.
//---------------------------------------------------------------

public class JoystickControl implements JoystickListener
{
    //---------------------------------------------------------------
    // MEMBERS
    //---------------------------------------------------------------

    // Class Constants
    public final int UP            = 0,
                     DOWN        = 1,
                     LEFT        = 2,
                     RIGHT        = 3,
                     BUTTON1    = 0,
                     BUTTON2    = 1,
                     BUTTON3    = 2,
                     BUTTON4    = 3;

    // Variable members
    private Joystick pJoy;
    private int nNumDevices;
    private boolean abAxis[] = {false, false, false, false};
    private boolean abButtons[] = {false, false, false, false};

    //---------------------------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------------------------
    public JoystickControl()
    {
        super();
        this.pJoy = null;
        this.nNumDevices = Joystick.getNumDevices();
        if (this.nNumDevices > 0)
        {
            try
            {
                this.pJoy = Joystick.createInstance();
                this.pJoy.addJoystickListener(this);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                this.pJoy = null;
            }
        }
    }

    //---------------------------------------------------------------
    // Is Available
    //---------------------------------------------------------------
    public boolean isAvailable ()
    {
        return (this.pJoy != null);
    }

    //---------------------------------------------------------------
    // getAxis (UP, DOWN, LEFT, RIGHT)
    //---------------------------------------------------------------
    public boolean getAxis (int nDir)
    {
        return isAvailable() && this.abAxis[nDir];
    }

    //---------------------------------------------------------------
    // getAxisAlt (UP, DOWN, LEFT, RIGHT) resets the direction
    //---------------------------------------------------------------
    public boolean getAxisAlt (int nDir)
    {
        boolean bRet = isAvailable() && this.abAxis[nDir];
        if (bRet)
            this.abAxis[nDir] = false;
        return bRet;
    }


    //---------------------------------------------------------------
    // getButton (BUTTON1..4)
    //---------------------------------------------------------------
    public boolean getButton (int nButton)
    {
        return isAvailable() && this.abButtons[nButton];
    }

    //---------------------------------------------------------------
    // getButtonAlt (BUTTON1..4) resets the button
    //---------------------------------------------------------------
    public boolean getButtonAlt (int nButton)
    {
        boolean bRet = isAvailable() && this.abButtons[nButton];
        if (bRet)
            this.abButtons[nButton] = false;
        return bRet;
    }

    //---------------------------------------------------------------
    // Event: joystickAxisChanged
    //---------------------------------------------------------------
    public void joystickAxisChanged(Joystick pJoy)
    {
        if (this.pJoy != pJoy)
            return;
        this.abAxis[UP] = this.pJoy.getY() == -1.0;
        this.abAxis[DOWN] = this.pJoy.getY() == 1.0;
        this.abAxis[RIGHT] = this.pJoy.getX() == 1.0;
        this.abAxis[LEFT] = this.pJoy.getX() == -1.0;
    }

    //---------------------------------------------------------------
    // Event: joystickButtonChanged
    //---------------------------------------------------------------
    public void joystickButtonChanged(Joystick pJoy)
    {
        if (this.pJoy != pJoy)
            return;

        this.abButtons[BUTTON1] = (this.pJoy.getButtons() & Joystick.BUTTON1) != 0;
        this.abButtons[BUTTON2] = (this.pJoy.getButtons() & Joystick.BUTTON2) != 0;
        this.abButtons[BUTTON3] = (this.pJoy.getButtons() & Joystick.BUTTON3) != 0;
        this.abButtons[BUTTON4] = (this.pJoy.getButtons() & Joystick.BUTTON4) != 0;
    }

}