package org.firstinspires.ftc.teamcode.opmodes.auto;
import org.firstinspires.ftc.teamcode.robot.robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class RightAutoBLUE extends LinearOpMode {
    public static double x = 12;
    public static double z = 0;
    public static double h = 180;
    public static boolean camera = false;

    public void runOpMode() {
        machine machine = new machine(this);
        robot r = new robot(this, x, z, h, camera, true);
        paths p = new paths(r);
        p.placeBasket(); //preload

        p.GoSub();
        p.orient();
        p.GetSub();
        p.placeBasket();

        p.GoSub();
        p.orient();
        p.GetSub();
        p.placeBasket();

        p.GoSub();
        p.orient();
        p.GetSub();
        p.placeBasket();

        p.GoSub();
        p.orient();
        p.GetSub();
        p.placeBasket();

        p.GoSub();
        p.orient();
        p.GetSub();
        p.placeBasket();

        p.TouchBar();
        machine.run(r, p);
    }
}
