package org.firstinspires.ftc.teamcode.mechanics.drivetrain.pathmaker;

import com.acmerobotics.dashboard.config.Config;

@Config
public class motionProfiling {
    public static double maxAcceleration = 92.88;
    public static double maxVelocity = 92.88;
    public static double maxAccelerationH = 1.0;
    public double maxVelocityH = 1.0;
    public double elapsedTime = -1;
    private double startTime = -1;
    public int debug = 0;
    public motionProfiling() {}
    public double trapezoid(double distance) {
        getElapsedTime();
        // Calculate the time it takes to accelerate to max velocity
        double accelerationDt = maxVelocity / maxAcceleration;

        // If we can't accelerate to max velocity in the given distance, we'll accelerate as much as possible
        double halfwayDistance = distance / 2;
        double accelerationDistance = 0.5 * maxAcceleration * Math.pow(accelerationDt, 2);

        if (accelerationDistance > halfwayDistance) {
            accelerationDt = Math.sqrt(halfwayDistance / (0.5 * maxAcceleration));
        }

        accelerationDistance = 0.5 * maxAcceleration * Math.pow(accelerationDt, 2);


        // We decelerate at the same rate as we accelerate
        double decelerationDt = accelerationDt;

        // Calculate the time that we're at max velocity
        double cruiseDistance = distance - 2 * accelerationDistance;
        double cruiseDt = cruiseDistance / maxVelocity;
        double decelerationTime = accelerationDt + cruiseDt;

        // Check if we're still in the motion profile
        double entireDt = accelerationDt + cruiseDt + decelerationDt;
        if (elapsedTime > entireDt) {
            return 0;
        }

        // If we're accelerating
        if (elapsedTime < accelerationDt) {

            // Use the kinematic equation for acceleration
            return (maxAcceleration * elapsedTime)/maxVelocity;
        }
        else if (elapsedTime < decelerationTime) {

            return 1.0;
        }
        else {
            return   maxAcceleration * (entireDt - elapsedTime) / maxVelocity;
        }
    }
    public double trapezoidH(double distance) {
        getElapsedTime();
        // Calculate the time it takes to accelerate to max velocity
        double accelerationDt = maxVelocityH / maxAccelerationH;

        // If we can't accelerate to max velocity in the given distance, we'll accelerate as much as possible
        double halfwayDistance = distance / 2;
        double accelerationDistance = 0.5 * maxAccelerationH * Math.pow(accelerationDt, 2);

        if (accelerationDistance > halfwayDistance) {
            accelerationDt = Math.sqrt(halfwayDistance / (0.5 * maxAccelerationH));
        }

        accelerationDistance = 0.5 * maxAccelerationH * Math.pow(accelerationDt, 2);

        // Recalculate max velocity based on the time we have to accelerate and decelerate
        maxVelocityH = maxAccelerationH * accelerationDt;

        // We decelerate at the same rate as we accelerate
        double decelerationDt = accelerationDt;

        // Calculate the time that we're at max velocity
        double cruiseDistance = distance - 2 * accelerationDistance;
        double cruiseDt = cruiseDistance / maxVelocityH;
        double decelerationTime = accelerationDt + cruiseDt;

        // Check if we're still in the motion profile
        double entireDt = accelerationDt + cruiseDt + decelerationDt;
        if (elapsedTime > entireDt) {
            return distance;
        }

        // If we're accelerating
        if (elapsedTime < accelerationDt) {
            // Use the kinematic equation for acceleration
            return 0.5 * maxAccelerationH * Math.pow(elapsedTime, 2);
        }

        // If we're cruising
        else if (elapsedTime < decelerationTime) {
            accelerationDistance = 0.5 * maxAccelerationH * Math.pow(accelerationDt, 2);
            double cruiseCurrentDt = elapsedTime - accelerationDt;

            // Use the kinematic equation for constant velocity
            return accelerationDistance + maxVelocity * cruiseCurrentDt;
        }

        // If we're decelerating
        else {
            accelerationDistance = 0.5 * maxAccelerationH * Math.pow(accelerationDt, 2);
            cruiseDistance = maxVelocityH * cruiseDt;
            double decelerationElapsedTime = elapsedTime - decelerationTime;

            // Use the kinematic equations to calculate the instantaneous desired position
            return accelerationDistance + cruiseDistance + maxVelocityH * decelerationElapsedTime - 0.5 * maxAccelerationH * Math.pow(decelerationElapsedTime, 2);
        }
    }
    public void getElapsedTime() {
        if (elapsedTime == -1) {
            startTime = (double) System.currentTimeMillis() /1000;
        }
        debug += 1;
        elapsedTime = (double) System.currentTimeMillis() /1000 - startTime + 0.01;
    }

}
