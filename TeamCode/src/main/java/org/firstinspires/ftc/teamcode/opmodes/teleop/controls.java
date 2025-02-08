package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;
import org.firstinspires.ftc.teamcode.vision.SampleDetectionEdges;
import org.firstinspires.ftc.teamcode.vision.SampleDetectionRect;

public class controls {
    robot robot;
    LinearOpMode op;
    public int left = -1;
    long init_time;
    SampleDetectionEdges samplePipeline;
    public controls(robot r, LinearOpMode l, long t, SampleDetectionEdges s){
        robot = r;
        op = l;
        init_time = t;
        samplePipeline = s;
    }
    public void action() {
        robot.movement.odo.update();

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
        /*if (op.gamepad1.left_stick_button) {
            robot.arm.basket();
            robot.movement.moveToAsync(-48, 24,-135);
            robot.claw.basket();
            return;
        }
        else if (op.gamepad1.right_stick_button) {
            long x = (long) 24 * (System.currentTimeMillis()-init_time)/(60*2*1000);
            robot.arm.high_bar();
            robot.movement.moveToAsync(x * left, 24,0);
            robot.claw.high_bar();
            return;
        }
        else if (op.gamepad1.right_stick_button && op.gamepad1.start) {
            robot.claw.high_bar();
            robot.claw.reset_x();
            robot.arm.reverse_ground();
            robot.claw.release();
            robot.movement.moveToAsync(48, 24,0);
            return;
        }
        else if (op.gamepad1.left_stick_button && op.gamepad1.start) {
            long x = (long) 24 * (System.currentTimeMillis()-init_time)/(60*2*1000);
            robot.arm.ground();
            robot.claw.release();
            robot.claw.zDown();
            robot.movement.moveToAsync(x * left, 24,0);
            return;
        }
        if (op.gamepad1.left_bumper) {
            robot.arm.setExtenderPower(-1);
        }
        else if (op.gamepad1.right_bumper) {
            robot.arm.setExtenderPower(1);
        }
        else {
            robot.arm.setExtenderPower(0);

        }*/


        robot.movement.move(op.gamepad1.left_stick_x, op.gamepad1.left_stick_y, op.gamepad1.right_stick_x);
        //driver control
            /*
        if (op.gamepad1.left_bumper && op.gamepad1.start) {
            robot.claw.rotate(1);
        }
        if (op.gamepad1.right_bumper && op.gamepad1.start) {
            robot.alignment(samplePipeline);
        }*/
        if (op.gamepad1.right_trigger > 0.3) {
            robot.claw.bite();
        }
        if(op.gamepad1.left_trigger>0.3 && op.gamepad1.left_stick_button) {
            robot.claw.high_bar_place();
            op.sleep(100);
            robot.claw.release();
        }
        else if (op.gamepad1.left_trigger > 0.3) {
            robot.claw.release();
        }

        if (op.gamepad1.y) {
            robot.arm.rot0();
            robot.claw.reset_x();
            robot.claw.high_bar();
        }
        if (op.gamepad1.y && op.gamepad1.start) {
            robot.ground_pick_up_parallel();
        }
        if (op.gamepad1.a) {
            robot.arm.rot1();
            robot.claw.reset_x();
            robot.claw.basket();
        }
        if (op.gamepad1.x) {
            robot.arm.rot2();
            robot.claw.zUp();
            robot.claw.rotate_x_right();
            robot.claw.release();
        }
        if (op.gamepad1.b) {
            robot.arm.rot3();
            robot.claw.zDown();
            robot.claw.rotate_x_right();
            robot.claw.zUp();
            robot.claw.reset_x();
            robot.claw.release();
        }
        if (op.gamepad1.dpad_left) {
            robot.claw.rotate_x_left();
        }
        if (op.gamepad1.dpad_right) {
            robot.claw.rotate_x_right();
        }
        if(op.gamepad1.dpad_up && op.gamepad1.dpad_down){
            robot.claw.zMax();
        }
        else if (op.gamepad1.dpad_up) {
            robot.claw.zUp();

        }
        else if (op.gamepad1.dpad_down) {
            robot.claw.zDown();

        }
        if(op.gamepad1.left_bumper && op.gamepad1.right_bumper){
            robot.arm.extend_w_pow();
        }
        if(op.gamepad1.left_bumper){
            robot.arm.retract();
        }
        if(op.gamepad1.right_bumper){
            robot.arm.extend();
        }
        if(op.gamepad1.start && op.gamepad1.guide){
            robot.arm.extend();
        }

        //Precise Control

        /*if (op.gamepad1.guide && op.gamepad1.dpad_down) {
            robot.arm.HookMechspool();
        }
        if(op.gamepad1.guide && op.gamepad1.dpad_up){
            robot.arm.spoolup();
        }
         */
    }

}
