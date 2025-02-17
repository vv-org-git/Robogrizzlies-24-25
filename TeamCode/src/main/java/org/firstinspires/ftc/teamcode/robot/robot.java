package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mech.arm.arm;
import org.firstinspires.ftc.teamcode.mech.intake.intake;
import org.firstinspires.ftc.teamcode.mech.movement.movement;
import org.firstinspires.ftc.teamcode.mech.CV.CV;


public class robot {
    public intake intake;
    public arm arm;
    public movement movement;
    public CV CV;
    public boolean busy = false;
    public long set_sleep = 0;

    LinearOpMode l;
    public robot(LinearOpMode l, double x, double y, double z, boolean w){
        movement = new movement(l, x, y, z);
        arm = new arm(l);
        intake = new intake(l);
        if (w) {
            CV = new CV(l);
        }
    }

}
