package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.robot.robot;

import java.util.ArrayList;

@Config
public class paths {
    ArrayList<trajectory> states;
    robot robot;
    public static double specimen_forward = 12;
    public paths(robot r) {
        robot = r;
        states = new ArrayList<>();
    }



}