package org.firstinspires.ftc.teamcode.mechanics.drivetrain;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
@Config
public class wheel {
    private DcMotor motor;

    public wheel(HardwareMap hardwareMap, String loc, boolean reverse) {
        motor = hardwareMap.get(DcMotor.class, loc);
        if (reverse) {motor.setDirection(DcMotor.Direction.REVERSE);}
        else {motor.setDirection(DcMotor.Direction.FORWARD);}
    }
    public void setPower(double p) {
        motor.setPower(Range.clip(p, -1, 1));
    }
    public double getPower() {
        return motor.getPower();
    }


}
