package org.firstinspires.ftc.teamcode.opmodes.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous
public class LeftAuto extends LinearOpMode {
    public void runOpMode() { //static
        machine machine = new machine(this);
        machine.run( true );
    }
}