package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
@Config
@TeleOp(name="servoTest", group="Driver OP")
public class servoProgramming extends LinearOpMode {
    public static double position = 1.0;
    public static double position2 = -1.0;
    public static String name = "claw_x";
    Servo servo;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, name);
        waitForStart();
        servo.setPosition(position2);
        while (!isStopRequested()){
            if (gamepad1.left_trigger > 0.3){
                servo.setPosition(position2);
            }
            if (gamepad1.right_trigger > 0.3){
                servo.setPosition(position);
            }
        }
    }
}