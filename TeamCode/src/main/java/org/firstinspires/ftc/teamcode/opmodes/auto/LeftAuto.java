package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
public class LeftAuto extends LinearOpMode {
    public void runOpMode() { //static
        machine machine = new machine(this);
        robot r = new robot(this, -12, 9,0);
        paths path = new paths(r);
        path.place_specimen_1();
        path.release();

        path.move_ground_sample_outer();
        path.bite();
        path.move_to_basket_with_block();
        path.release();

        path.move_ground_sample_middle();
        path.bite();
        path.move_to_basket_with_block();
        path.release();

        path.move_ground_sample_inner();
        path.bite();
        path.move_to_basket_with_block();
        path.release();

        path.park_left();

        machine.run( true, r, path);
    }
}