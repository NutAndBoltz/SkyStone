package org.firstinspires.ftc.teamcode;


public class RobotMovement implements RobotVariable
{

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
}
