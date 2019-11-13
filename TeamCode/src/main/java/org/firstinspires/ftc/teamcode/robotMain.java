package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class robotMain extends robotInitialize {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        while (super.opModeIsActive()) {


            super.motorBL.setPower(1);
            super.motorBR.setPower(1);



        }
    }
}
