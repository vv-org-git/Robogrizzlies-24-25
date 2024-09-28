package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.mechanics.arm.arm;
import org.firstinspires.ftc.teamcode.mechanics.claw.claw;
import org.firstinspires.ftc.teamcode.mechanics.drivetrain.movement;
import org.firstinspires.ftc.teamcode.mechanics.webcam.webcam;
import org.firstinspires.ftc.teamcode.vision.SampleDetection;

public class robot {
    public movement movement;
    public claw claw;
    public arm arm;
    public webcam webcam;
    public static int m_x = 1;
    public static int m_y = 1;
    public static int m_r = 1;

    public static int b_x = 1;
    public static int b_y = 1;
    public static int b_r = 1;
    LinearOpMode l;
    public robot(LinearOpMode l){
        movement = new movement(l);
        arm = new arm(l);
        claw = new claw(l);
        webcam = new webcam(l);
    }
    public void alignment(SampleDetection samplePipeline) {
        samplePipeline.run = true;
        while (samplePipeline.run) {
            l.sleep(50);
        }
        movement.moveAdditional(m_x * samplePipeline.x_delta + b_x , m_y * samplePipeline.x_delta + b_y, 0);
        claw.rotate(m_r * samplePipeline.angle_delta + b_r);
    }
    public boolean is_busy() {
        return (this.movement.is_busy() || this.arm.isBusy());
    }
}
