package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;
import org.firstinspires.ftc.teamcode.vision.SampleDetectionEdges;
import org.firstinspires.ftc.teamcode.vision.SampleDetectionRect;
//import claw from claw;

public class main_controls {
    robot robot;
    LinearOpMode op;
    long init_time;
    SampleDetectionEdges samplePipeline;
    public main_controls(robot r, LinearOpMode l, long t, SampleDetectionEdges s){
        robot = r;
        op = l;
        init_time = t;
        samplePipeline = s;
    }

    public void action() {
        robot.movement.odo.bulkUpdate();
        robot.movement.move(op.gamepad1.left_stick_x, op.gamepad1.left_stick_y, op.gamepad1.right_stick_x);
        if (op.gamepad1.dpad_up) {
            robot.claw.zUp();
        }
        if (op.gamepad1.dpad_down) {
            robot.claw.zDown();
        }
        if(op.gamepad1.y){
            robot.arm.rot0();
        }
        if(op.gamepad1.b){
            robot.arm.rot1();
        }
        if(op.gamepad1.a){
            robot.arm.rot2();
        }
        if(op.gamepad1.x){
            robot.arm.rot3();
        }
        if (op.gamepad1.dpad_left) {
            robot.claw.rotate_x_left();
        }
        if (op.gamepad1.dpad_right) {
            robot.claw.rotate_x_right();
        }
        if(op.gamepad1.left_bumper){
            robot.arm.extend();
        }
        if(op.gamepad1.right_bumper){
            robot.arm.retract();
        }

        if (op.gamepad1.right_trigger>0.3) {
            robot.claw.bite();
            return;
        }
        if (op.gamepad1.left_trigger > 0.3) {
            robot.claw.release();
        }
        if(op.gamepad1.right_stick_button && op.gamepad1.left_stick_button){
            robot.arm.retract_reset();
        }
        //
        if(op.gamepad1.right_stick_button && op.gamepad1.left_stick_button){
            robot.arm.retract_negative();
        }
    }

}
