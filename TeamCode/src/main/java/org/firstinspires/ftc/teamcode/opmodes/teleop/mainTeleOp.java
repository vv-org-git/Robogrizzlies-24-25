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
@TeleOp(name="mainTeleOp", group="Driver OP")
public class mainTeleOp extends LinearOpMode {

    robot robot;
    SampleDetection samplePipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new robot(this);
        samplePipeline = new SampleDetection(telemetry);
        robot.webcam.startStreaming(samplePipeline);
        long init_time = System.currentTimeMillis();
        int left = -1;
        waitForStart();
        while (!isStopRequested()) {
            if (gamepad2.x) {
                left = 1;
            }

            //Automations
            if (gamepad1.left_stick_button) {
                robot.arm.basket();
                robot.movement.moveToAsync(-48, 24,-135);
                robot.claw.basket();
                continue;
            }
            else if (gamepad1.right_stick_button) {
                long x = (long) 24 * (System.currentTimeMillis()-init_time)/(60*2*1000);
                robot.arm.high_bar();
                robot.movement.moveToAsync(x * left, 24,-135);
                robot.claw.high_bar();
                continue;
            }
            else if (gamepad1.left_bumper) {
                robot.arm.reverse_ground();
                robot.claw.ground_pick_up_reverse();
                robot.movement.moveToAsync(48, 24,0);
                continue;
            }
            else if (gamepad1.right_bumper) {
                robot.arm.ground();
                robot.claw.reset_z();
                robot.claw.reset_x();
                robot.movement.moveToAsync(12 * left, 24,0);
                continue;
            }

            robot.movement.move(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            //driver control
            if (gamepad1.left_bumper && gamepad1.start) {
                robot.claw.rotate(1);
            }
            if (gamepad1.right_bumper && gamepad1.start) {
                robot.claw.rotate(-1);
            }
            if (gamepad1.right_trigger > 0.3) {
                robot.claw.bite();
            }
            if (gamepad1.left_trigger > 0.3) {
                robot.claw.release();
            }
            if (gamepad1.y) {
                robot.claw.reset_z();
                robot.arm.extend();
            }
            if (gamepad1.a) {
                robot.claw.reset_z();
                robot.claw.reset_x();
                robot.arm.retract();
            }
            if (gamepad1.x) {
                robot.claw.reset_z();
                robot.arm.down();
            }
            if (gamepad1.b) {
                robot.arm.up();
            }
            if (gamepad1.dpad_left) {
                robot.claw.reset_x();
            }
            if (gamepad1.dpad_right) {
                robot.claw.reset_z();
            }
            if (gamepad1.dpad_up) {
                robot.alignment(samplePipeline);

            }

            //Precise Control

            if (gamepad1.guide) {
                if (gamepad1.y) {
                    robot.arm.setRotatorPower(0.8);
                }
                else if (gamepad1.a) {
                    robot.arm.setRotatorPower(-0.8);
                }
                if (gamepad1.b) {
                    robot.arm.setExtenderPower(0.8);
                }
                else if (gamepad1.a) {
                    robot.arm.setExtenderPower(-0.8);
                }

                if (gamepad1.dpad_up) {
                    robot.claw.rotate_z(0.1);
                }
                if (gamepad1.dpad_up) {
                    robot.claw.rotate_z(-0.1);
                }
            }

        }


    }

}