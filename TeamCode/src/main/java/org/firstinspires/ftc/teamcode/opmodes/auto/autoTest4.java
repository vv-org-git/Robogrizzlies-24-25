package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.Pose2D;
import org.firstinspires.ftc.teamcode.robot.robot;

@Autonomous
@Config
public class autoTest4 extends LinearOpMode {
    public static String direction = "X";
    public void runOpMode() { //static
        robot r = new robot(this, 0, 0,0, false);
        waitForStart();
        int calls = 0;
        long time = System.currentTimeMillis();
        paths path = new paths(r);
        path.tune_pid(direction);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();
        path.states.get(0).run();
        while (!isStopRequested()) {

                Pose2D p =  r.movement.odo.getPosition();
                double x_f = p.getX(DistanceUnit.INCH);
                double y_f = p.getY(DistanceUnit.INCH);
                double h_f = p.getHeading(AngleUnit.DEGREES);
            dashboardTelemetry.addData("x", x_f);
            dashboardTelemetry.addData("y", y_f);
            dashboardTelemetry.addData("h", h_f);
            dashboardTelemetry.addData("FL", r.movement.fl.getPower());
            dashboardTelemetry.addData("BL", r.movement.fl.getPower());
            dashboardTelemetry.addData("BR", r.movement.fl.getPower());
            dashboardTelemetry.addData("FR", r.movement.fl.getPower());
            dashboardTelemetry.addData("xp", r.movement.x_pow_t);
            dashboardTelemetry.addData("yp", r.movement.y_pow_t);
            dashboardTelemetry.addData("hp", r.movement.h_pow_t);
            dashboardTelemetry.addData("calls", calls);
            dashboardTelemetry.addData("hertz", 1000.0 * calls/( System.currentTimeMillis() - time));
            dashboardTelemetry.addData("busy", r.is_busy());

            dashboardTelemetry.update();

                path.states.get(0).run();



        }
    }
}