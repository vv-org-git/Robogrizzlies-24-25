package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;

public class machine {
    LinearOpMode l;
    public machine(LinearOpMode l0) {l = l0;}

    public void run(boolean left) {
        robot robot = new robot(l);
        if (left) {
            robot.movement.init_x_offset = - 12.0;
        }
        paths path = new paths(robot);
        path.place_specimen_1();
        path.open_claw();
        int n_states = path.states.size();
        int current_state = 0;
        boolean first_achieved = false;
        if(l.isStopRequested()) return;
        path.states.get(current_state).run();
        while (l.opModeIsActive() && !l.isStopRequested() && current_state < n_states) {
            if (!robot.is_busy()) {
                current_state += 1;
            }
            else {
                path.states.get(current_state).run();
            }
        }
    }

}
