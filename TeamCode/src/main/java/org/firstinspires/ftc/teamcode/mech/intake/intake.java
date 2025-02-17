package org.firstinspires.ftc.teamcode.mech.intake;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

//TODO: Integrate CV with this


public class intake {
    public static String color = "NULL";
    CRServo active_intake;
    ColorSensor color_sensor;

    public static double intake_constant = 1.0;
    public double redValue = 0.0;
    public double blueValue = 0.0;
    public double greenValue = 0.0;
    public double alphaValue = 0.0;

    public boolean redOrBlue = true; //true = red, false = blue
    public double targetValue = 650; //Intensity Threshold
    public static double yB = 0;
    public static double yR = 0.95;
    public static double yG = 0.90;
    public static double rB = 0.05;
    public static double rR = 0.95;
    public static double rG = 0.10;
    public static double bB = 0.95;
    public static double bR = 0.05;
    public static double bG = 0.05;



    public intake(LinearOpMode l){
        l.hardwareMap.get(Servo.class, "active_intake");
        l.hardwareMap.get(ColorSensor.class, "color_sensor");
    }
    public void chooseBlue(){
        redOrBlue = false;
    }
    public void takeIn(){
        redValue = color_sensor.red();
        greenValue = color_sensor.green();
        blueValue = color_sensor.blue();
        alphaValue = color_sensor.alpha();
        //look for yellow
        if(blueValue>yB && redValue>yR && greenValue>yG && alphaValue> targetValue){
            active_intake.setPower(1.0);
        }
        //look for red
        if(redOrBlue){
            if(blueValue>rB && redValue>rR && greenValue>rG && alphaValue> targetValue){
                active_intake.setPower(1.0);
            }
        }
        //look for blue
        else{
            if(blueValue>bB && redValue>bR && greenValue>bG && alphaValue> targetValue){
                active_intake.setPower(1.0);
            }
        }
    }
    public void eject(){
        active_intake.setPower(-intake_constant);
    }
    public void stop(){
        active_intake.setPower(0);
    }


    //Tester Functions
    public double rV(){
        return redValue;
    }
    public double gV(){
        return greenValue;
    }
    public double bV(){
        return blueValue;
    }
    public double aV(){
        return alphaValue;
    }
}
