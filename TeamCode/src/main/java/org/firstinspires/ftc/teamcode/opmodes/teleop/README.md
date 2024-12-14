# Robotics Program File Descriptions

This document specifies the program files used in the driver hub, along with their respective functions. Highlighted functions are to be tested with the robot.

| Program File Name      | Function Description |
|------------------------|----------------------|
| **armTest**            | A file to override op mode for testing the arm. Commands: <br> **A**: Tests arm extension speed (10 ticks per inch). <br> **B**: Tests arm rotation speed. <br> **Right Trigger**: Extends the arm. <br> **Left Trigger**: Retracts the arm. <br> **Dpad Up**: Arm moves down (meant to be up; needs change). <br> **Dpad Down**: Arm moves down. |
| **clawTest**           | A file to override op mode for testing the claw. Commands: <br> **A**: Tests X servo. <br> **B**: Tests Z servo. <br> **X**: Claw release. <br> **Y**: Claw bite. |
| **Control2 (requires gamepad2)** | A file to test various controls. Commands: <br> **Left Stick Button**: Moves arm/claw to basket, moves robot to basket. <br> **Right Stick Button**: Automates robot movement to high bar (30 seconds left). <br> **Right Stick Button + Start**: Automates placing specimens on high bar. <br> **Left Stick Button + Start**: Reaches main pile, releases claw, sets Z servo down, moves to a specific board spot. <br> Other buttons implement claw and arm movements or trigger specific robot actions. |
| **controls**           | Similar to Control2, with differences: <br> **X**: Arm goes down, retracts partially, Z servo up, resets X, releases claw. <br> **B**: Arm retracts partially, moves up, Z servo up, resets X, releases claw. |
| **controls3**          | Constructs the robot and variables for bars. Commands: <br> **Right Trigger**: Claw bite. <br> **Left Trigger**: Claw release. <br> Various other buttons adjust arm and claw positioning or trigger specific movements. |
| **cvTest**             | Webcam testing file. Commands: <br> **A**: Sets color to 0. <br> **Y**: Sets color to 1. <br> **B**: Sets color to 2. <br> **Right Trigger**: Meant to find color position, rotate claw. (Work in progress.) |
| **cvTestTelemetry**    | Similar to cvTest, with hub info updates. |
| **deadwheelTest**      | Same as cvTest. |
| **drive1**             | Similar to mainTeleOpLeft. |
| **driveTest**          | Displays x, y, heading on screen. Commands: <br> **Left Stick**: Move to (-48, 24, -135). <br> **Right Stick**: Move along X axis depending on time. <br> **Left Bumper**: Move to (48, 24, 0). <br> **Right Bumper**: Move to (0, 24, 0). <br> **X**: Move to (0, 0, 90). |
| **mainTeleOp**         | Defines the robot's main teleoperation mode. Initializes the robot, resets claw positions, and waits for user action. |
| **mainTeleOpLeft**     | Sets up the robot, samplePipeline, time, and controls, allowing the driver to control the robot with actions defined in the controls class. |
| **motorTest**          | Sets motor to reverse, runs motor with "Encoder" and "Brake", moves toward a position. |
| **motorTest2**         | Sets motors to reverse, gives power. |
| **servoTest**          | Tests servos for resistance (checks if servo is plugged correctly). Press any trigger to set position. |
