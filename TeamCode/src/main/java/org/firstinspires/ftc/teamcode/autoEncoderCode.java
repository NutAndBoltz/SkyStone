package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="autoEncoderCode", group="auto")
//@Disabled
public class autoEncoderCode extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 2240;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = COUNTS_PER_MOTOR_REV / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     Counts_PER_DegreeTurn   = 24;
    static final double     DRIVE_SPEED             = 0.5;

    /* Declare OpMode members. */
    DcMotor motorFL = null;
    DcMotor motorFR = null;
    DcMotor motorBL = null;
    DcMotor motorBR = null;
    Servo foundationClaw;



    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        motorFL = hardwareMap.get(DcMotor.class, "motor_fl");
        motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
        motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
        motorBR = hardwareMap.get(DcMotor.class, "motor_br");

        //set direction for each mecanum wheel
        motorFL.setDirection(DcMotor.Direction.FORWARD);
        motorBL.setDirection(DcMotor.Direction.FORWARD);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        //Reset Encoders
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Encoder Mode
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //initiate the servo claw that will move the foundation
        foundationClaw = hardwareMap.get(Servo.class, "foundationClaw");
        //place the claw at its start position so the robot satisfies measurement requirements
        foundationClaw.setPosition(1);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at motorFl: %7d, motorFR:%7d, motorBL: %7d, motoBR: %7d",
                motorFL.getCurrentPosition(),
                motorFR.getCurrentPosition(),
                motorBL.getCurrentPosition(),
                motorBR.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {

            //Move servo arm up
            foundationClaw.setPosition(0);

            //Move forward to a little bit before the edge of the foundation
            driveForward(16);

            //Strafe right to the middle of the foundation
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 1)) {
                motorFL.setPower(1);
                motorFR.setPower(1);
                motorBR.setPower(-1);
                motorBL.setPower(-1);

                telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                telemetry.addData("Shaft Left",null);
                telemetry.update();
            }


            //Move servo arm down to latch onto foundation squares
            foundationClaw.setPosition(1);

            //delay
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 3.0)) {
                stopRobot();
                telemetry.addData("Motor", "Stopped");    //
                telemetry.update();
            }

            //Move backward into the depot (leave enough space for robot)
            driveBackward(10);

            //Pick up claw
            foundationClaw.setPosition(0.7);

            //Strafe left until stopped under the sky bridge
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 3)) {
                motorFL.setPower(DRIVE_SPEED);
                motorFR.setPower(DRIVE_SPEED);
                motorBR.setPower(-DRIVE_SPEED);
                motorBL.setPower(-DRIVE_SPEED);

                telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
                telemetry.addData("Shaft Left",null);
                telemetry.update();
            }

            stopRobot ();



        }


    }

    // Drive Forward Func
    public void driveForward(int inches) {
        int newmotorFLTarget;
        int newmotorFRTarget;
        int newmotorBLTarget;
        int newmotorBRTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = motorFL.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newmotorFRTarget = motorFR.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newmotorBLTarget = motorBL.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            newmotorBRTarget = motorBR.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            motorFL.setTargetPosition(newmotorFLTarget);
            motorFR.setTargetPosition(newmotorFRTarget);
            motorBL.setTargetPosition(newmotorBLTarget);
            motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorFL.setPower(Math.abs(DRIVE_SPEED));
            motorFR.setPower(Math.abs(DRIVE_SPEED));
            motorBL.setPower(Math.abs(DRIVE_SPEED));
            motorBR.setPower(Math.abs(DRIVE_SPEED));
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (motorFL.isBusy() && motorFR.isBusy() && motorBL.isBusy() && motorBR.isBusy())) {

                // Display it for the driver.
                telemetry.addData("motorFL", "%7d", motorFL.getCurrentPosition());
                telemetry.addData("motorFR", "%7d", motorFR.getCurrentPosition() );
                telemetry.addData("motorBL", "%7d", motorBL.getCurrentPosition());
                telemetry.addData("motorBR", "%7d", motorBR.getCurrentPosition());
                telemetry.update();

            }

            // Stop all motion;
            stopRobot();

            // Turn off encoder mode
            motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
    }

    // Drive Backward Function
    public void driveBackward(int inches) {
        int newmotorFLTarget;
        int newmotorFRTarget;
        int newmotorBLTarget;
        int newmotorBRTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            //turn on encoder mode
            motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            // Determine new target position, and pass to motor controller
            newmotorFLTarget = motorFL.getCurrentPosition() + (int)(inches * -COUNTS_PER_INCH);
            newmotorFRTarget = motorFR.getCurrentPosition() + (int)(inches * -COUNTS_PER_INCH);
            newmotorBLTarget = motorBL.getCurrentPosition() + (int)(inches * -COUNTS_PER_INCH);
            newmotorBRTarget = motorBR.getCurrentPosition() + (int)(inches * -COUNTS_PER_INCH);
            motorFL.setTargetPosition(newmotorFLTarget);
            motorFR.setTargetPosition(newmotorFRTarget);
            motorBL.setTargetPosition(newmotorBLTarget);
            motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorFL.setPower(Math.abs(DRIVE_SPEED));
            motorFR.setPower(Math.abs(DRIVE_SPEED));
            motorBL.setPower(Math.abs(DRIVE_SPEED));
            motorBR.setPower(Math.abs(DRIVE_SPEED));
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (motorFL.isBusy() && motorFR.isBusy() && motorBL.isBusy() && motorBR.isBusy())) {

                // Display it for the driver.
                telemetry.addData("motorFL", "%7d", motorFL.getCurrentPosition());
                telemetry.addData("motorFR", "%7d", motorFR.getCurrentPosition() );
                telemetry.addData("motorBL", "%7d", motorBL.getCurrentPosition());
                telemetry.addData("motorBR", "%7d", motorBR.getCurrentPosition());
                telemetry.update();

            }

            // Stop all motion;
            stopRobot();

            // Turn off encoder mode
            motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
    }

    // Stop Robot Func
    public void stopRobot () {
        motorFL.setPower(0);
        motorFR.setPower(0);
        motorBL.setPower(0);
        motorBR.setPower(0);
    }
}