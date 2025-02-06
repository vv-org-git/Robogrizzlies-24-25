package org.firstinspires.ftc.teamcode.mechanics.drivetrain;

import com.acmerobotics.dashboard.config.Config;

@Config
public class PIDConstants {
    public static double KpX = 1;
    public static double KpY = 1;
    public static double KpR = 1;

    public static double KiX = 1;
    public static double KiY = 1;
    public static double KiR = 1;


    public static double KdX = 1;
    public static double KdY = 1;
    public static double KdR = 1;

    public static double integralSumMaxX = 1;
    public static double integralSumMaxY = 1;
    public static double integralSumMaxR = 1;

    public static double stability_threshX = 1;
    public static double stability_threshY = 1;
    public static double stability_threshR = 1;

    public static double lowPassGainX = 1;
    public static double lowPassGainY = 1;
    public static double lowPassGainR = 1;


    public static double pidXSaturation = 0.1;
    public static double pidYSaturation = 0.1;
    public static double pidHSaturation = 0.1;

}
