package org.firstinspires.ftc.teamcode.mech.movement;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.helperFunctions.helperFunctions;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
public class wheel {
    //Define our motor for the wheel
    private DcMotor motor;
    private helperFunctions help;
    //Constructor For Wheel:
    public wheel(HardwareMap map, String s, boolean reverse){
        motor = map.get(DcMotor.class, s);
        help = new helperFunctions();
        if(reverse){
            motor.setDirection(DcMotor.Direction.REVERSE);
        }else{
            motor.setDirection(DcMotor.Direction.FORWARD);
        }
    }
    public void setPower(double power){
        //if above 1
        motor.setPower(help.Clip(power));
    }
    public double getPower(){
        return motor.getPower();
    }
}
