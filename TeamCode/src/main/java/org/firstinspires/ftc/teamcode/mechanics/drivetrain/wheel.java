package org.firstinspires.ftc.teamcode.mechanics.drivetrain;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class wheel {
    private DcMotor motor;

    public wheel(HardwareMap hardwareMap, String loc) {
        motor = hardwareMap.get(DcMotor.class, loc);
    }
    public void setPower(double p) {
        motor.setPower(Range.clip(p, -1, 1));
    }

}
