package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.robot.robot;

import org.firstinspires.ftc.teamcode.vision.SampleDetectionRect;


@Config
@TeleOp(name="mainTeleOp", group="Driver OP")
public class mainTeleOp extends LinearOpMode {
    public static double AUTO_END_X = 10;
    public static double AUTO_END_Y = 10;
    public static double AUTO_END_Z = 10;

    robot robot;
    SampleDetectionRect samplePipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new robot(this, AUTO_END_X, AUTO_END_Y, AUTO_END_Z);
        samplePipeline = new SampleDetectionRect(telemetry);
        robot.webcam.startStreaming(samplePipeline);
        long init_time = System.currentTimeMillis();
        controls control = new controls(robot, this,init_time, samplePipeline);

        waitForStart();
        while (!isStopRequested()) {
            control.action();
        }


    }

}