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
    CRServo active_intake;
    public static double intake_constant = 1.0;
    public double targetValue = 650; //Intensity Threshold
    public intake(LinearOpMode l, boolean color){
        l.hardwareMap.get(CRServo.class, "intake");
        //l.hardwareMap.get(ColorSensor.class, "color_sensor");
    }
    public void takeIn(){
        active_intake.setPower(1.0);
    }
    public void eject(){
        active_intake.setPower(-intake_constant);
    }
    public void stop(){
        active_intake.setPower(0);
    }
    public boolean busy(){
        return (active_intake.getPower()<0.1);
    }
}
