package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
public class RightAuto extends LinearOpMode {
    public void runOpMode() { //static
        machine machine = new machine(this);
        robot r = new robot(this,0, 0,0, false);
        paths path = new paths(r);
        path.place_specimen_1();
        path.release();
        //specimen pick up
        path.move_ground_sample_inner_right();
        path.bite();
        path.place_block_observation();
        path.release();
        path.move_ground_sample_middle_right();
        path.bite();
        path.place_block_observation();
        path.move_ground_sample_outer_right();
        path.bite();
        path.place_block_observation();
        path.release();

        //cycle specimens
        path.claw_specimen_pick_up();
        path.place_specimen_2(4);
        path.release();
        path.claw_specimen_pick_up();
        path.place_specimen_2(7);
        path.release();
        path.claw_specimen_pick_up();
        path.place_specimen_2(10);
        path.release();
        path.claw_specimen_pick_up();
        path.place_specimen_2(13);
        path.release();

        path.park_right();
        machine.run( false, r, path );

    }
}