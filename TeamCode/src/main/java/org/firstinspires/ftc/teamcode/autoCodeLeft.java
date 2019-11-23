package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="autoCodeLeft", group="auto")
@Disabled

public class autoCodeLeft extends robotMovements {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        resetEncoder();
        startEncoderMode();

        while (opModeIsActive())
        {
            //delay
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1)) {
                stopRobot();
                telemetry.addData("Motor", "Stopped");    //
                telemetry.update();
            }

            //Strafe left
            moveLeft("AUTO", 15);


        }
    }
}