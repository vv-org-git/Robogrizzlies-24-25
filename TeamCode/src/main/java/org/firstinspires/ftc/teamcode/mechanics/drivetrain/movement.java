package org.firstinspires.ftc.teamcode.mechanics.drivetrain;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.Pose2D;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.pathmaker.pathmaker;
@Config
public class movement {
    public GoBildaPinpointDriver odo;
    double oldTime = 0;
    public wheel fl;
    public wheel fr;
    public wheel bl;
    public wheel br;
    LinearOpMode li;


    public static double err = 0.99;
    public static double break_constant = 8.0;

    public static double allowed_x_err = 0.9;
    public static double allowed_y_err = 0.9;
    public static double allowed_h_err = 0.9;
    public static double FR_PERCENT = 1;
    public static double BR_PERCENT = 1;
    public static double FL_PERCENT = 1.0;
    public static double BL_PERCENT = 1;

    public double power = 1.0;
    public static double speed = 0.5;

    public double init_x_offset = 12.0;
    private double prev_vel_x = 0;
    private double prev_vel_y = 0;
    private double prev_vel_h = 0;


    public movement(LinearOpMode l, double x, double y, double z ) {
        li = l;
        l.telemetry.addData("movement", "movement");

        odo = l.hardwareMap.get(GoBildaPinpointDriver.class, "imu");
        odo.setOffsets(-84.0, -168.0);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
        odo.setPosition(new Pose2D(DistanceUnit.INCH, x, y,AngleUnit.DEGREES, z));

        fl = new wheel(l.hardwareMap, "FL", true);
        fr = new wheel(l.hardwareMap, "FR", false);
        bl = new wheel(l.hardwareMap, "BL", false);
        br = new wheel(l.hardwareMap, "BR", true);
    }

    public boolean is_busy(){
        return (fl.getPower() > 0 || fr.getPower() > 0 || bl.getPower() > 0  || br.getPower() > 0 );
    }


