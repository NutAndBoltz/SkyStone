package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "re_new_teleOp")
public class re_new_teleOp extends robotMovements {
    private double motorFLspeed=0;
    private double motorFRspeed=0;
    private double motorBLspeed=0;
    private double motorBRspeed=0;
    void adjust()
    {
        if(motorFLspeed>1)
        {
            motorFLspeed=1;
        }
        if(motorFRspeed>1)
        {
            motorFLspeed=1;
        }
        if(motorBLspeed>1)
        {
            motorFLspeed=1;
        }
        if(motorBLspeed>1)
        {
            motorFLspeed=1;
        }

        if(motorFLspeed<1)
        {
            motorFLspeed=-1;
        }
        if(motorFRspeed<1)
        {
            motorFLspeed=-1;
        }
        if(motorBLspeed<1)
        {
            motorFLspeed=-1;
        }
        if(motorBLspeed<1)
        {
            motorFLspeed=-1;
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();


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
            if(gamepad1.y)
            {
                //move claw down
                robot.foundationClaw.setPosition(0);
            }
            if(gamepad1.b)
            {
                //move claw 90 deg
                robot.foundationClaw.setPosition(0.5);
            }
            if(gamepad1.a)
            {
                //move claw up
                robot.foundationClaw.setPosition(1);
            }
            if(G1rightStickX!=0) {
                motorFLspeed = G1rightStickX;
                motorFRspeed = G1rightStickX;
                motorBLspeed = G1rightStickX;
                motorBRspeed = G1rightStickX;
                adjust();

            }
            else
            {
                motorFLspeed=G1leftStickY;
                motorFRspeed=G1leftStickY;
                motorBLspeed=G1leftStickY;
                motorBRspeed=G1leftStickY;
                adjust();

                motorFLspeed += G1leftStickX*2;
                motorFRspeed -= G1leftStickX*2;
                motorBLspeed -= G1leftStickX*2;
                motorBRspeed += G1leftStickX*2;
                adjust();

                motorFLspeed +=G1leftStickY*0.5;
                motorFRspeed +=G1leftStickY*0.5;
                motorBLspeed +=G1leftStickY*0.5;
                motorBRspeed +=G1leftStickY*0.5;
                adjust();
            }









            robot.motorFL.setPower(motorFLspeed);
            robot.motorFR.setPower(motorFRspeed);
            robot.motorBL.setPower(motorBLspeed);
            robot.motorBR.setPower(motorBRspeed);

            telemetry.addData("Status", "Running");
            telemetry.addData("motorFL",motorFLspeed);
            telemetry.addData("motorFR",motorFRspeed);
            telemetry.addData("motorBL",motorBLspeed);
            telemetry.addData("motorBR",motorBRspeed);
            telemetry.update();
        }
    }
}
