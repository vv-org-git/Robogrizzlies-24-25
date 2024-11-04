package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.teamcode.mechanics.claw.claw;
import org.firstinspires.ftc.teamcode.mechanics.webcam.webcam;

import org.firstinspires.ftc.teamcode.vision.SampleDetectionRect;


@Config
@TeleOp(name="cvTest", group="Driver OP")
public class cvTest extends LinearOpMode {

    claw claw;
    webcam webcam;
    SampleDetectionRect samplePipeline;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        claw = new claw(this);
        webcam = new webcam(this);
        samplePipeline = new SampleDetectionRect(telemetry);
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