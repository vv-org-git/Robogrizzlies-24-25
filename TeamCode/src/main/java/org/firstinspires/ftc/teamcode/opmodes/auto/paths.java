package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.robot.robot;

import java.util.ArrayList;

public class paths {
    ArrayList<trajectory> states;
    robot robot;
    public paths(robot r) {
        robot = r;
        states = new ArrayList<>();
    }
    public void place_specimen_1() {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsync(0, 24,0));
        t.add(() -> robot.arm.high_bar());
        t.add(() -> robot.claw.high_bar());

        states.add(t);
    }
    public void place_specimen_2(int r) {
        trajectory t = new trajectory();
        t.add(() -> robot.movement.moveToAsync(r, 24,0));
        t.add(() -> robot.arm.high_bar());
        t.add(() -> robot.claw.high_bar());

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
        t.add(() -> robot.movement.moveToAsync(-60, 24,0));
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
    public void place_block_observation() {
        trajectory t = new trajectory();
        t.add(() -> robot.arm.reverse_ground());
        t.add(() -> robot.claw.ground_pick_up());

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
