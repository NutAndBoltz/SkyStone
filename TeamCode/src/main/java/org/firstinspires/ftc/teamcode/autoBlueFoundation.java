package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="autoBlueFoundation", group="auto")

public class autoBlueFoundation extends robotMovements{

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

        //while we are under 27 seconds
            //move foundation
                //Move servo arm up
                robot.foundationClaw.setPosition(0);

                //Move forward to a little bit before the edge of the foundation
                moveBackward(32);

                //Strafe left
                moveRight(16);

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

            //turn right
            //Move forward to stones
            //Turn left so camera is facing stones
            //While there are stones
                // Move forward to stones
                //Scan for skystone
                //If skystone: pick it up, turn left, drive forward to foundation, drop skystone
                //Else: continue
                        //Drive backward to next stone
                //Turn right to face stone



        //then, park
            //find placement on field
            //find directions to midfield line
            // move there

        stopRobot();
    }
}


