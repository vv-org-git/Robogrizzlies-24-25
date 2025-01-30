package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
public class autoTest2 extends LinearOpMode {
    public void runOpMode() { //static
        machine machine = new machine(this);
        robot r = new robot(this, 0, 0,0, false);
        paths path = new paths(r);

        path.place_specimen_test2();
        path.rotate2();



        //path.strafe_forward();
        machine.run( true, r, path);
    }
}