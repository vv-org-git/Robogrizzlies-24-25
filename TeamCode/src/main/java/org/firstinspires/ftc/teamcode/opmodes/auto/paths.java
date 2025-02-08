package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.robot.robot;

import java.util.ArrayList;

@Config
public class paths {
    ArrayList<trajectory> states;
    robot robot;
    public static double specimen_forward = 12;
    public paths(robot r) {
        robot = r;
        states = new ArrayList<>();
    }
    public void place_specimen_1() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsyncPID(0, 24,0));
        t.add(() -> robot.arm.rot2());
        t.add(() -> robot.claw.zUp());
        t.add(() -> robot.claw.release());

        states.add(t);
    }
    public void place_basket() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.maxExtend());
        t.add(() -> robot.arm.rot1());
        t.add(() -> robot.claw.reset_x());
        t.add(() -> robot.claw.bite());
        t.add(() -> robot.claw.zDown());

        states.add(t);
    }
    public void reset_arm() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.rot3());
        t.add(() -> robot.arm.retract());
        t.add(() -> robot.claw.reset_x());
        t.add(() -> robot.claw.zDown());

        states.add(t);
    }
    public void drop() {
        trajectory t = new trajectory();
        t.add(() -> robot.claw.release());

        states.add(t);
    }
    public void tune_pid(String direction) {
        trajectory t = new trajectory();
        if (direction.equals("X")) {
            t.add(() -> robot.movement.pidTuneX(24, 0,0));
        }
        else if (direction.equals("Y")) {
            t.add(() -> robot.movement.pidTuneY(0, 24,0));
        }
        else {
            t.add(() -> robot.movement.pidTuneH(0, 0,90));
        }

        states.add(t);
    }
    public void tune_motion(String direction) {
        trajectory t = new trajectory();
        if (direction.equals("X")) {
            t.add(() -> robot.movement.tuneMotionProfileX(24));
        }
        else if (direction.equals("Y")) {
            t.add(() -> robot.movement.tuneMotionProfileXYH(0, 24,0));
        }
        else {
            t.add(() -> robot.movement.tuneMotionProfileXYH(0, 0,90));

        }


        states.add(t);
    }
    public void place_specimen_test() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsyncPID(0, specimen_forward, 0));
        t.add(() -> robot.arm.rot2());
        t.add(() -> robot.claw.zUp());
        t.add(() -> robot.claw.bite());

        states.add(t);
    }
    public void drop_specimen() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsyncPID(0, 24, 0));
        t.add(() -> robot.arm.rot2());
        t.add(() -> robot.claw.zDown());
        t.add(() -> robot.claw.release());

        states.add(t);
    }
    public void place_specimen_test2() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsyncPIDCustomXY(12,12));
        t.add(() -> robot.arm.high_bar());
        t.add(() -> robot.claw.zUp());
        t.add(() -> robot.claw.release());

        states.add(t);
    }
    public void rotate() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsyncHeading(90));

        states.add(t);
    }
    public void rotate2() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsyncPIDCustomH(90));

        states.add(t);
    }

    public void place_specimen_2(int r) {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsync(r, 24,0));
        t.add(() -> robot.claw.bite());
        t.add(() -> robot.arm.high_bar());
        t.add(() -> robot.claw.high_bar());

        states.add(t);
    }
    public void park_right() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsync(63, 9,0));
        t.add(() -> robot.claw.release());
        t.add(() -> robot.arm.retract());

        states.add(t);
    }
    public void park_left() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsync(-63, 9,0));
        t.add(() -> robot.claw.release());
        t.add(() -> robot.arm.retract());

        states.add(t);
    }


    public void move_to_basket_with_block() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.basket());
        t.add(() -> robot.movement.moveToAsync(-48, 24,-135));
        states.add(t);
    }
    public void move_ground_sample_middle() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.ground());
        t.add(() -> robot.claw.release());
        t.add(() -> robot.claw.ground_pick_up());
        t.add(() -> robot.movement.moveToAsync(-58, 24,0));
        states.add(t);
    }
    public void move_ground_sample_inner() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.ground());
        t.add(() -> robot.claw.release());
        t.add(() -> robot.claw.ground_pick_up());
        t.add(() -> robot.movement.moveToAsync(-48, 24,0));
        states.add(t);
    }
    public void move_ground_sample_inner_right() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.ground());
        t.add(() -> robot.claw.release());
        t.add(() -> robot.claw.ground_pick_up());
        t.add(() -> robot.movement.moveToAsync(48, 24,0));
        states.add(t);
    }
    public void strafe_left(double val) {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsync(0, val,0));
        states.add(t);
    }
    public void strafe_forward(double val) {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsync(val, 0,0));
        states.add(t);
    }
    public void move_ground_sample_middle_right() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.ground());
        t.add(() -> robot.claw.release());
        t.add(() -> robot.claw.ground_pick_up());
        t.add(() -> robot.movement.moveToAsync(58, 24,0));
        states.add(t);
    }
    public void move_ground_sample_outer_right() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.setRotPos(0));
        t.add(() -> robot.arm.setExtPos(26));

        t.add(() -> robot.claw.release());
        t.add(() -> robot.claw.setX(-34));
        t.add(() -> robot.movement.moveToAsync(58, 24,68));
        states.add(t);
    }
    public void move_ground_sample_outer() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.setRotPos(0));
        t.add(() -> robot.arm.setExtPos(26));

        t.add(() -> robot.claw.release());
        t.add(() -> robot.claw.setX(34));
        t.add(() -> robot.movement.moveToAsync(-58, 24,68));
        states.add(t);
    }
    public void obs_pick_up_specimen() {
        trajectory t = new trajectory();

        t.add(() -> robot.claw.zUp());
        t.add(() -> robot.claw.reset_x());
        t.add(() -> robot.arm.reverse_ground());
        t.add(() -> robot.claw.release());
        t.add(() -> robot.movement.moveToAsync(48, 24,0));
        states.add(t);
    }
    public void place_block_observation() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.reverse_ground());
        t.add(() -> robot.claw.reset_z());

        states.add(t);
    }
    public void claw_specimen_pick_up() {
        trajectory t = new trajectory();

        t.add(() -> robot.claw.zUp());
        t.add(() -> robot.claw.reset_x());
        t.add(() -> robot.arm.reverse_ground());
        t.add(() -> robot.claw.release());
        t.add(() -> robot.movement.moveToAsync(48, 24,0));

        states.add(t);
    }
    public void sleep(long ms) {
        trajectory t = new trajectory();
        t.add(() -> robot.sleep(ms));
        states.add(t);
    }
    public void bite() {
        trajectory t = new trajectory();
        t.add(() -> robot.claw.bite());
        states.add(t);
    }
    public void release() {
        trajectory t = new trajectory();
        t.add(() -> robot.claw.bite());
        states.add(t);
    }
    public void retract() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.retract());
        states.add(t);
    }



}
