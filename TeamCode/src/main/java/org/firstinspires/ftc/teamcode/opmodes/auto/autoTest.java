package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mechanics.drivetrain.movement;
import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
public class autoTest extends LinearOpMode {
    public static double x = 10;
    public static double y = 10;
    public static double z = 0;

    public void runOpMode() { //static
        machine machine = new machine(this);
        movement m = new movement(this, 0, 0, 0);
        while (!isStopRequested() && opModeIsActive()){
            m.moveToAsync(x,y,z);
        }

    }
}