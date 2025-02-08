package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
public class autoTest2 extends LinearOpMode {
    public void runOpMode() { //static
        machine machine = new machine(this);
        robot r = new robot(this, 0, 0,0, false);
        while (!isStopRequested()){
            r.movement.odo.update();
            Pose2D p =  r.movement.odo.getPosition();
            double x_f = p.getX(DistanceUnit.INCH);
            double y_f = p.getY(DistanceUnit.INCH);
            double h_f = p.getHeading(AngleUnit.DEGREES);

            telemetry.addData("x", x_f);
            telemetry.addData("y", y_f);
            telemetry.addData("h", h_f);
            telemetry.update();
        }
    }
}