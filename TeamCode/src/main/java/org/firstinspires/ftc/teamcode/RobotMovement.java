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


    public int DistanceToTick(double distance)
    {
        int tick = (int)(distance/INCH_PER_COUNT);
        return tick;
    }

    public void MoveForwared(double distance, String mode)
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
    public void runOpMode() throws InterruptedException {


    }
}
