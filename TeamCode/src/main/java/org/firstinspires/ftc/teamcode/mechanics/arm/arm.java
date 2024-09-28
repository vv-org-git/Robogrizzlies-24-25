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
    public static int min_height = 1;

    public static int max_rotation = 60;
    public static int min_rotation = 1;
    public static int high_bar_rotation = 1;



    public arm(LinearOpMode l) {
        extender = new utility_motor(l.hardwareMap, "extender", extender_dir);
        rotator = new utility_motor(l.hardwareMap, "rotator", rotator_dir);
    }

    public void up(){rotator.setPlace(max_rotation);}
    public void down(){rotator.setPlace(min_rotation);}
    public void high_bar(){rotator.setPlace(high_bar_rotation);}


    public void extend() {extender.setPlace(max_height);}
    public void retract() {extender.setPlace(min_height);}

    public void setExtenderPower(double power) {extender.setPower(power);}
    public void setRotatorPower(double power) {rotator.setPower(power);}

    public boolean isBusy() {return (extender.getPower() > 0 || rotator.getPower() > 0);}

}
