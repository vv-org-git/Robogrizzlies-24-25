package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;
import org.firstinspires.ftc.teamcode.vision.SampleDetectionEdges;
import org.firstinspires.ftc.teamcode.vision.SampleDetectionRect;

public class control2 {
    robot robot;
    LinearOpMode op;
    public int left = -1;
    long init_time;
    SampleDetectionEdges samplePipeline;
    public control2(robot r, LinearOpMode l, long t, SampleDetectionEdges s){
        robot = r;
        op = l;
        init_time = t;
        samplePipeline = s;
    }
    public void action() {
        robot.movement.odo.bulkUpdate();

        /*
        if (op.gamepad2.a) {
            samplePipeline.setColor(0);
        }
        if (op.gamepad2.y) {
            samplePipeline.setColor(1);
        }
        if (op.gamepad2.x) {
            samplePipeline.setColor(2);
        }*/
        //Automations
        if (op.gamepad2.left_stick_button) {
            robot.arm.basket();
            robot.movement.moveToAsync(-48, 24,-135);
            robot.claw.basket();
            return;
        }
        else if (op.gamepad2.right_stick_button) {
            long x = (long) 24 * (System.currentTimeMillis()-init_time)/(60*2*1000);
            robot.arm.high_bar();
            robot.movement.moveToAsync(x * left, 24,0);
            robot.claw.high_bar();
            return;
        }
        else if (op.gamepad2.right_stick_button && op.gamepad1.start) {
            robot.claw.high_bar();
            robot.claw.reset_x();
            robot.arm.reverse_ground();
            robot.claw.release();
            robot.movement.moveToAsync(48, 24,0);
            return;
        }
        else if (op.gamepad2.left_stick_button && op.gamepad1.start) {
            long x = (long) 24 * (System.currentTimeMillis()-init_time)/(60*2*1000);
            robot.arm.ground();
            robot.claw.release();
            robot.claw.zDown();
            robot.movement.moveToAsync(x * left, 24,0);
            return;
        }
        else if (op.gamepad2.left_bumper) {
            robot.claw.rotate(-5);
            return;
        }
        else if (op.gamepad2.right_bumper) {
            robot.claw.rotate(5);
            return;
        }

        robot.movement.move(op.gamepad1.left_stick_x, op.gamepad1.left_stick_y, op.gamepad1.right_stick_x);
        //driver control
            /*
        if (op.gamepad1.left_bumper && op.gamepad1.start) {
            robot.claw.rotate(1);
        }
        if (op.gamepad1.right_bumper && op.gamepad1.start) {
            robot.alignment(samplePipeline);
        }*/
        if (op.gamepad2.right_trigger > 0.3) {
            robot.claw.bite();
        }
        if (op.gamepad2.left_trigger > 0.3) {
            robot.claw.release();
        }
        if (op.gamepad2.y) {
            robot.arm.high_bar();
            robot.claw.reset_x();
            robot.claw.high_bar();
        }
        if (op.gamepad2.a) {
            robot.arm.basket();
            robot.claw.reset_x();
            robot.claw.basket();
        }
        if (op.gamepad2.x) {
            robot.arm.reverse_ground();
            robot.claw.zUp();
            robot.claw.reset_x();
        }
        if (op.gamepad2.b) {
            robot.arm.up();
        }
        if (op.gamepad2.dpad_left) {
            robot.claw.reset_x();
        }
        if (op.gamepad2.dpad_right) {
            robot.claw.reset_z();
        }
        if (op.gamepad2.dpad_up) {
            robot.claw.zUp();

        }
        if (op.gamepad2.dpad_down) {
            robot.claw.zDown();

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
