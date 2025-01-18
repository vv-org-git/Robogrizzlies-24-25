package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.Pose2D;
import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
@Config
public class autoTest4 extends LinearOpMode {
    public static int x = 10;
    public static int y = 0;
    public static int h = 0;

    public void runOpMode() { //static
        robot r = new robot(this, 0, 0,0, false);
        waitForStart();
        int calls = 0;
        long time = System.currentTimeMillis();
        paths path = new paths(r);
        path.place_specimen_test();
        path.states.get(0).run();
        while (!isStopRequested()) {
                calls += 1;
                Pose2D p =  r.movement.odo.getPosition();
                double x_f = p.getX(DistanceUnit.INCH);
                double y_f = p.getY(DistanceUnit.INCH);
                double h_f = p.getHeading(AngleUnit.DEGREES);
                telemetry.addData("x", x_f);
                telemetry.addData("y", y_f);
                telemetry.addData("h", h_f);
                telemetry.addData("calls", calls);
                telemetry.addData("hertz", 1000.0 * calls/( System.currentTimeMillis() - time));
                telemetry.addData("busy", r.is_busy());

                telemetry.update();

                path.states.get(0).run();



        }
    }
}