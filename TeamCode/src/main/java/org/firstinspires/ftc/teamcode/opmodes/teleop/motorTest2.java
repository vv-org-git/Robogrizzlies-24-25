package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.acmerobotics.dashboard.config.Config;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
@Config
@TeleOp(name="motorTest2", group="Driver OP")
public class motorTest2 extends LinearOpMode {

    public static boolean reverse = true;
    public static String var_name = "BR";

    public static int ticks = 100;

    DcMotor motor;


    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotor.class, var_name);
        if (reverse) {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }


        waitForStart();
        while(!isStopRequested()) {
            motor.setPower(1.0);
        }

    }
}