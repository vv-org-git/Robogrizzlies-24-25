package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.Pose2D;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
@Config
@TeleOp(name="deadwheelTest", group="Driver OP")
public class deadwheelTest extends LinearOpMode {

    public static boolean reverse = true;
    public static String var_name = "BR";

    public static int ticks = 100;

    DcMotor motor;
    public GoBildaPinpointDriver odo;


    @Override
    public void runOpMode() throws InterruptedException {
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "imu");
        odo.setOffsets(-84.0, -168.0);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
        odo.setPosition(new Pose2D(DistanceUnit.INCH, 10, 0, AngleUnit.DEGREES, 0));
        telemetry.addData("test1w321", "p.getX(DistanceUnit.INCH)");

        waitForStart();
        telemetry.addData("helloo", "p.getX(DistanceUnit.INCH)");

        while(!isStopRequested()) {
            odo.bulkUpdate();
            Pose2D p = odo.getPosition();
            telemetry.addData("x", p.getX(DistanceUnit.INCH));
            telemetry.addData("x", odo.getPosX());
            telemetry.addData("t", System.currentTimeMillis());
            telemetry.addData("y", p.getY(DistanceUnit.INCH));
            telemetry.addData("z", p.getHeading(AngleUnit.DEGREES));
            telemetry.update();
        }


    }
}