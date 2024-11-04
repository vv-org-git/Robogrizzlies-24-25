package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.Pose2D;

import org.firstinspires.ftc.teamcode.mechanics.drivetrain.movement;

import org.firstinspires.ftc.teamcode.vision.SampleDetectionRect;


@Config
@TeleOp(name="driveTest", group="Driver OP")
public class driveTest extends LinearOpMode {

    movement movement;
    SampleDetectionRect samplePipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        movement = new movement(this, 0, 0, 0);
        long init_time = System.currentTimeMillis();
        waitForStart();
        while (!isStopRequested()) {
            if (gamepad1.left_stick_button) {
                movement.moveToAsync(-48, 24,-135);
                continue;
            }
            else if (gamepad1.right_stick_button) {
                long x = (long) 24 * (System.currentTimeMillis()-init_time)/(60*2*1000);
                movement.moveToAsync(x, 24,-135);
                continue;
            }
            else if (gamepad1.left_bumper) {
                movement.moveToAsync(48, 24,0);
                continue;
            }
            else if (gamepad1.right_bumper) {
                movement.moveToAsync(12 , 24,0);
                continue;
            }
            movement.move(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            Pose2D p = movement.odo.getPosition();
            telemetry.addData("x", p.getX(DistanceUnit.INCH));
            telemetry.addData("y", p.getY(DistanceUnit.INCH));
            telemetry.addData("h", p.getHeading(AngleUnit.DEGREES));
        }


    }

}