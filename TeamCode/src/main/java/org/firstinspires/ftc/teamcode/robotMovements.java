
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class robotMovements extends LinearOpMode implements robotVariable
{
    ElapsedTime runtime = new ElapsedTime();
    DcMotor motorFL;
    DcMotor motorFR;
    DcMotor motorBL;
    DcMotor motorBR;
    Servo foundationClaw;

    public void moveForward(String opMode, double distance)
    {
        if(opMode.equals("AUTO"))
        {

        }
        else
        {
            motorBL.setPower(teleOP_FORWARD_SPEED);
            motorBR.setPower(teleOP_FORWARD_SPEED);
            motorFL.setPower(teleOP_FORWARD_SPEED);
            motorFR.setPower(teleOP_FORWARD_SPEED);
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {
       init();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)


    }

}
