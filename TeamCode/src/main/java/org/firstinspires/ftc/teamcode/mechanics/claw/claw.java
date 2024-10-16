package org.firstinspires.ftc.teamcode.mechanics.claw;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class claw {
    Servo x_servo;
    public static double x_servo_center = 0;

    Servo z_servo;
    public static double x_constant = 180;
    public static double z_constant = 180;



    Servo claw_servo;
    public static double claw_open = 0;
    public static double claw_close = 0;

    public static double claw_z_high_bar = 136;
    public static double claw_z_basket = 148;


    public claw(LinearOpMode l){
        x_servo = l.hardwareMap.get(Servo.class, "claw_x");
        z_servo = l.hardwareMap.get(Servo.class, "claw_z");
        claw_servo = l.hardwareMap.get(Servo.class, "claw_servo");

        x_servo.setPosition(0);
        z_servo.setPosition(0);
        claw_servo.setPosition(claw_open);

    }

    public void bite() {claw_servo.setPosition(claw_close);}
    public void release() {claw_servo.setPosition(claw_open);}


    public void high_bar() {
        x_servo.setPosition(0);
        z_servo.setPosition(claw_z_high_bar/z_constant);
    }
    public void basket() {
        x_servo.setPosition(0);
        z_servo.setPosition(claw_z_basket/z_constant);
    }

    public void ground_pick_up() {
        x_servo.setPosition(0);
        z_servo.setPosition(90/z_constant);
    }
    public void ground_pick_up_reverse() {
        x_servo.setPosition(0);
        z_servo.setPosition(-90/z_constant);
    }

    public void x_test() {
        x_servo.setPosition(90/x_constant);
    }
    public void z_test() {
        z_servo.setPosition(90/z_constant);
    }

    public void rotate(double amount) {x_servo.setPosition(x_servo.getPosition() + amount/x_constant);}
    public void rotate_z(double amount) {z_servo.setPosition(z_servo.getPosition() + amount/z_constant);}

    public void reset_x() {x_servo.setPosition(0);}
    public void reset_z() {z_servo.setPosition(0);}






}
