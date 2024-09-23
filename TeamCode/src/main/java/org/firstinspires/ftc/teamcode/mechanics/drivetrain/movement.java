package org.firstinspires.ftc.teamcode.mechanics.drivetrain;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.Pose2D;

public class movement {
    GoBildaPinpointDriver odo;
    double oldTime = 0;
    public wheel fl;
    public wheel fr;
    public wheel bl;
    public wheel br;

    public int breaking_distance = 12;
    public int turn_breaking_distance = 18;

    public movement(HardwareMap hardwareMap) {
        odo.setOffsets(-84.0, -168.0);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

        fl = new wheel(hardwareMap, "lr");
        fr = new wheel(hardwareMap, "fr");
        bl = new wheel(hardwareMap, "bl");
        br = new wheel(hardwareMap, "br");
    }
    public void move(double l_x, double l_y, double r_x){
        double horizontal = l_x;
        double vertical = l_y;
        double turn = - r_x;
        fl.setPower(vertical + horizontal + turn);
        fr.setPower(vertical - horizontal - turn);
        bl.setPower(vertical - horizontal + turn);
        br.setPower(vertical + horizontal - turn);
    }
    public void moveTo(double x, double y, double heading) {
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double y_f = p.getY(DistanceUnit.INCH);
        double h_f = p.getHeading(AngleUnit.DEGREES);
        double x_vel = -1.0;
        double y_vel = -1.0;
        double h_vel = -1.0;

        if (Math.abs(x_f - x) < breaking_distance) {
            x_vel = (x_f - x)/breaking_distance;
        }
        else if ((x_f-x) > 0) {
            x_vel = 1.0;
        }

        if (Math.abs(y_f - y) < breaking_distance) {
            y_vel = (y_f - y)/breaking_distance;
        }
        else if ((y_f-y) > 0) {
            y_vel = 1.0;
        }

        if (Math.abs(h_f - heading) < turn_breaking_distance) {
            h_vel = (h_f - heading)/turn_breaking_distance;
        }
        else if ((h_f - heading) > 0) {
            h_vel = 1.0;
        }
        move(x_vel, y_vel, h_vel);

    }

}
