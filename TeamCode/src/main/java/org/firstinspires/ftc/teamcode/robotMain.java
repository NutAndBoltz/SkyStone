package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class robotMain extends robotInitialize {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        while (opModeIsActive()) {


            motorBL.setPower(1);
            motorBR.setPower(-1);



        }
    }
}
