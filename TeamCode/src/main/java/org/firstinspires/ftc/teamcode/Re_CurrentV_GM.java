package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "re_CurrentV_GM")
public class Re_CurrentV_GM extends robotMovements {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        while(opModeIsActive())
        {



            if (G1left_trigger > 0.5) { //backwards
                moveBackward("TeleOP",0);

            } else if (G1right_trigger > 0.5){ //forwards
                moveForward("TeleOp",0);

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
