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
        t.add(() -> robot.arm.extend());
        t.add(() -> robot.arm.high_bar());
        states.add(t);
    }
    public void open_claw() {
        trajectory t = new trajectory();
        t.add(() -> robot.claw.release());
        states.add(t);
    }
}
