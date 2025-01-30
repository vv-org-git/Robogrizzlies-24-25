package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.mechanics.arm.arm;


@Config
@TeleOp(name="armTest", group="Driver OP")
public class armTest extends LinearOpMode {
    arm arm;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        arm = new arm(this);


        waitForStart();
        while (!isStopRequested()) {
          arm.rot0();

        }
    }

}