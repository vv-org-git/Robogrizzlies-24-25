package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.mechanics.webcam.webcam;

import org.firstinspires.ftc.teamcode.vision.SampleDetectionEdges;


@Config
@TeleOp(name="cvTestTelemetry", group="Driver OP")
public class cvTestTelemetry extends LinearOpMode {

    webcam webcam;
    SampleDetectionEdges samplePipeline;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        webcam = new webcam(this);
        samplePipeline = new SampleDetectionEdges(telemetry);
        webcam.startStreaming(samplePipeline);

        samplePipeline.run_continuous = run_continuous;

        waitForStart();
        while (!isStopRequested()) {
            if (gamepad1.a) {
                samplePipeline.setColor(0);
            }
            if (gamepad1.y) {
                samplePipeline.setColor(1);
            }
            if (gamepad1.b) {
                samplePipeline.setColor(2);
            }
            if (gamepad1.right_trigger > 0.2) {

                telemetry.addData("angle", samplePipeline.angle_delta);
                telemetry.addData("x", samplePipeline.x_delta);
                telemetry.addData("y", samplePipeline.y_delta);
                telemetry.update();

            }

        }


    }

}