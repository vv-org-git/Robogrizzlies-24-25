package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
public class main_left_auto extends LinearOpMode {
    public void runOpMode() { //static
        machine machine = new machine(this);
        robot r = new robot(this, -12, 9,0, false);
        paths path = new paths(r);
        path.place_specimen_1();

        machine.run( true, r, path);
    }
}