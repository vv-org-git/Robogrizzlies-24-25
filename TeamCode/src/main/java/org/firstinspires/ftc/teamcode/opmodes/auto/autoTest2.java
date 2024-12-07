package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
@Config
public class autoTest2 extends LinearOpMode {
    public static int x = 10;
    public static int y = 0;
    public static int h = 0;

    public void runOpMode() { //static
        machine machine = new machine(this);
        robot r = new robot(this, 0, 0,0, false);
        paths path = new paths(r);
        waitForStart();
        while (!isStopRequested()) {
            r.movement.moveToAsync2(x,y,h);
        }
    }
}