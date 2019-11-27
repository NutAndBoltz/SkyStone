package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="autoCodeRight", group="auto")
@Disabled

public class autoCodeRight extends robotMovements {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        setMode(AUTO);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        resetEncoder();
        startEncoderMode();

        while (opModeIsActive())
        {
            stopRobot(1);

            //Strafe right
            moveRight(15);


        }
    }
}