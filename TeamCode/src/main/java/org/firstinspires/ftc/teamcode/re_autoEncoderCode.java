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


            //Move servo arm up
            robot.foundationClaw.setPosition(0);

            //Move forward to a little bit before the edge of the foundation
            moveBackward("AUTO", 8.8);

            //Strafe left
            moveRight("AUTO", 5);

            //Move servo arm down to latch onto foundation squares
            robot.foundationClaw.setPosition(0.6);

            //delay
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1.5)) {
                stopRobot();
                telemetry.addData("Motor", "Stopped");    //
                telemetry.update();
            }

            //Move backward into the depot (leave enough space for robot)
            moveForward("AUTO", 9.5);

            //delay
            runtime.reset();
            stopRobot(1);

            //Move servo arm up
            robot.foundationClaw.setPosition(0);

            stopRobot(1);
            //Strafe right
            moveLeft("AUTO", 15);

            //Move forward to a little bit before the edge of the foundation
            moveBackward("AUTO", 1);

            stopRobot(1);
            stopRobot();


    }
}


