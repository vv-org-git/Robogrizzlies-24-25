package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;

public class machine {
    LinearOpMode l;
    public machine(LinearOpMode l0) {l = l0;}

    public void run(robot robot, paths path) {
        l.waitForStart();
        boolean done = false;
        int n_states = path.states.size();
        int current_state = 0;
        if(l.isStopRequested()) return;
        path.states.get(current_state).run();
        l.sleep(500);
        while (l.opModeIsActive() && !l.isStopRequested() && current_state < n_states) {
            l.sleep(20);
            robot.movement.odo.bulkUpdate();
            path.states.get(current_state).run();
            if (!robot.is_busy()) {
                current_state += 1;
                path.states.get(current_state).run();
            }
        }
    }

}