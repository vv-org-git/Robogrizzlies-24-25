package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.config.Config;
import org.firstinspires.ftc.teamcode.robot.robot;
@Config
@TeleOp(name="mainTeleOp", group="Driver OP")
public class mainTeleOpRED extends LinearOpMode {
    public static double AUTO_END_X = 63;
    public static double AUTO_END_Y = 9;
    public static double AUTO_END_Z = 0;
    robot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new robot(this, AUTO_END_X, AUTO_END_Y, AUTO_END_Z, false);
        long init_time = System.currentTimeMillis();
        controls control = new controls(robot,this, init_time);
        waitForStart();
        //Initalization goes here
        while (!isStopRequested()) {
            control.action();
            telemetry.addData("RedValue", robot.intake.rV());
            telemetry.addData("BlueValue", robot.intake.bV());
            telemetry.addData("GreenValue", robot.intake.gV());
            telemetry.addData("AlphaValue", robot.intake.aV());
        }
    }
}