package org.firstinspires.ftc.teamcode.mechanics.drivetrain.pathmaker;

import com.acmerobotics.dashboard.config.Config;

@Config
public class path_func {
    public double[] coefficients;
    public double length = 0;
    public path_func(double[] coefficients) {
        this.coefficients = coefficients;

    }
    public double[] tangent(double position) {
        double val = 0;
        for (int i = 1; i < coefficients.length; i++) {
            val += coefficients[i] * i * Math.pow(position,i - 1);
        }
        return new double[] {1, val};
    }

    public double[] value(double position) {
        double val = 0;
        for (int i = 0; i < coefficients.length; i++) {
            val += coefficients[i] * Math.pow(position,i);
        }
        return new double[] {1, val};
    }
    public double angle_vel(double position) {
        double[] v_t = this.tangent(position);
        double[] v_v = this.value(position);

        double dp = v_t[0] * v_v[0] + v_t[1]*v_v[1];

        double a_cos = dp/(magnitude(v_t)*magnitude(v_v));
        return Math.acos(a_cos);
    }
    public double magnitude(double[] x) {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            sum += Math.pow(x[i], 2);
        }
        return Math.pow(sum, 0.5);

    }
}
