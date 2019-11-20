package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="re_autoEncoderCode", group="auto")
//@Disabled
public class re_autoEncoderCode extends robotMovements{

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        resetEncoder();
        startEncoderMode();

        //place the claw at its start position so the robot satisfies measurement requirements
        robot.foundationClaw.setPosition(1);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive())
        {
            //Move servo arm up
            robot.foundationClaw.setPosition(0.5);

            //Move forward to a little bit before the edge of the foundation
            moveForward("AUTO", 36);

            //Move servo arm down to latch onto foundation squares
            robot.foundationClaw.setPosition(1);

            //Move backward into the depot (leave enough space for robot)
            moveBackward("AUTO", 10);

            //Pick up claw
            robot.foundationClaw.setPosition(0.7);

            moveLeft("AUTO", 5);

        }
    }
}


