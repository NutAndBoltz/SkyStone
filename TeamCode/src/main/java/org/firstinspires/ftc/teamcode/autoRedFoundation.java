package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="autoRedFoundation", group="auto")

public class autoRedFoundation extends robotMovements{

    @Override
    public void runOpMode() throws InterruptedException {
       
        super.runOpMode();
        setMode(AUTO);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        resetEncoder();
        startEncoderMode();

        //place the claw at its start position so the robot satisfies measurement requirements
        robot.foundationClaw.setPosition(1);

        //Move servo arm up
        robot.foundationClaw.setPosition(0);

        //Move forward to a little bit before the edge of the foundation
        moveBackward(32);

        //Strafe right
        moveLeft(16);

        //Move servo arm down to latch onto foundation squares
        robot.foundationClaw.setPosition(0.6);

        //delay

        stopRobot(2);

        //Move backward into the depot (leave enough space for robot)
        moveForward(36);

        //delay
        stopRobot(1);

        //Move servo arm up
        robot.foundationClaw.setPosition(0);

        //delay
        stopRobot(1);

        //Strafe Left
        moveRight(66);

        stopRobot();
    }
}


