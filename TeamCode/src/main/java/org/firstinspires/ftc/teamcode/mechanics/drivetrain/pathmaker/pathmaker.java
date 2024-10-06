package org.firstinspires.ftc.teamcode.mechanics.drivetrain.pathmaker;

import com.acmerobotics.dashboard.config.Config;

@Config
public class pathmaker {
    public static int breaking_distance = 12;
    public static int turn_breaking_distance = 18;
    public static double[] linear(double x_f, double x, double y_f, double y, double h_f, double heading){
        double x_vel = -1.0;
        double y_vel = -1.0;
        double h_vel = -1.0;

        if (Math.abs(x_f - x) < breaking_distance) {
            x_vel = (x_f - x) / breaking_distance;
        } else if ((x_f - x) > 0) {
            x_vel = 1.0;
        }
        if (Math.abs(y_f - y) < breaking_distance) {
            y_vel = (y_f - y) / breaking_distance;
        } else if ((y_f - y) > 0) {
            y_vel = 1.0;
        }
        if (Math.abs(h_f - heading) < turn_breaking_distance) {
            h_vel = (h_f - heading) / turn_breaking_distance;
        } else if ((h_f - heading) > 0) {
            h_vel = 1.0;
        }
        return new double[] {x_vel, y_vel, h_vel};
    }

}
