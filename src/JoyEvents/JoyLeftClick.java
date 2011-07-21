package JoyEvents;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class JoyLeftClick extends JoyEvent {

    public void pressed() {
        //System.out.println("pressed : " + alive);
        if (!alive) {
            try {
                (new Robot()).mousePress(InputEvent.BUTTON1_MASK);
                alive = true;
            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }

    public void released() {

        System.out.println("released : " + alive);
        if (alive) {
            try {
                (new Robot()).mouseRelease(InputEvent.BUTTON1_MASK);
                alive = false;

            } catch (Exception ex) {
                System.out.println("Enter Key Error..");
            }
        }
    }

    /*public void setAction(boolean action) {
        this.action=action;
        apply();
    }

    public void apply() {
        if (action) {
            pressed();
        } else {
            released();
        }
    }*/
}

//
//var
//
//
//{
//    degisken:'degere',
//    width:350,
//    height:300,
//    title:'baslik',
//    autoCollapse:true
//}
//
//grid.expand();