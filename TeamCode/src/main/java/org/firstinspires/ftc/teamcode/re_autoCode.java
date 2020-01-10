package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "re_autoCode",group = "auto")
public class re_autoCode extends robotMovements {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        setMode(AUTO);
        robot.runtime.reset();


        moveLeft(5);
        //drive backward to bridge
        moveBackward(10);
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
