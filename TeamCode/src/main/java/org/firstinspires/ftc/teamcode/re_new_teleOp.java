package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "re_new_teleOp")
public class re_new_teleOp extends robotMovements {


    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        double motorFLspeed=0;
        double motorFRspeed=0;
        double motorBLspeed=0;
        double motorBRspeed=0;

        while(opModeIsActive())
        {
            double G1leftStickX =-gamepad1.left_stick_x;
            double G1rightStickX =-gamepad1.right_stick_x;
            double G1rightStickY = -gamepad1.right_stick_y;
            double G1leftStickY = -gamepad1.left_stick_y;
            float G1left_trigger = gamepad1.left_trigger;
            float G1right_trigger= gamepad1.right_trigger;
            boolean G1buttonB = gamepad1.b;
            float dpadThreshold = 0.2f;



            motorFLspeed=((G1leftStickX+G1leftStickY)*0.4+(G1rightStickX+G1rightStickY)*0.1);
            motorFRspeed=((-G1leftStickX+G1leftStickY)*0.4+(-G1rightStickX+G1rightStickY)*0.1);
            motorBLspeed=((-G1leftStickX+G1leftStickY)*0.4+(-G1rightStickX+G1rightStickY)*0.1);
            motorBRspeed=((G1leftStickX+G1leftStickY)*0.4+(G1rightStickX+G1rightStickY)*0.1);
            robot.motorFL.setPower(motorFLspeed);
            robot.motorFR.setPower(motorFRspeed);
            robot.motorBL.setPower(motorBLspeed);
            robot.motorBR.setPower(motorBRspeed);

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
