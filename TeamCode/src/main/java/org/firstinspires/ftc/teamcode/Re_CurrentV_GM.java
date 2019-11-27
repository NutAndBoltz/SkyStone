package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Disabled
@TeleOp(name = "re_CurrentV_GM")
public class Re_CurrentV_GM extends robotMovements {


    @Override
    public void runOpMode() throws InterruptedException {
        setMode(TELEOP);
        super.runOpMode();

        while(opModeIsActive())
        {
            //Connecting to game controller elements

            double G1leftStickX =-gamepad1.left_stick_x;
            double G1rightStickX =-gamepad1.right_stick_x;
            double G1rightStickY = -gamepad1.right_stick_y;
            double G1leftStickY = -gamepad1.left_stick_y;
            float G1left_trigger = gamepad1.left_trigger;
            float G1right_trigger= gamepad1.right_trigger;
            boolean G1buttonB = gamepad1.b;
            float dpadThreshold = 0.2f;





            if (G1left_trigger> 0.5) { //backwards
                moveBackward(0);

            } else if (G1right_trigger > 0.5){ //forwards
                moveForward(0);

            } else if(gamepad1.y){ //move claw down
                robot.foundationClaw.setPosition(0);

            } else if(gamepad1.b){ //move claw 90 deg
                robot.foundationClaw.setPosition(0.5);

            } else if(gamepad1.a){ //move claw up
                robot.foundationClaw.setPosition(1);
            }
            else if(G1rightStickX>0.3)
            {
                moveRight(0);
            }
            else if(G1rightStickX<-0.3)
            {
                moveLeft(0);
            }
            else {

                robot.motorFL.setPower(G1leftStickX);
                robot.motorFR.setPower(-G1leftStickX);
                robot.motorBR.setPower(-G1leftStickX);
                robot.motorBL.setPower(G1leftStickX);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
