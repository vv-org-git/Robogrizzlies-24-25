package org.firstinspires.ftc.teamcode.mechanics.arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
@Config
public class utility_motor {
    private DcMotor motor;




    public utility_motor(HardwareMap hardwareMap, String loc, boolean reverse) {
        motor = hardwareMap.get(DcMotor.class, loc);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Turn the motor back on, required if you use STOP_AND_RESET_ENCODER
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (reverse) {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }
    }
    public void setPower(double p) {
        motor.setPower(Range.clip(p, -1, 1));
    }
    public double getPower() {
        return motor.getPower();
    }

    public void setPosition(int t) {
        while (motor.getCurrentPosition() - t != 0) {
            if (motor.getCurrentPosition() - t > 0) {this.setPower(1.0); }
            else {this.setPower(-1.0);}
        }
    }
    public void setPlace(double up_ticks) {
        this.setPower(up_ticks);
    }
}
