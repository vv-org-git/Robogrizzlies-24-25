package org.firstinspires.ftc.teamcode.mechanics.arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class arm {
    utility_motor extender;
    utility_motor rotator;

    public static boolean extender_dir = false;
    public static boolean rotator_dir = false;
    public static int max_height = 60;
    public static int mid_height = 60;
    public static int basket_height = 54;
    public static int basket_rotation = 58;


    public static int max_rotation = 60;
    public static int min_rotation = 1;
    public static int high_bar_rotation = 46;
    public static int high_bar_height = 38;

    public static int ground_pick_up = 24;
    public static int ground_pick_up_rev = 14;


    public static int ticks_per_inch_ex = 300;
    public static int ticks_per_inch_r = 300;



    public arm(LinearOpMode l) {
        extender = new utility_motor(l.hardwareMap, "extender", extender_dir);
        rotator = new utility_motor(l.hardwareMap, "rotator", rotator_dir);
    }

    public void up(){rotator.setPlace(max_rotation * ticks_per_inch_r);}
    public void down(){rotator.setPlace(0);}


    public void extend() {extender.setPower(1.0);}
    public void extend_mid() {extender.setPlace(mid_height * ticks_per_inch_ex);}
    public void basket() {
        extender.setPlace(basket_height * ticks_per_inch_ex);
        rotator.setPlace(basket_rotation * ticks_per_inch_r);
    }

    public void high_bar() {
        extender.setPlace(high_bar_height * ticks_per_inch_ex);
        rotator.setPlace(high_bar_rotation * ticks_per_inch_r);
    }

    public void ground() {
        extender.setPlace(ground_pick_up * ticks_per_inch_ex);
        rotator.setPlace(0);
    }
    public void reverse_ground() {
        extender.setPlace(ground_pick_up_rev * ticks_per_inch_ex);
        rotator.setPlace(180 * ticks_per_inch_r);
    }

    public void setExtPos(double in){extender.setPlace((int) in*ticks_per_inch_r);}
    public void setRotPos(double deg){rotator.setPlace((int) deg*ticks_per_inch_r);}



    public void retract() {extender.setPlace(0);}

    public void setExtenderPower(double power) {extender.setPower(power);}
    public void setRotatorPower(double power) {rotator.setPower(power);}

    public void testExtenderTicks() {extender.setPlace(10 * ticks_per_inch_ex);}
    public void testRotatorTicks() {rotator.setPlace(90 * ticks_per_inch_r);}

    public boolean isBusy() {return (extender.getPower() > 0 || rotator.getPower() > 0);}

}