    public void move(double l_x, double l_y, double r_x){
        double horizontal = l_x;
        double vertical = -l_y;
        double turn =  r_x;
        li.telemetry.addData("fl", (vertical + horizontal - turn)*FL_PERCENT*power);
        li.telemetry.addData("fr", (vertical - horizontal + turn)*FR_PERCENT*power);
        li.telemetry.addData("bl", (vertical + horizontal + turn)*BL_PERCENT*power);
        li.telemetry.addData("br", (vertical - horizontal - turn)*BR_PERCENT*power);




        fl.setPower((vertical + horizontal - turn)*FL_PERCENT*power);
        fr.setPower((vertical - horizontal + turn)*FR_PERCENT*power);
        bl.setPower((vertical + horizontal + turn)*BL_PERCENT*power);
        br.setPower((vertical - horizontal - turn)*BR_PERCENT*power);
    }
    public void moveAuto(double l_x, double l_y, double r_x){
        double horizontal = l_x;
        double vertical = -l_y;
        double turn =  r_x;
        fl.setPower((vertical - horizontal - turn));
        fr.setPower((vertical - horizontal + turn));
        bl.setPower((vertical + horizontal + turn));
        br.setPower((vertical + horizontal - turn));
    }
    public void moveAutoTest(double l_x, double l_y, double r_x){
        double horizontal = l_x;
        double vertical = -l_y;
        double turn =  r_x;
        fl.setPower((vertical - horizontal - turn));
        fr.setPower((vertical - horizontal + turn));
        bl.setPower((vertical + horizontal + turn));
        br.setPower((vertical + horizontal - turn));
    }
    public  void moveTo(double x, double y, double heading) {
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double y_f = p.getY(DistanceUnit.INCH);
        double h_f = p.getHeading(AngleUnit.DEGREES);
        while (Math.abs(x_f-x) < allowed_x_err && Math.abs(y_f-y) < allowed_y_err && Math.abs(h_f-heading) < allowed_h_err) {
            double[] vels = pathmaker.linear(x_f, x, y_f, y, h_f, heading);
            move(vels[0], vels[1], vels[2]);
            li.sleep(5);
            x_f = p.getX(DistanceUnit.INCH);
            y_f = p.getY(DistanceUnit.INCH);
            h_f = p.getHeading(AngleUnit.DEGREES);
        }
        move(0,0,0);

    }
    public void moveToAsync4(double x, double y, double heading) {
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double y_f = p.getY(DistanceUnit.INCH);
        double h_f = p.getHeading(AngleUnit.DEGREES);
        li.telemetry.addData("vx1", x_f);
        li.telemetry.addData("vz1", y_f);
        li.telemetry.addData("vh1", h_f);

        li.telemetry.addData("vx2", x);
        li.telemetry.addData("vz2", y);
        li.telemetry.addData("vh2", heading);
        li.telemetry.update();
        if (Math.abs(x_f-x) < allowed_x_err && Math.abs(y_f-y) < allowed_y_err && Math.abs(h_f-heading) < allowed_h_err) {
            this.move(0,0,0);
        }
        else {
            double[] vels = pathmaker.linear2(x_f, x, y_f, y, h_f, heading);
            li.telemetry.addData("vx", vels[0]);
            li.telemetry.addData("vz", vels[1]);
            li.telemetry.addData("vh", vels[2]);
            li.telemetry.update();

            move(vels[0], vels[1], vels[2]);
        }
    }
    public void moveToAsync2(double x, double y, double heading) {
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double y_f = p.getY(DistanceUnit.INCH);
        double h_f = p.getHeading(AngleUnit.DEGREES);
        li.telemetry.addData("vx1", x_f);
        li.telemetry.addData("vz1", y_f);
        li.telemetry.addData("vh1", h_f);

        li.telemetry.addData("vx2", x);
        li.telemetry.addData("vz2", y);
        li.telemetry.addData("vh2", heading);
        li.telemetry.update();
        if (Math.abs(x_f-x) < allowed_x_err && Math.abs(y_f-y) < allowed_y_err && Math.abs(h_f-heading) < allowed_h_err) {
            this.move(0,0,0);
        }
        else {
            double x_velocity = -1.0;
            double y_velocity = -1.0;
            double h_velocity = -1.0;

            if (Math.abs(x-x_f) < allowed_x_err) {
                x_velocity = 0.0;
            }
            else if (x - x_f > 0 ) {
                x_velocity = speed;
            }

            if (Math.abs(y-y_f) < allowed_y_err) {
                y_velocity = 0.0;
            }
            else if (y - y_f > 0) {
                y_velocity = 1.0;
            }

            if (Math.abs(heading-h_f) < allowed_h_err) {
                h_velocity = 0.0;
            }
            else if (heading - h_f > 0) {
                h_velocity = 1.0;
            }


            move(x_velocity, y_velocity, h_velocity);
        }
    }
    public void moveToAsync(double x, double y, double h) {
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double y_f = p.getY(DistanceUnit.INCH);
        double h_f = p.getHeading(AngleUnit.DEGREES);
        double x_velocity = 0;
        double y_velocity = 0;
        double h_velocity = 0;

        if (x_f > x) {
            x_velocity = Math.min(speed, (x_f - x)/break_constant);
        }
        else if (x > x_f ) {
            x_velocity = -Math.min(speed, (x - x_f)/break_constant);;
        }

        if (y_f > y) {
            y_velocity = Math.min(speed, (y_f - y)/break_constant);
        }
        else if (y > y_f ) {
            y_velocity = -Math.min(speed, (y - y_f)/break_constant);;
        }

        if (prev_vel_x * x_velocity < 0) {
            x_velocity = 0;
        }
        else {
            prev_vel_x = x_velocity;
        }

        if (prev_vel_y * y_velocity < 0) {
            y_velocity = 0;
        }
        else {
            prev_vel_y = y_velocity;
        }
        if(h_f>h){
            h_velocity = Math.min(speed, (h_f-h)/break_constant);
        }else if(h>h_f){
            h_velocity = -Math.min(speed, (h-h_f)/break_constant);
        }
        if(prev_vel_h * h_velocity < 0){
            h_velocity = 0;
        }else{
            prev_vel_h = h_velocity;
        }


        move(x_velocity, y_velocity, h_velocity);
    }
    public void moveToAsyncX(double x) {
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double x_velocity = 0;
        if (x_f > x) {
            x_velocity = Math.min(speed, (x_f - x)/break_constant);
        }
        else if (x > x_f ) {
            x_velocity = -Math.min(speed, (x - x_f)/break_constant);;
        }
        if (prev_vel_x * x_velocity < 0) {
            x_velocity = 0;
        }
        else {
            prev_vel_x = x_velocity;
        }


        li.telemetry.addData("xv", x_velocity);

        move(x_velocity, 0, 0);

    }
    public void moveToAsyncY(double y) {
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double y_f = p.getY(DistanceUnit.INCH);
        double y_velocity = 0;


        if (y_f > y) {
            y_velocity = Math.min(speed, (y_f - y)/break_constant);
        }
        else if (y > y_f ) {
            y_velocity = -Math.min(speed, (y - y_f)/break_constant);;
        }

        if (prev_vel_y * y_velocity < 0) {
            y_velocity = 0;
        }
        else {
            prev_vel_y = y_velocity;
        }

        li.telemetry.addData("yv", y_velocity);

        move(0, y_velocity, 0);

    }
    public void rotate(double y) {
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double y_f = p.getHeading(AngleUnit.DEGREES);
        double h_velocity = 0;


        if (y_f > y) {
            h_velocity = Math.min(speed, (y_f - y)/break_constant);
        }
        else if (y > y_f ) {
            h_velocity = -Math.min(speed, (y - y_f)/break_constant);;
        }

        if (prev_vel_y * h_velocity < 0) {
            h_velocity = 0;
        }
        else {
            prev_vel_h = h_velocity;
        }

        li.telemetry.addData("hv", h_velocity);

        move(0, 0, h_velocity);

    }
    public void moveToAsync(double x, double y) {
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double y_f = p.getY(DistanceUnit.INCH);
        double x_velocity = 0;
        double y_velocity = 0;
        if (x_f > x) {
            x_velocity = Math.min(speed, (x_f - x)/break_constant);
        }
        else if (x > x_f ) {
            x_velocity = -Math.min(speed, (x - x_f)/break_constant);;
        }

        if (y_f > y) {
            y_velocity = Math.min(speed, (y_f - y)/break_constant);
        }
        else if (y > y_f ) {
            y_velocity = -Math.min(speed, (y - y_f)/break_constant);;
        }

        if (prev_vel_x * x_velocity < 0) {
            x_velocity = 0;
        }
        else {
            prev_vel_x = x_velocity;
        }

        if (prev_vel_y * y_velocity < 0) {
            y_velocity = 0;
        }
        else {
            prev_vel_y = y_velocity;
        }

        li.telemetry.addData("xv", x_velocity);
        li.telemetry.addData("yv", y_velocity);

        move(x_velocity, y_velocity, 0);
    }
    public void moveToAsyncHeading(double h) {
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double h_f = p.getHeading(AngleUnit.DEGREES);
        double h_velocity = 0;
        if (h_f > h) {
            h_velocity = -Math.min(speed, (h_f - h)/break_constant);
        }
        else if (h > h_f ) {
            h_velocity = Math.min(speed, (h - h_f)/break_constant);;
        }


        if (prev_vel_h * h_velocity < 0) {
            h_velocity = 0;
        }
        else {
            prev_vel_h = h_velocity;
        }
        li.telemetry.addData("hv", h_velocity);


        move(0,0, h_velocity);
    }

    public void moveToAsync3() {
        double x = 12.0;
        odo.bulkUpdate();
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        li.telemetry.addData("vx1", x_f);

        li.telemetry.addData("vx2", x);
        li.telemetry.update();
        move(0.0,1.0,0.0);
    }
    public void moveAdditional(double x, double y, double heading) {
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH)+x;
        double y_f = p.getY(DistanceUnit.INCH)+y;
        double h_f = p.getHeading(AngleUnit.DEGREES)+heading;
        this.moveTo(x_f, y_f, h_f);
    }


}
