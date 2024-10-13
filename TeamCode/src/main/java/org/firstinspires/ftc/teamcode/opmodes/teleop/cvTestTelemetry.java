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
@TeleOp(name="cvTestTelemetry", group="Driver OP")
public class cvTestTelemetry extends LinearOpMode {

    webcam webcam;
    SampleDetection samplePipeline;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        webcam = new webcam(this);
        samplePipeline = new SampleDetection(telemetry);
        webcam.startStreaming(samplePipeline);

        samplePipeline.run_continuous = run_continuous;

        waitForStart();
        while (!isStopRequested()) {
            if (gamepad1.right_trigger > 0.2) {
                telemetry.addData("angle", samplePipeline.angle_delta);
                telemetry.addData("x", samplePipeline.x_delta);
                telemetry.addData("y", samplePipeline.y_delta);
                telemetry.update();

            }

        }


    }

}