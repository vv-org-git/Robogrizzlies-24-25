package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.robot.robot;

import java.util.ArrayList;

@Config
public class paths {
    ArrayList<trajectory> states;
    robot robot;
    public static double basket_x = 12;
    public static double basket_z = 12;
    public static double basket_heading = 135;
    public static double bar_x = 15;
    public static double bar_y = 15;
    public static double bar_heading = 90;

    public paths(robot r) {
        robot = r;
        states = new ArrayList<>();
    }
    public void GoSub(){
        trajectory t = new trajectory();
        t.add(() -> robot.movement.move(0, 24,0));
    }
    //webacm
    public void orient(){

    }
    public void GetSub(){
        trajectory t = new trajectory();
        t.add(() -> robot.arm.inTank());
        t.add(() -> robot.intake.takeIn());
    }

    public void stop(){
        trajectory t = new trajectory();
        t.add(() -> robot.stop(35));
    }
    public void placeBasket(){
        trajectory t = new trajectory();
        t.add(() -> robot.movement.move(basket_x, basket_z,basket_heading));
    }
    public void TouchBar(){
        trajectory t = new trajectory();
        t.add(() -> robot.movement.move(bar_x, bar_y,bar_heading));
    }
}