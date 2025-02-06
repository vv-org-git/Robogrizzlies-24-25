package org.firstinspires.ftc.teamcode.mechanics.arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
public class arm {
    public utility_motor extender;
    public utility_motor extender312;
    public utility_motor rotator;

    public static boolean extender_dir = true;
    public static boolean rotator_dir = false;
    public static double motor_312_pow = 117/312;
    public static int max_height = 60;
    public static int mid_height = 60;
    public static double basket_height = 54;
    public static double basket_rotation = 0.7;


    public static int max_rotation = 1;
    public static int min_rotation = 0;
    public static double high_bar_rotation = 0.3;
    public static int high_bar_height = 38;

    public static int ground_pick_up = 24;
    public static int ground_pick_up_rev = 14;
    public static int max_extension = 2500;

    public static double ticks_per_inch_ex = 75;
    public static double ticks_per_rotation = 568;
    public static double ticks_per_degree = 568/90;
    public static double offset = 25;
    public static int arm_const = 10000;



    public arm(LinearOpMode l) {
        extender = new utility_motor(l.hardwareMap, "extender", extender_dir);
        extender312 = new utility_motor(l.hardwareMap, "extender2", extender_dir);

        rotator = new utility_motor(l.hardwareMap, "rotator", rotator_dir);
    }

    public void up(){rotator.setPlace((int) (max_rotation * ticks_per_degree), 0.5);}
    public void down(){rotator.setPlace(0, 0.5);}
    public void extend() {
        extender.setPlace(max_extension, 1);
        extender312.setPlace(max_extension, motor_312_pow);


    }

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
    public void release(){
        extender.reset();
    }
    public boolean bad_mode(){
        return extender.checker() != DcMotor.RunMode.RUN_TO_POSITION;
    }
    public boolean keep_arm_under_limit(){
        return !(extender.get_pos() * 1.1 >= arm_const);
    }
    public static double amount2 = -3;

    public void ground_pick_up(){
        rotator.setPlace((int) ((amount2 + offset) * ticks_per_degree), 0.3);
    }
    //go to 45
    public static double rotNegC = 0;

    public void rotNeg(){
        rotator.setPlace((int) (rotNegC * ticks_per_degree), 0.3);
    }
    public static double rot0C = 0;

    public void rot0(){
        rotator.setPlace((int) (rot0C * ticks_per_degree), 0.3);
    }
    //make it go to 0 deg

    public static double rot1C = 0;
    public void rot1(){
        rotator.setPlace((int) (rot1C * ticks_per_degree), 0.3);
    }
    //go to 115 deg
    public static double rot2C = 0;
    public void rot2(){
        rotator.setPlace((int) (rot2C * ticks_per_degree), 0.3);
    }
    //go all the way back

    public void retract() {
        extender.setPlace(0, 1);
        extender312.setPlace(0, motor_312_pow);
    }
    public static double rot3C = 0;
    public void rot3(){
        rotator.setPlace((int) (rot3C * ticks_per_degree), 0.3);
    }
    public void retract_negative(){
        rotator.setPlace(-3500, -0.7);
    }

    public void setExtenderPower(double power) {extender.setPower(power);}
    public void setRotatorPower(double power) {rotator.setPower(power);}

    public void testExtenderTicks() {extender.setPlace((int) (10 * ticks_per_inch_ex), 0.3);}
    public void testRotatorTicks() {rotator.setPlace((int) (90 * ticks_per_rotation), 0.3);}
    public void retract_reset(){
        extender.setPlace(-max_extension, 1);
        extender.reset();
    }
    public boolean isBusy() {return (extender.getPower() > 0 || rotator.getPower() > 0);}
}


//go all the way back
