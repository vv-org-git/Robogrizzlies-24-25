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
    public static double z_servo_center = 0;
    public static double z_servo_up = 0;
    public static double z_servo_down = 0;
    public static double degree_constant = 6.28;



    Servo claw_servo;
    public static double claw_open = 0;
    public static double claw_close = 0;


    public claw(LinearOpMode l){
        x_servo = l.hardwareMap.get(Servo.class, "claw_x");
        z_servo = l.hardwareMap.get(Servo.class, "claw_z");
        claw_servo = l.hardwareMap.get(Servo.class, "claw_servo");

        x_servo.setPosition(x_servo_center);
        z_servo.setPosition(z_servo_center);
        claw_servo.setPosition(claw_open);

    }

    public void bite() {claw_servo.setPosition(claw_close);}
    public void release() {claw_servo.setPosition(claw_open);}

    public void place() {
        z_servo.setPosition(z_servo_up);
        this.release();
    }

    public void rotate(double amount) {x_servo.setPosition(x_servo.getPosition() + amount/degree_constant);}
    public void rotate_z(double amount) {z_servo.setPosition(z_servo.getPosition() + amount/degree_constant);}

    public void reset_x() {x_servo.setPosition(x_servo_center);}
    public void reset_z() {z_servo.setPosition(z_servo_center);}






}
