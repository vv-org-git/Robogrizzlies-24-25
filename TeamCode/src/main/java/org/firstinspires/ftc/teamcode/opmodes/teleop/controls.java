package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;
import org.firstinspires.ftc.teamcode.vision.SampleDetectionRect;

public class controls {
    robot robot;
    LinearOpMode op;
    public int left = -1;
    long init_time;
    SampleDetectionRect samplePipeline;
    public controls(robot r, LinearOpMode l, long t, SampleDetectionRect s){
        robot = r;
        op = l;
        init_time = t;
        samplePipeline = s;
    }
    public void action() {
        if (op.gamepad2.x) {
            left = 1;
        }

        //Automations
        if (op.gamepad1.left_stick_button) {
            robot.arm.basket();
            robot.movement.moveToAsync(-48, 24,-135);
            robot.claw.basket();
            return;
        }
        else if (op.gamepad1.right_stick_button) {
            long x = (long) 24 * (System.currentTimeMillis()-init_time)/(60*2*1000);
            robot.arm.high_bar();
            robot.movement.moveToAsync(x * left, 24,-135);
            robot.claw.high_bar();
            return;
        }
        else if (op.gamepad1.left_bumper) {
            robot.arm.reverse_ground();
            robot.claw.ground_pick_up_reverse();
            robot.movement.moveToAsync(48, 24,0);
            return;
        }
        else if (op.gamepad1.right_bumper) {
            robot.arm.ground();
            robot.claw.reset_z();
            robot.claw.reset_x();
            robot.movement.moveToAsync(12 * left, 24,0);
            return;
        }

        robot.movement.move(op.gamepad1.left_stick_x, op.gamepad1.left_stick_y, op.gamepad1.right_stick_x);
        //driver control
        if (op.gamepad1.left_bumper && op.gamepad1.start) {
            robot.claw.rotate(1);
        }
        if (op.gamepad1.right_bumper && op.gamepad1.start) {
            robot.claw.rotate(-1);
        }
        if (op.gamepad1.right_trigger > 0.3) {
            robot.claw.bite();
        }
        if (op.gamepad1.left_trigger > 0.3) {
            robot.claw.release();
        }
        if (op.gamepad1.y) {
            robot.claw.reset_z();
            robot.arm.extend();
        }
        if (op.gamepad1.a) {
            robot.claw.reset_z();
            robot.claw.reset_x();
            robot.arm.retract();
        }
        if (op.gamepad1.x) {
            robot.claw.reset_z();
            robot.arm.down();
        }
        if (op.gamepad1.b) {
            robot.arm.up();
        }
        if (op.gamepad1.dpad_left) {
            robot.claw.reset_x();
        }
        if (op.gamepad1.dpad_right) {
            robot.claw.reset_z();
        }
        if (op.gamepad1.dpad_up) {
            robot.alignment(samplePipeline);

        }

        //Precise Control

        if (op.gamepad1.guide) {
            if (op.gamepad1.y) {
                robot.arm.setRotatorPower(0.8);
            }
            else if (op.gamepad1.a) {
                robot.arm.setRotatorPower(-0.8);
            }
            if (op.gamepad1.b) {
                robot.arm.setExtenderPower(0.8);
            }
            else if (op.gamepad1.a) {
                robot.arm.setExtenderPower(-0.8);
            }

            if (op.gamepad1.dpad_up) {
                robot.claw.rotate_z(0.1);
            }
            if (op.gamepad1.dpad_up) {
                robot.claw.rotate_z(-0.1);
            }
        }
    }

}
