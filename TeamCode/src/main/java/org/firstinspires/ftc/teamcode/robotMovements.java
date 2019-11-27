package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

public class robotMovements extends LinearOpMode implements robotVariable
{
    robotInit robot = new robotInit();
    ElapsedTime runtime = new ElapsedTime();
//    //Connecting to game controller elements
//    double G1rightStickY = -gamepad1.right_stick_y;
//    double G1rightStickX = -gamepad1.right_stick_x;
//    double G1leftStickY = -gamepad1.left_stick_y;
//    double G1leftStickX = -gamepad1.left_stick_x;
//    float G1left_trigger = gamepad1.left_trigger;
//    float G1right_trigger= gamepad1.right_trigger;
//    boolean G1buttonB = gamepad1.b;
//    float dpadThreshold = 0.2f;



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
    public void moveForward(String opMode, double inches)
    {

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
            robot.motorBL.setPower(teleOP_FORWARD_SPEED);
            robot.motorBR.setPower(teleOP_FORWARD_SPEED);
            robot.motorFL.setPower(teleOP_FORWARD_SPEED);
            robot.motorFR.setPower(teleOP_FORWARD_SPEED);
        }
    }

    public void moveBackward(String opMode, double inches)
    {

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
                robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }

        }
        else
        {
            robot.motorBL.setPower(-teleOP_FORWARD_SPEED);
            robot.motorBR.setPower(-teleOP_FORWARD_SPEED);
            robot.motorFL.setPower(-teleOP_FORWARD_SPEED);
            robot.motorFR.setPower(-teleOP_FORWARD_SPEED);
        }
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
    //for test
    void testRight()
    {
        int newmotorFLTarget;
        int newmotorFRTarget;
        int newmotorBLTarget;
        int newmotorBRTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            int FLnow =robot.motorFL.getCurrentPosition();
            int FRnow =robot.motorFR.getCurrentPosition();
            int BLnow =robot.motorBL.getCurrentPosition();
            int BRnow =robot.motorBR.getCurrentPosition();

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() + (int)COUNTS_PER_MOTOR_REV;
            newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int)COUNTS_PER_MOTOR_REV;
            newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int)COUNTS_PER_MOTOR_REV;
            newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int)COUNTS_PER_MOTOR_REV;
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
                telemetry.addData("motorFL", "%7d", Math.abs(robot.motorFL.getCurrentPosition()-FLnow));
                telemetry.addData("motorFR", "%7d", Math.abs(robot.motorFR.getCurrentPosition()-FRnow));
                telemetry.addData("motorBL", "%7d", Math.abs(robot.motorBL.getCurrentPosition()-BLnow));
                telemetry.addData("motorBR", "%7d", Math.abs(robot.motorBR.getCurrentPosition()-BRnow));
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
    public void moveRight(String opMode,double inches)
    {
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
            robot.motorFL.setPower(DRIVE_SPEED);
            robot.motorFR.setPower(-DRIVE_SPEED);
            robot.motorBL.setPower(-DRIVE_SPEED);
            robot.motorBR.setPower(DRIVE_SPEED);
        }
    }
    public void moveLeft(String opMode,double inches)
    {
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
            robot.motorFL.setPower(-DRIVE_SPEED);
            robot.motorFR.setPower(DRIVE_SPEED);
            robot.motorBL.setPower(DRIVE_SPEED);
            robot.motorBR.setPower(-DRIVE_SPEED);
        }
    }
    public void turnleft(double speed)
    {
        robot.motorFL.setPower(-speed);
        robot.motorFR.setPower(speed);
        robot.motorBL.setPower(-speed);
        robot.motorBR.setPower(speed);
    }
    public void turnright(double speed)
    {
        robot.motorFL.setPower(speed);
        robot.motorFR.setPower(-speed);
        robot.motorBL.setPower(speed);
        robot.motorBR.setPower(-speed);
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


    // IMPORTANT:  For Phone Camera, set 1) the camera source and 2) the orientation, based on how your phone is mounted:
    // 1) Camera Source.  Valid choices are:  BACK (behind screen) or FRONT (selfie side)
    // 2) Phone Orientation. Choices are: PHONE_IS_PORTRAIT = true (portrait) or PHONE_IS_PORTRAIT = false (landscape)
    //
    // NOTE: If you are running on a CONTROL HUB, with only one USB WebCam, you must select CAMERA_CHOICE = BACK; and PHONE_IS_PORTRAIT = false;
    //
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            " AV2BXJD/////AAABmcpiiweem0wwrt9f0UbVq5xY7zw2pWY0l6FVDthBpGXx5pMt8hZ92jzwz0U7GXRlmRVN3yLTGXevMsX/8dzp8+iS413YFnRhrCpd2W90/NvhX4UXk9CTqjb8SmH9BnAp72GrFBkaUMI/GHsS77eFTPxMw5gW5Hen01HqkszGE/mHg0MDonBUCKx4T3V5opfD/aCJesDjePoWVO/O8D4bAqGKo7IIx+IgSo9zHWWciQiACobgpzHoXog+wLIr+NTJgGPQKL5CVzKKcPnBKqTUpj1Uo9xxkL70anbmo6sMQOKuSvHV7hY093Gp8Zbh9pulihTmLYlJQ6HZgyfLPzhDVZeXp2pLjx1BSSRkot2auSC5";

    // Since ImageTarget trackables use mm to specifiy their dimensions, we must use mm for all the physical dimension.
    // We will define some constants and conversions here
    private static final float mmPerInch        = 25.4f;

    // fix this after designing
    private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    // Constant for Stone Target
    private static final float stoneZ = 2.00f * mmPerInch;

    // Constants for the center support targets
    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59;                                 // Units are degrees
    private static final float bridgeRotZ = 180;

    // Constants for perimeter targets
    private static final float halfField = 72 * mmPerInch;
    private static final float quadField  = 36 * mmPerInch;

    // Class Members
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate    = 0;
    private float phoneYRotate    = 0;
    private float phoneZRotate    = 0;


    String mode="";

    public void setMode(String mode)
    {
        this.mode =mode;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //init vuforia
            /*
             * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
             * We can pass Vuforia the handle to a camera preview resource (on the RC phone);
             * If no camera monitor is desired, use the parameter-less constructor instead (commented out below).
             */
            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            //VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(); //we don't want the phone lag

            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraDirection   = CAMERA_CHOICE;

            //  Instantiate the Vuforia engine
            vuforia = ClassFactory.getInstance().createVuforia(parameters);

            // Load the data sets for the trackable objects. These particular data
            // sets are stored in the 'assets' part of our application.
            VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

            VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
            stoneTarget.setName("Stone Target");
            VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
            blueRearBridge.setName("Blue Rear Bridge");
            VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
            redRearBridge.setName("Red Rear Bridge");
            VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
            redFrontBridge.setName("Red Front Bridge");
            VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
            blueFrontBridge.setName("Blue Front Bridge");
            VuforiaTrackable red1 = targetsSkyStone.get(5);
            red1.setName("Red Perimeter 1");
            VuforiaTrackable red2 = targetsSkyStone.get(6);
            red2.setName("Red Perimeter 2");
            VuforiaTrackable front1 = targetsSkyStone.get(7);
            front1.setName("Front Perimeter 1");
            VuforiaTrackable front2 = targetsSkyStone.get(8);
            front2.setName("Front Perimeter 2");
            VuforiaTrackable blue1 = targetsSkyStone.get(9);
            blue1.setName("Blue Perimeter 1");
            VuforiaTrackable blue2 = targetsSkyStone.get(10);
            blue2.setName("Blue Perimeter 2");
            VuforiaTrackable rear1 = targetsSkyStone.get(11);
            rear1.setName("Rear Perimeter 1");
            VuforiaTrackable rear2 = targetsSkyStone.get(12);
            rear2.setName("Rear Perimeter 2");

            // For convenience, gather together all the trackable objects in one easily-iterable collection */
            List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
            allTrackables.addAll(targetsSkyStone);

            /**
             * In order for localization to work, we need to tell the system where each target is on the field, and
             * where the phone resides on the robot.  These specifications are in the form of <em>transformation matrices.</em>
             * Transformation matrices are a central, important concept in the math here involved in localization.
             * See <a href="https://en.wikipedia.org/wiki/Transformation_matrix">Transformation Matrix</a>
             * for detailed information. Commonly, you'll encounter transformation matrices as instances
             * of the {@link OpenGLMatrix} class.
             *
             * If you are standing in the Red Alliance Station looking towards the center of the field,
             *     - The X axis runs from your left to the right. (positive from the center to the right)
             *     - The Y axis runs from the Red Alliance Station towards the other side of the field
             *       where the Blue Alliance Station is. (Positive is from the center, towards the BlueAlliance station)
             *     - The Z axis runs from the floor, upwards towards the ceiling.  (Positive is above the floor)
             *
             * Before being transformed, each target image is conceptually located at the origin of the field's
             *  coordinate system (the center of the field), facing up.
             */

            // Set the position of the Stone Target.  Since it's not fixed in position, assume it's at the field origin.
            // Rotated it to to face forward, and raised it to sit on the ground correctly.
            // This can be used for generic target-centric approach algorithms
            stoneTarget.setLocation(OpenGLMatrix
                    .translation(0, 0, stoneZ)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

            //Set the position of the bridge support targets with relation to origin (center of field)
            blueFrontBridge.setLocation(OpenGLMatrix
                    .translation(-bridgeX, bridgeY, bridgeZ)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, bridgeRotZ)));

            blueRearBridge.setLocation(OpenGLMatrix
                    .translation(-bridgeX, bridgeY, bridgeZ)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, bridgeRotZ)));

            redFrontBridge.setLocation(OpenGLMatrix
                    .translation(-bridgeX, -bridgeY, bridgeZ)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, 0)));

            redRearBridge.setLocation(OpenGLMatrix
                    .translation(bridgeX, -bridgeY, bridgeZ)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, 0)));

            //Set the position of the perimeter targets with relation to origin (center of field)
            red1.setLocation(OpenGLMatrix
                    .translation(quadField, -halfField, mmTargetHeight)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));

            red2.setLocation(OpenGLMatrix
                    .translation(-quadField, -halfField, mmTargetHeight)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));

            front1.setLocation(OpenGLMatrix
                    .translation(-halfField, -quadField, mmTargetHeight)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90)));

            front2.setLocation(OpenGLMatrix
                    .translation(-halfField, quadField, mmTargetHeight)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 90)));

            blue1.setLocation(OpenGLMatrix
                    .translation(-quadField, halfField, mmTargetHeight)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));

            blue2.setLocation(OpenGLMatrix
                    .translation(quadField, halfField, mmTargetHeight)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));

            rear1.setLocation(OpenGLMatrix
                    .translation(halfField, quadField, mmTargetHeight)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , -90)));

            rear2.setLocation(OpenGLMatrix
                    .translation(halfField, -quadField, mmTargetHeight)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

            //
            // Create a transformation matrix describing where the phone is on the robot.
            //
            // NOTE !!!!  It's very important that you turn OFF your phone's Auto-Screen-Rotation option.
            // Lock it into Portrait for these numbers to work.
            //
            // Info:  The coordinate frame for the robot looks the same as the field.
            // The robot's "forward" direction is facing out along X axis, with the LEFT side facing out along the Y axis.
            // Z is UP on the robot.  This equates to a bearing angle of Zero degrees.
            //
            // The phone starts out lying flat, with the screen facing Up and with the physical top of the phone
            // pointing to the LEFT side of the Robot.
            // The two examples below assume that the camera is facing forward out the front of the robot.

            // We need to rotate the camera around it's long axis to bring the correct camera forward.
            if (CAMERA_CHOICE == BACK) {
                phoneYRotate = -90;
            } else {
                phoneYRotate = 90;
            }

            // Rotate the phone vertical about the X axis if it's in portrait mode
            if (PHONE_IS_PORTRAIT) {
                phoneXRotate = 90 ;
            }

            // Next, translate the camera lens to where it is on the robot.
            // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
            final float CAMERA_FORWARD_DISPLACEMENT  = 4.0f * mmPerInch;   // eg: Camera is 4 Inches in front of robot center
            final float CAMERA_VERTICAL_DISPLACEMENT = 8.0f * mmPerInch;   // eg: Camera is 8 Inches above ground
            final float CAMERA_LEFT_DISPLACEMENT     = 0;     // eg: Camera is ON the robot's center line

            OpenGLMatrix robotFromCamera = OpenGLMatrix
                    .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

            /**  Let all the trackable listeners know where the phone is.  */
            for (VuforiaTrackable trackable : allTrackables) {
                ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
            }
            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            // run until the end of the match (driver presses STOP)





        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
    }

}
