package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.teamcode.mechanics.claw.claw;
import org.firstinspires.ftc.teamcode.mechanics.webcam.webcam;

import org.firstinspires.ftc.teamcode.vision.SampleDetectionEdges;
import org.firstinspires.ftc.teamcode.vision.SampleDetectionRect;


@Config
@TeleOp(name="cvTest", group="Driver OP")
public class cvTest extends LinearOpMode {

    claw claw;
    webcam webcam;
    SampleDetectionEdges samplePipeline;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        claw = new claw(this);
        webcam = new webcam(this);
        samplePipeline = new SampleDetectionEdges(telemetry);
        samplePipeline.run_continuous = run_continuous;
        webcam.startStreaming(samplePipeline);

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
                samplePipeline.run_main();
                sleep(500);
                double[] r = samplePipeline.getResult();
                telemetry.addData("r", r);
                claw.rotate(r[2]);
            }

        }


    }

}