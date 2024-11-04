package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.teamcode.mechanics.claw.claw;


@Config
@TeleOp(name="clawTest", group="Driver OP")
public class clawTest extends LinearOpMode {
    claw claw;
    public static boolean run_continuous = true;
    @Override
    public void runOpMode() throws InterruptedException {
        claw = new claw(this);


        waitForStart();
        while (!isStopRequested()) {
            if (gamepad1.a ) {
                claw.x_test();
            }
            if (gamepad1.b ) {
                claw.z_test();
            }
            if (gamepad1.x ) {
                claw.release();
            }
            if (gamepad1.y ) {
                claw.bite();
            }

        }


    }

}