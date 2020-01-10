package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class robotMovements extends LinearOpMode implements robotVariable
{
    int cameraMonitorViewId=0; //used for Vuforia_Thread

    robotInit robot = new robotInit();
    ElapsedTime runtime = new ElapsedTime();
    VuforiaData vuforiaData =new VuforiaData();

    //auto mode functions
    public void resetEncoder()
    {
        robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at motorFl: %7d, motorFR:%7d, motorBL: %7d, motoBR: %7d",
                robot.motorFL.getCurrentPosition(),
                robot.motorFR.getCurrentPosition(),
                robot.motorBL.getCurrentPosition(),
                robot.motorBR.getCurrentPosition());
        telemetry.update();
    }
    public void startEncoderMode()
    {
        //Set Encoder Mode
        robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void stopEncoderMode()
    {
        robot.motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }



    //movement functions
    public void moveForward(double inches)
    {
        String opMode=this.opMode;

        if(opMode.equals("AUTO"))
        {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Ensure that the opmode is still active
            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newmotorFLTarget = robot.motorFL.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newmotorFRTarget = robot.motorFR.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newmotorBLTarget = robot.motorBL.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                robot.motorFL.setTargetPosition(newmotorFLTarget);
                robot.motorFR.setTargetPosition(newmotorFRTarget);
                robot.motorBL.setTargetPosition(newmotorBLTarget);
                robot.motorBR.setTargetPosition(newmotorBRTarget);

                // Turn On RUN_TO_POSITION
                robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                robot.motorFL.setPower(Math.abs(DRIVE_SPEED));
                robot.motorFR.setPower(Math.abs(DRIVE_SPEED));
                robot.motorBL.setPower(Math.abs(DRIVE_SPEED));
                robot.motorBR.setPower(Math.abs(DRIVE_SPEED));
                // keep looping while we are still active, and there is time left, and both motors are running.
                // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
                // its target position, the motion will stop.  This is "safer" in the event that the robot will
                // always end the motion as soon as possible.
                // However, if you require that BOTH motors have finished their moves before the robot continues
                // onto the next step, use (isBusy() || isBusy()) in the loop test.
                while (opModeIsActive() &&
                        (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("motorFL", "%7d", robot.motorFL.getCurrentPosition());
                    telemetry.addData("motorFR", "%7d", robot.motorFR.getCurrentPosition() );
                    telemetry.addData("motorBL", "%7d", robot.motorBL.getCurrentPosition());
                    telemetry.addData("motorBR", "%7d", robot.motorBR.getCurrentPosition());
                    telemetry.update();

                }

                // Stop all motion;
                stopRobot();

                // Turn off RUN_TO_POSITION
                robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }

        }
        else
        {
            moveForward();
        }
    }
    public void moveForward()
    {

        robot.motorFL.setPower(teleOP_FORWARD_SPEED);       robot.motorFR.setPower(teleOP_FORWARD_SPEED);
        robot.motorBL.setPower(teleOP_FORWARD_SPEED);       robot.motorBR.setPower(teleOP_FORWARD_SPEED);

    }
    public void moveForward(float speed)
    {
        robot.motorFL.setPower(speed);       robot.motorFR.setPower(speed);
        robot.motorBL.setPower(speed);       robot.motorBR.setPower(speed);


    }

    public void moveBackward(double inches)
    {
        String opMode=this.opMode;

        if(opMode.equals("AUTO"))
        {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Ensure that the opmode is still active
            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newmotorFLTarget = robot.motorFL.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
                newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
                newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
                newmotorBRTarget = robot.motorBR.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
                robot.motorFL.setTargetPosition(newmotorFLTarget);
                robot.motorFR.setTargetPosition(newmotorFRTarget);
                robot.motorBL.setTargetPosition(newmotorBLTarget);
                robot.motorBR.setTargetPosition(newmotorBRTarget);

                // Turn On RUN_TO_POSITION
                robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                robot.motorFL.setPower(Math.abs(DRIVE_SPEED));
                robot.motorFR.setPower(Math.abs(DRIVE_SPEED));
                robot.motorBL.setPower(Math.abs(DRIVE_SPEED));
                robot.motorBR.setPower(Math.abs(DRIVE_SPEED));
                // keep looping while we are still active, and there is time left, and both motors are running.
                // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
                // its target position, the motion will stop.  This is "safer" in the event that the robot will
                // always end the motion as soon as possible.
                // However, if you require that BOTH motors have finished their moves before the robot continues
                // onto the next step, use (isBusy() || isBusy()) in the loop test.
                while (opModeIsActive() &&
                        (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("motorFL", "%7d", robot.motorFL.getCurrentPosition());
                    telemetry.addData("motorFR", "%7d", robot.motorFR.getCurrentPosition() );
                    telemetry.addData("motorBL", "%7d", robot.motorBL.getCurrentPosition());
                    telemetry.addData("motorBR", "%7d", robot.motorBR.getCurrentPosition());
                    telemetry.update();

                }

                // Stop all motion;
                stopRobot();

                // Turn off RUN_TO_POSITION
                robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);       robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);       robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            }

        }
        else
        {
            moveBackward();
        }
    }
    public void moveBackward()
    {

        robot.motorFL.setPower(-teleOP_FORWARD_SPEED);      robot.motorFR.setPower(-teleOP_FORWARD_SPEED);

        robot.motorBL.setPower(-teleOP_FORWARD_SPEED);      robot.motorBR.setPower(-teleOP_FORWARD_SPEED);

    }
    public void moveBackward(float speed)
    {

        robot.motorFL.setPower(-speed);      robot.motorFR.setPower(-speed);

        robot.motorBL.setPower(-speed);      robot.motorBR.setPower(-speed);

    }
    void reportTick()
    {
        int FLnow =robot.motorFL.getCurrentPosition();
        int FRnow =robot.motorFR.getCurrentPosition();
        int BLnow =robot.motorBL.getCurrentPosition();
        int BRnow =robot.motorBR.getCurrentPosition();
        while (opModeIsActive() &&
                (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

            // Display it for the driver.
            telemetry.addData("motorFL", "%7d", Math.abs(robot.motorFL.getCurrentPosition()-FLnow));
            telemetry.addData("motorFR", "%7d", Math.abs(robot.motorFR.getCurrentPosition()-FRnow));
            telemetry.addData("motorBL", "%7d", Math.abs(robot.motorBL.getCurrentPosition()-BLnow));
            telemetry.addData("motorBR", "%7d", Math.abs(robot.motorBR.getCurrentPosition()-BRnow));
            telemetry.update();
            // Turn off RUN_TO_POSITION
            robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }

    public void moveRight(double inches)
    {
        String opMode=this.opMode;
        if(opMode.equals("AUTO"))
        {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Ensure that the opmode is still active
            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newmotorFLTarget = robot.motorFL.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
                newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
                newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                robot.motorFL.setTargetPosition(newmotorFLTarget);
                robot.motorFR.setTargetPosition(newmotorFRTarget);
                robot.motorBL.setTargetPosition(newmotorBLTarget);
                robot.motorBR.setTargetPosition(newmotorBRTarget);

                // Turn On RUN_TO_POSITION
                robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                robot.motorFL.setPower(Math.abs(DRIVE_SPEED));
                robot.motorFR.setPower(Math.abs(DRIVE_SPEED));
                robot.motorBL.setPower(Math.abs(DRIVE_SPEED));
                robot.motorBR.setPower(Math.abs(DRIVE_SPEED));
                // keep looping while we are still active, and there is time left, and both motors are running.
                // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
                // its target position, the motion will stop.  This is "safer" in the event that the robot will
                // always end the motion as soon as possible.
                // However, if you require that BOTH motors have finished their moves before the robot continues
                // onto the next step, use (isBusy() || isBusy()) in the loop test.
                while (opModeIsActive() &&
                        (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("motorFL", "%7d", robot.motorFL.getCurrentPosition());
                    telemetry.addData("motorFR", "%7d", robot.motorFR.getCurrentPosition() );
                    telemetry.addData("motorBL", "%7d", robot.motorBL.getCurrentPosition());
                    telemetry.addData("motorBR", "%7d", robot.motorBR.getCurrentPosition());
                    telemetry.update();

                }

                // Stop all motion;
                stopRobot();

                // Turn off RUN_TO_POSITION
                robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }

        }
        else
        {
            moveRight();
        }
    }
    public void moveRight()
    {
        robot.motorFL.setPower(teleOP_FORWARD_SPEED);       robot.motorFR.setPower(-teleOP_FORWARD_SPEED);

        robot.motorBL.setPower(-teleOP_FORWARD_SPEED);      robot.motorBR.setPower(teleOP_FORWARD_SPEED);

    }
    public void moveRight(float speed)
    {
        robot.motorFL.setPower(speed);       robot.motorFR.setPower(-speed);

        robot.motorBL.setPower(-speed);      robot.motorBR.setPower(speed);

    }
    public void moveLeft(double inches)
    {
        String opMode=this.opMode;
        if(opMode.equals("AUTO"))
        {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Ensure that the opmode is still active
            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newmotorFLTarget = robot.motorFL.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
                newmotorFRTarget = robot.motorFR.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newmotorBLTarget = robot.motorBL.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
                newmotorBRTarget = robot.motorBR.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
                robot.motorFL.setTargetPosition(newmotorFLTarget);
                robot.motorFR.setTargetPosition(newmotorFRTarget);
                robot.motorBL.setTargetPosition(newmotorBLTarget);
                robot.motorBR.setTargetPosition(newmotorBRTarget);

                // Turn On RUN_TO_POSITION
                robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                robot.motorFL.setPower(Math.abs(DRIVE_SPEED));
                robot.motorFR.setPower(Math.abs(DRIVE_SPEED));
                robot.motorBL.setPower(Math.abs(DRIVE_SPEED));
                robot.motorBR.setPower(Math.abs(DRIVE_SPEED));
                // keep looping while we are still active, and there is time left, and both motors are running.
                // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
                // its target position, the motion will stop.  This is "safer" in the event that the robot will
                // always end the motion as soon as possible.
                // However, if you require that BOTH motors have finished their moves before the robot continues
                // onto the next step, use (isBusy() || isBusy()) in the loop test.
                while (opModeIsActive() &&
                        (robot.motorFL.isBusy() && robot.motorFR.isBusy() && robot.motorBL.isBusy() && robot.motorBR.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("motorFL", "%7d", robot.motorFL.getCurrentPosition());
                    telemetry.addData("motorFR", "%7d", robot.motorFR.getCurrentPosition() );
                    telemetry.addData("motorBL", "%7d", robot.motorBL.getCurrentPosition());
                    telemetry.addData("motorBR", "%7d", robot.motorBR.getCurrentPosition());
                    telemetry.update();

                }

                // Stop all motion;
                stopRobot();

                // Turn off RUN_TO_POSITION
                robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }

        }
        else
        {
            moveLeft();
        }
    }
    public void moveLeft()
    {
        robot.motorFL.setPower(-teleOP_FORWARD_SPEED);      robot.motorFR.setPower(teleOP_FORWARD_SPEED);

        robot.motorBL.setPower(teleOP_FORWARD_SPEED);       robot.motorBR.setPower(-teleOP_FORWARD_SPEED);

    }
    public void moveLeft(float speed)
    {
        robot.motorFL.setPower(-speed);      robot.motorFR.setPower(speed);

        robot.motorBL.setPower(speed);       robot.motorBR.setPower(-speed);

    }
    public void turnleft(double speed)
    {

        robot.motorFL.setPower(-speed);         robot.motorFR.setPower(speed);
        robot.motorBL.setPower(-speed);         robot.motorBR.setPower(speed);
    }
    public void turnright(double speed)
    {

        robot.motorFL.setPower(speed);          robot.motorFR.setPower(-speed);
        robot.motorBL.setPower(speed);          robot.motorBR.setPower(-speed);
    }

    public void stopRobot()
    {
        robot.motorFL.setPower(0);
        robot.motorFR.setPower(0);
        robot.motorBL.setPower(0);
        robot.motorBR.setPower(0);
    }
    public void stopRobot(int seconds)
    {
        //delay
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < seconds)) {
            stopRobot();
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }
    }
    public void grabStone()
    {
        // TODO: 2019-12-11 add servo, and make method
    }
    public void releaseStone()
    {
        // TODO: 2019-12-11 add servo, and make method
    }
    public void lifterUp()
    {
        // TODO: 2019-12-11 add servo, and make method
    }
    public void lifterDown()
    {
        // TODO: 2019-12-11 add servo, and make method
    }




    String opMode ="";

    public void setMode(int opMode) {
        if(opMode==1)
        {
            this.opMode="AUTO";
        }
        else
        {
            this.opMode ="TELEOP";
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        //robot.gyro.calibrate();

        // make sure the gyro is calibrated before continuing
//        while (!isStopRequested() && robot.gyro.isCalibrating())  {
//            sleep(50);
//        }

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        this.opMode="";
    }

}
