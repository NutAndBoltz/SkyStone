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


    //make sure opmode is active b4 calling these method
    public void MoveForwared(double distance, String mode)
    {
        if(mode.equals("TeleOp"))
        {
            runtime.reset();
            while(runtime.seconds()<1.0)
            {



                motorFL.setPower(-1);
                motorFR.setPower(1);
                motorBR.setPower(1);
                motorBL.setPower(-1);
                telemetry.addData("Path", "Going forward for %d seconds",runtime.seconds());
                telemetry.update();
            }
            motorFL.setPower(0);
            motorFR.setPower(0);
            motorBR.setPower(0);
            motorBL.setPower(0);



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
            motorFL.setPower(1);
            motorFR.setPower(-1);
            motorBR.setPower(-1);
            motorBL.setPower(1);
            telemetry.addData("Path", "Going forward for %d seconds",runtime.seconds());
            telemetry.update();
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
            motorFL.setPower(-1);
            motorFR.setPower(-1);
            motorBR.setPower(1);
            motorBL.setPower(1);
            telemetry.addData("Path", "Going forward for %d seconds",runtime.seconds());
            telemetry.update();

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
            motorFL.setPower(1);
            motorFR.setPower(1);
            motorBR.setPower(-1);
            motorBL.setPower(-1);
            telemetry.addData("Path", "Going forward for %d seconds",runtime.seconds());
            telemetry.update();
        }
        else
        {
            int tick =DistanceToTick(distance);
        }
    }




    @Override
    public  void runOpMode() throws InterruptedException
    {
        telemetry.addData("TestClass","RobotMovement.class runOpMode running");
        motorFL = hardwareMap.get(DcMotor.class, "motor_fl");
        motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
        motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
        motorBR = hardwareMap.get(DcMotor.class, "motor_br");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.init(hardwareMap);
        double G1rightStickY = -gamepad1.right_stick_y;
        double G1leftStickY = -gamepad1.left_stick_y;
        float G1left_trigger = gamepad1.left_trigger;
        float G1right_trigger= gamepad1.right_trigger;
        boolean G1buttonB = gamepad1.b;
        float dpadThreshold = 0.2f;






    }
    public void robotMain() throws InterruptedException {
        runOpMode();
    }
}
