package org.firstinspires.ftc.teamcode.mech.arm;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.openftc.easyopencv.OpenCvPipeline;
public class arm {
    motor bottomJoint;
    Servo LJoint;
    Servo RJoint;
    //this should be angled down at around roughly 45 degree off the ground (should be lower outside than inside)
    //https://www.youtube.com/watch?v=5SnarXNfdNI&t=21s&ab_channel=FRCTeam4613-BarkerRedbacks (0:20)
    public static int bottom_OUTSIDE_intake = 135;
    public static double servo_OUTSIDE_intake = 0.3;
    //should be a bit higher up
    public static int bottom_WITHIN_intake = 150;
    public static double servo_WITHIN_intake = 0.2;
    //around the same thing as intake we can check?
    public static int bottom_get_specimen = 165;
    public static double servo_get_specimen = 0.75;
    //idk why this is a bit up but it seems like it
    public static int bottom_after_place_specimen = 300;
    public static double servo_after_place_specimen = 0.65;
    //should be angled but not straight up
    public static int bottom_place_specimen = 450;
    public static double servo_place_specimen = 0.6;
    //this should be straight up (90 degrees from ground)
    public static int bottom_basket_eject = 650;
    public static double servo_basket_eject = 0.8;
    //should be at 45 but should be behind the pivot
    public static int bottom_hanging = 800;
    public static double servo_hanging = 0.4;
    public arm(LinearOpMode l){
        bottomJoint = new motor(l.hardwareMap, "bottomJoint", false);
        LJoint = l.hardwareMap.get(Servo.class, "LJoint");
        RJoint = l.hardwareMap.get(Servo.class, "RJoint"); //Spins Opposite Direction
    }
    public void outTank(){
        bottomJoint.setPlace(bottom_OUTSIDE_intake, 0.3);
        LJoint.setPosition(servo_OUTSIDE_intake);
        RJoint.setPosition(-servo_OUTSIDE_intake);
    }
    public void inTank(){
        bottomJoint.setPlace(bottom_WITHIN_intake, 0.3);
        LJoint.setPosition(servo_WITHIN_intake);
        RJoint.setPosition(-servo_WITHIN_intake);
    }
    public void getSpecimen(){
        bottomJoint.setPlace(bottom_get_specimen, 0.3);
        LJoint.setPosition(servo_get_specimen);
        RJoint.setPosition(-servo_get_specimen);
    }
    public void afterSpecimen(){
        bottomJoint.setPlace(bottom_after_place_specimen, 0.3);
        LJoint.setPosition(servo_after_place_specimen);
        RJoint.setPosition(-servo_after_place_specimen);
    }
    public void placeSpecimen(){
        bottomJoint.setPlace(bottom_place_specimen, 0.3);
        LJoint.setPosition(servo_place_specimen);
        RJoint.setPosition(-servo_place_specimen);
    }
    public void highBasket(){
        bottomJoint.setPlace(bottom_basket_eject, 0.3);
        LJoint.setPosition(servo_basket_eject);
        RJoint.setPosition(-servo_basket_eject);
    }
    public void hang(){
        bottomJoint.setPlace(bottom_hanging, 0.3);
        LJoint.setPosition(servo_hanging);
        RJoint.setPosition(-servo_hanging);
    }
    public void setPower(int x){
        bottomJoint.setPower(x);
    }
    public void set0(){
        bottomJoint.reset();
    }
}
