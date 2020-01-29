package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Park:blueAlliance", group="auto")


public class autoCodeLeft extends robotMovements {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        setMode(AUTO);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        resetEncoder();
        startEncoderMode();

        //park over mid-line
        moveBackward(4);
        moveLeft(33);
    }
}