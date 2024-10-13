package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.teamcode.mechanics.claw.claw;
import org.firstinspires.ftc.teamcode.mechanics.arm.arm;
import org.firstinspires.ftc.teamcode.mechanics.webcam.webcam;
import org.firstinspires.ftc.teamcode.robot.robot;

import org.firstinspires.ftc.teamcode.mechanics.drivetrain.movement;

import org.firstinspires.ftc.teamcode.vision.SampleDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


@Config
@TeleOp(name="clawTest", group="Driver OP")
public class clawTest extends LinearOpMode {
    claw claw;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        claw = new claw(this);


        waitForStart();
        while (!isStopRequested()) {
            if (gamepad1.a ) {
                claw.x_test();
            }
            if (gamepad1.b ) {
                claw.z_test();
            }
            if (gamepad1.x ) {
                claw.release();
            }
            if (gamepad1.y ) {
                claw.bite();
            }

        }


    }

}