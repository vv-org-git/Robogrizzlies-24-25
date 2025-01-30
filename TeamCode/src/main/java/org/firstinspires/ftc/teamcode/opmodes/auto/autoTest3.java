package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
@Config
public class autoTest3 extends LinearOpMode {
    public static double x = 0;
    public void runOpMode() { //static
        machine machine = new machine(this);
        robot r = new robot(this, 0, 0,0, false);
        paths path = new paths(r);
        path.place_specimen_test();
        path.drop_specimen();
        machine.run( true, r, path);
    }
}