package org.firstinspires.ftc.teamcode.mechanics.drivetrain;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.gobuildaPinpointDriver.Pose2D;
import org.firstinspires.ftc.teamcode.vision.SampleDetection;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.pathmaker.pathmaker;
public class movement {
    GoBildaPinpointDriver odo;
    double oldTime = 0;
    public wheel fl;
    public wheel fr;
    public wheel bl;
    public wheel br;
    LinearOpMode l;



    public static double allowed_x_err = 0.1;
    public static double allowed_y_err = 0.1;
    public static double allowed_h_err = 0.1;

    public double init_x_offset = 12.0;

    public movement(LinearOpMode l) {
        odo.setOffsets(-84.0, -168.0);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

        fl = new wheel(l.hardwareMap, "lr");
        fr = new wheel(l.hardwareMap, "fr");
        bl = new wheel(l.hardwareMap, "bl");
        br = new wheel(l.hardwareMap, "br");
    }

    public boolean is_busy(){
        return (fl.getPower() > 0 || fr.getPower() > 0 || bl.getPower() > 0  || br.getPower() > 0 );
    }


    public void move(double l_x, double l_y, double r_x){
        double horizontal = l_x;
        double vertical = l_y;
        double turn = - r_x;
        fl.setPower(vertical + horizontal + turn);
        fr.setPower(vertical - horizontal - turn);
        bl.setPower(vertical - horizontal + turn);
        br.setPower(vertical + horizontal - turn);
    }
    public  void moveTo(double x, double y, double heading) {
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double y_f = p.getY(DistanceUnit.INCH);
        double h_f = p.getHeading(AngleUnit.DEGREES);
        while (Math.abs(x_f-x) < allowed_x_err && Math.abs(y_f-y) < allowed_y_err && Math.abs(h_f-heading) < allowed_h_err) {
            double[] vels = pathmaker.linear(x_f, x, y_f, y, h_f, heading);
            move(vels[0], vels[1], vels[2]);
            l.sleep(5);
            x_f = p.getX(DistanceUnit.INCH);
            y_f = p.getY(DistanceUnit.INCH);
            h_f = p.getHeading(AngleUnit.DEGREES);
        }
        move(0,0,0);

    }
    public void moveToAsync(double x, double y, double heading) {

        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH);
        double y_f = p.getY(DistanceUnit.INCH);
        double h_f = p.getHeading(AngleUnit.DEGREES);
        if (Math.abs(x_f-x) < allowed_x_err && Math.abs(y_f-y) < allowed_y_err && Math.abs(h_f-heading) < allowed_h_err) {
            this.move(0,0,0);
        }
        else {
            double[] vels = pathmaker.linear(x_f, x, y_f, y, h_f, heading);
            move(vels[0], vels[1], vels[2]);
        }
    }

    public void moveAdditional(double x, double y, double heading) {
        Pose2D p = odo.getPosition();
        double x_f = p.getX(DistanceUnit.INCH)+x;
        double y_f = p.getY(DistanceUnit.INCH)+y;
        double h_f = p.getHeading(AngleUnit.DEGREES)+heading;
        this.moveTo(x_f, y_f, h_f);
    }


}
