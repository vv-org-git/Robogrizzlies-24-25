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
@TeleOp(name="cvTest", group="Driver OP")
public class cvTest extends LinearOpMode {

    claw claw;
    webcam webcam;
    SampleDetection samplePipeline;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        claw = new claw(this);
        webcam = new webcam(this);
        samplePipeline = new SampleDetection(telemetry);
        samplePipeline.run_continuous = run_continuous;
        webcam.startStreaming(samplePipeline);

        waitForStart();
        while (!isStopRequested()) {
            if (gamepad1.right_trigger > 0.2) {
                claw.rotate(samplePipeline.angle_delta);
            }

        }


    }

}