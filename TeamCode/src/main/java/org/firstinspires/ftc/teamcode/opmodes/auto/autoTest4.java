package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
        while (!isStopRequested()) {
            if (r.movement.odo.getPosX() < 10) {
                r.movement.fl.setPower(1.0);
                r.movement.fr.setPower(1.0);
                r.movement.bl.setPower(1.0);
                r.movement.br.setPower(1.0);


            }
        }
    }
}