package org.firstinspires.ftc.teamcode.opmodes.auto;
import org.firstinspires.ftc.teamcode.robot.robot;

import java.util.ArrayList;

public class trajectory {
    ArrayList<Runnable> steps;

    public trajectory(){
        steps = new ArrayList<Runnable>();
    }

    public void add(Runnable r) {
        steps.add(r);
    }
    public void run() {
        for (Runnable act : steps) {
            act.run();
        }
    }
}
