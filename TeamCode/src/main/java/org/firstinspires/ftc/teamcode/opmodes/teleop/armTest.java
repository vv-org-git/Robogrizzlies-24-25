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
@TeleOp(name="armTest", group="Driver OP")
public class armTest extends LinearOpMode {
    arm arm;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        arm = new arm(this);


        waitForStart();
        while (!isStopRequested()) {
            if (gamepad1.a ) {
                arm.testExtenderTicks();
            }
            if (gamepad1.b ) {
                arm.testRotatorTicks();
            }
            if (gamepad1.right_trigger > 0.1) {
                arm.extend();
            }
            if (gamepad1.left_trigger > 0.1) {
                arm.retract();
            }
            if (gamepad1.dpad_up) {
                arm.down();
            }
            if (gamepad1.dpad_down) {
                arm.down();
            }

        }


    }

}