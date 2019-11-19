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
                this.motorFL.setPower(1);
                this.motorFR.setPower(-1);
                this.motorBR.setPower(-1);
                this.motorBL.setPower(1);

            } else if (G1right_trigger > 0.5){ //forwards
                this.motorFL.setPower(-1);
                this.motorFR.setPower(1);
                this.motorBR.setPower(1);
                this.motorBL.setPower(-1);

            } else if(gamepad1.y){ //move claw down
                this.foundationClaw.setPosition(0);

            } else if(gamepad1.b){ //move claw 90 deg
                this.foundationClaw.setPosition(0.5);

            } else if(gamepad1.a){ //move claw up
                this.foundationClaw.setPosition(1);
            }else {
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
