package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.robot.robot;
public class controls {
    robot robot;
    LinearOpMode op;
    long init_time;
    public controls(robot r, LinearOpMode l, long t){
        robot = r;
        op = l;
        init_time = t;
    }
    public void action() {
        robot.movement.odo.bulkUpdate();
        robot.movement.move(op.gamepad1.left_stick_x, op.gamepad1.left_stick_y, op.gamepad1.right_stick_x);
        if (op.gamepad1.right_trigger > 0.3) {
            robot.arm.afterSpecimen();
        }
        if (op.gamepad1.left_trigger > 0.3) {
            robot.intake.eject();
        }else{
            robot.intake.stop();
        }
        if(op.gamepad1.right_bumper){
            robot.arm.getSpecimen();
        }
        if(op.gamepad1.left_bumper){
            robot.arm.placeSpecimen();
        }
        if(op.gamepad1.y){
            robot.arm.hang();
        }
        if(op.gamepad1.b){
            robot.arm.inTank();
            robot.intake.takeIn();
        }
        if(op.gamepad1.a){
            robot.arm.outTank();
        }
        if(op.gamepad1.x){
            robot.arm.highBasket();
        }
        if(op.gamepad1.guide){
            robot.arm.setPower(-1);
        }
        if(op.gamepad1.start){
            robot.arm.set0();
        }
    }
}
