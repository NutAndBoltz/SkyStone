package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

public class RobotMovement extends LinearOpMode implements RobotVariable
{

    HardwarePushbot         robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    ElapsedTime     runtime = new ElapsedTime();

    DcMotor motorFL = null;
    DcMotor motorFR = null;
    DcMotor motorBL = null;
    DcMotor motorBR = null;

    public int DistanceToTick(double distance)
    {
        int tick = (int)(distance/INCH_PER_COUNT);
        return tick;
    }

    public void MoveForwared(double distance, String mode)
    {
        if(mode.equals("TeleOp"))
        {
            while (opModeIsActive())
            {
                motorFL.setPower(1);
                motorFR.setPower(-1);
                motorBR.setPower(-1);
                motorBL.setPower(1);
                telemetry.addData("Path", "Going forward for %d seconds",runtime.seconds());
                telemetry.update();
            }
        }
        else
        {
            int tick =DistanceToTick(distance);
           //idk how to get
        }

    }
    public void MoveBackward(double distance, String mode)
    {
        if(mode.equals("TeleOp"))
        {
            //move infinetly
        }
        else
        {
            int tick =DistanceToTick(distance);
        }
    }
    protected void TurnRight()
    {

    }
    protected void TurnLeft()
    {

    }
    public void MoveRight(double distance, String mode)
    {
        TurnRight();
        if(mode.equals("TeleOp"))
        {
            //move infinetly
        }
        else
        {
            int tick =DistanceToTick(distance);
        }
    }
    public void MoveLeft(double distance, String mode)
    {
        TurnLeft();
        if(mode.equals("TeleOp"))
        {
            //move infinetly
        }
        else
        {
            int tick =DistanceToTick(distance);
        }
    }
    @Override
    public void runOpMode() throws InterruptedException
    {
        motorFL = hardwareMap.get(DcMotor.class, "motor_fl");
        motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
        motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
        motorBR = hardwareMap.get(DcMotor.class, "motor_br");
        robot.init(hardwareMap);
        waitForStart();
        runtime.reset();




    }
}
