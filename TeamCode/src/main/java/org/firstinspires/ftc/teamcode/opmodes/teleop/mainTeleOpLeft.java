package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.robot.robot;

import org.firstinspires.ftc.teamcode.vision.SampleDetectionEdges;


@Config
@TeleOp(name="mainTeleOpLeft", group="Driver OP")
public class mainTeleOpLeft extends LinearOpMode {
    public static double AUTO_END_X = -63;
    public static double AUTO_END_Y = 9;
    public static double AUTO_END_Z = 0;

    robot robot;
    SampleDetectionEdges samplePipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new robot(this, AUTO_END_X, AUTO_END_Y, AUTO_END_Z, false);
        samplePipeline = new SampleDetectionEdges(telemetry);
        //robot.webcam.startStreaming(samplePipeline);
        long init_time = System.currentTimeMillis();
        control2 control = new control2(robot, this,init_time, samplePipeline);

        waitForStart();
        while (!isStopRequested()) {
            control.action();
        }


    }

}