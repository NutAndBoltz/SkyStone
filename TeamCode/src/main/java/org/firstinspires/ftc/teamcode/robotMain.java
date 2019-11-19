package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//this is test class
@TeleOp
@Disabled
public class robotMain extends robotMovements {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        while (opModeIsActive()) {


            moveForward("teleOp",0);

        }
    }
}
