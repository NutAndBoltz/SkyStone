package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//this is test class
@TeleOp
@Disabled

public class robotMain extends robotMovements{
    private float G1leftStickX;
    private float G1rightStickX;
    private float G1rightStickY;
    private float G1leftStickY;



    @Override
    public void runOpMode() throws InterruptedException {


        super.runOpMode();
        setMode(TELEOP);
        G1rightStickY=0;
        G1leftStickY=0;
        G1rightStickX=0;
        G1leftStickY=0;
        while(opModeIsActive())
        {
            G1leftStickX = -gamepad1.left_stick_x;
            G1rightStickX = -gamepad1.right_stick_x;
            G1rightStickY = -gamepad1.right_stick_y;
            G1leftStickY = -gamepad1.left_stick_y;


            telemetry.addLine("G1leftStick X : "+G1leftStickX+"\t"+"G1rightStick X : "+G1rightStickX);
            telemetry.addLine();
            telemetry.addLine("G1leftStick Y : "+G1leftStickY+"\t"+"G1rightStick Y : "+G1rightStickY);
            telemetry.update();
        }





    }
}
