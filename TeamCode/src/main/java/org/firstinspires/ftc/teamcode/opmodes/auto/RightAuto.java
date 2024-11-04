package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
public class RightAuto extends LinearOpMode {
    public void runOpMode() { //static
        machine machine = new machine(this);
        robot r = new robot(this,12, 0,0);
        paths path = new paths(r);
        path.place_specimen_1();
        path.release();
        path.move_ground_sample_inner_right();
        path.bite();
        path.place_block_observation();
        path.release();
        path.retract();
        path.place_block_observation();
        path.bite();
        path.place_specimen_2(-5);
        path.release();
        machine.run( false, r, path );
    }
}