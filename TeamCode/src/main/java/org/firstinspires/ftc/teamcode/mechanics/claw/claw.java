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
    //useless variable
    public static double x_servo_center = 0;

    Servo z_servo;
    public static double x_constant = 50;
    public static double z_constant = 180;

    public static double x_left = 1;
    public static double x_right = 0.5;

    public static double z_up = 1.0;
    public static double z_down = 0.5;

    public static double x_center =0.75;
    public static double z_center = 0.60;
    //find z_center^


    Servo claw_servo;
    public static double claw_open = 1;
    public static double claw_close = -0.3;

    public static double high_bar_percent = 0.7;


    public claw(LinearOpMode l){
        x_servo = l.hardwareMap.get(Servo.class, "claw_x");
        z_servo = l.hardwareMap.get(Servo.class, "claw_z");
        claw_servo = l.hardwareMap.get(Servo.class, "claw_servo");

        x_servo.setPosition(x_center);
        z_servo.setPosition(0);
        claw_servo.setPosition(claw_open);

    }

    public void bite() {claw_servo.setPosition(claw_close);}
    public void release() {claw_servo.setPosition(claw_open);}

    public void high_bar() {
        x_servo.setPosition(x_left);
        z_servo.setPosition(0.80);
    }
    public void high_bar_place(){
        z_servo.setPosition(z_center);
    }
    public void basket() {
        x_servo.setPosition(x_left);
        z_servo.setPosition(z_up );
    }

    public void ground_pick_up() {
        x_servo.setPosition(x_center);
        z_servo.setPosition(z_down);
        this.release();
    }
    //useless function
    public void ground_pick_up_reverse() {
        x_servo.setPosition(x_center);
        z_servo.setPosition(z_up);
    }

    public void x_test() {
        x_servo.setPosition(90/x_constant);
    }
    public void z_test() {
        z_servo.setPosition(90/z_constant);
    }

    public void rotate(double amount) {x_servo.setPosition(x_servo.getPosition() + amount/x_constant);}
    public void rotate_z(double amount) {z_servo.setPosition(z_servo.getPosition() + amount/z_constant);}
    public void setX(double amount) {x_servo.setPosition(amount/x_constant + x_center);}
    public void setZ(double amount) {z_servo.setPosition(amount/z_constant);} //useless function
    public void zUp() {
        z_servo.setPosition(z_up);
    }
    public void zDown() {
        z_servo.setPosition(z_down);
    }

    public void reset_x() {x_servo.setPosition(x_center);}

    //EXACT SAME FUNCTION AS zDown thus we must find a new variable to pass in, otherwise remove function + usages and cal zDown
    public void reset_z() {z_servo.setPosition(z_down);}
    public void rotate_x_left(){
        x_servo.setPosition(x_left);
    }
    public void rotate_x_right(){
        x_servo.setPosition(x_right);
    }





}
