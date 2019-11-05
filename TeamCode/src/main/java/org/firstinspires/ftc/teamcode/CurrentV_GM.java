package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class CurrentV_GM extends LinearOpMode {
    private ElapsedTime     runtime = new ElapsedTime();
    DcMotor motorFL;
    DcMotor motorFR;
    DcMotor motorBL;
    DcMotor motorBR;

    static final double     FORWARD_SPEED = 0.05;
    static final double     FORWARD_SPEED2 = 0.4;


    @Override
    public void runOpMode() {
        motorFL = hardwareMap.get(DcMotor.class, "motor_fl");
        motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
        motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
        motorBR = hardwareMap.get(DcMotor.class, "motor_br");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //Connecting to game controller elements
            double G1rightStickY = -gamepad1.right_stick_y;
            double G1leftStickY = -gamepad1.left_stick_y;
            float G1left_trigger = gamepad1.left_trigger;
            float G1right_trigger= gamepad1.right_trigger;
            boolean G1buttonB = gamepad1.b;
            float dpadThreshold = 0.2f;


            if (G1left_trigger > 0.5) { //backwards
                this.motorFL.setPower(1);
                this.motorFR.setPower(-1);
                this.motorBR.setPower(-1);
                this.motorBL.setPower(1);

            } else if (G1right_trigger > 0.5){ //forwards
                this.motorFL.setPower(-1);
                this.motorFR.setPower(1);
                this.motorBR.setPower(1);
                this.motorBL.setPower(-1);

            } else {
                //strafe right
                this.motorFL.setPower(G1leftStickY);
                this.motorFR.setPower(G1leftStickY);
                //strafe left
                this.motorBR.setPower(-G1rightStickY);
                this.motorBL.setPower(-G1rightStickY);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}