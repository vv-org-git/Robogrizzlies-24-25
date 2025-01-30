package org.firstinspires.ftc.teamcode.mechanics.arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class arm {
    utility_motor extender;
    utility_motor rotator;

    public static boolean extender_dir = true;
    public static boolean rotator_dir = true;
    public static int max_height = 24;
    public static int mid_height = 60;
    public static double basket_height = 54;
    public static double basket_rotation = 0.7;


    public static int max_rotation = 1;
    public static int min_rotation = 0;
    public static double high_bar_rotation = 0.3;
    public static int high_bar_height = 38;

    public static int ground_pick_up = 24;
    public static int ground_pick_up_rev = 14;


    public static double ticks_per_inch_ex = 75;
    public static double ticks_per_rotation = 568;
    public static double ticks_per_degree = 1200;
    public static double offset = 0;



    public arm(LinearOpMode l) {
        extender = new utility_motor(l.hardwareMap, "extender", extender_dir);
        rotator = new utility_motor(l.hardwareMap, "rotator", rotator_dir);
    }

    public void up(){rotator.setPlace((int) (max_rotation * ticks_per_degree), 0.5);}
    public void down(){rotator.setPlace(0, 0.5);}


    public void extend() {extender.setPlace((int) (max_height * ticks_per_inch_ex), 1);}
    //useless function
    public void extend_mid() {extender.setPlace((int) (mid_height * ticks_per_inch_ex), 1);}
    public void basket() {
        extender.setPlace((int) (basket_height * ticks_per_rotation), 1);
        rotator.setPlace((int) (basket_rotation * ticks_per_rotation), 0.5);
    }

    public void high_bar() {
        extender.setPlace((int) (high_bar_height * ticks_per_inch_ex), 1.0);
        rotator.setPlace((int) (high_bar_rotation * ticks_per_rotation), 0.5);
    }

    public void ground() {
        extender.setPlace((int) (ground_pick_up * ticks_per_inch_ex), 1);
        rotator.setPlace(0, 0.5);
    }
    public void reverse_ground() {
        extender.setPlace((int) (ground_pick_up_rev * ticks_per_inch_ex), 1);
        rotator.setPlace((int) ( ticks_per_rotation), 0.5);
    }
    public void partial_retract() {
        extender.setPlace((int) (30 * ticks_per_inch_ex), 1);
    }

    public void setExtPos(double in){extender.setPlace((int) ( in*ticks_per_rotation), 1);}
    public void setRotPos(double deg){rotator.setPlace((int) (deg*ticks_per_rotation), 0.3);}

    //go to 45
    public static double amount1 = 0.72;

    public void rot0(){
        rotator.setPlace((int) ((amount1 + offset) * ticks_per_degree), 0.4);
    }
    //make it go to 0 deg
    public static double amount2 = 0.94;

    public void rot1(){
        rotator.setPlace((int) ((amount2 + offset)* ticks_per_degree), 0.4);
    }
    //go to 115 deg

    public static double amount3 = 0.4;

    public void rot2(){
        rotator.setPlace((int) ((amount3 + offset) * ticks_per_degree), 0.4);
    }
    //go all the way back
    public static double amount4 = 0.05;
    public void rot3(){
        rotator.setPlace((int) ((amount4+offset) * ticks_per_degree), 0.6);
    }

    public void retract() {extender.setPlace(0, -1);}

    public void setExtenderPower(double power) {extender.setPower(power);}
    public void setRotatorPower(double power) {rotator.setPower(power);}

    public void testExtenderTicks() {extender.setPlace((int) (10 * ticks_per_inch_ex), 0.3);}
    public void testRotatorTicks() {rotator.setPlace((int) (90 * ticks_per_rotation), 0.3);}
    public void retract_reset(){
        rotator.setPlace(-2000, 1);
    }

    public boolean isBusy() {return (Math.abs(extender.getPower()) > 0.5 || Math.abs(rotator.getPower()) > 0.1);}

}
