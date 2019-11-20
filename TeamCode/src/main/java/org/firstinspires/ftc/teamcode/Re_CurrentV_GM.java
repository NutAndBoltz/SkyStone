package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "re_CurrentV_GM")
public class Re_CurrentV_GM extends robotMovements {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        while(opModeIsActive())
        {
            //Connecting to game controller elements
            double G1rightStickY = -gamepad1.right_stick_y;
            double G1leftStickY = -gamepad1.left_stick_y;
            float G1left_trigger = gamepad1.left_trigger;
            float G1right_trigger= gamepad1.right_trigger;
            boolean G1buttonB = gamepad1.b;
            float dpadThreshold = 0.2f;


            if (G1left_trigger > 0.5) { //backwards
                robot.motorFL.setPower(1);
                robot.motorFR.setPower(-1);
                robot.motorBR.setPower(-1);
                robot.motorBL.setPower(1);

            } else if (G1right_trigger > 0.5){ //forwards
                robot.motorFL.setPower(-1);
                robot.motorFR.setPower(1);
                robot.motorBR.setPower(1);
                robot.motorBL.setPower(-1);

            } else if(gamepad1.y){ //move claw down
                robot.foundationClaw.setPosition(0);

            } else if(gamepad1.b){ //move claw 90 deg
                robot.foundationClaw.setPosition(0.5);

            } else if(gamepad1.a){ //move claw up
                robot.foundationClaw.setPosition(1);
            }else {
                //strafe right
                robot.motorFL.setPower(G1leftStickY);
                robot.motorFR.setPower(G1leftStickY);
                //strafe left
                robot.motorBR.setPower(-G1rightStickY);
                robot.motorBL.setPower(-G1rightStickY);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
