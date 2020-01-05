package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Re_CurrentV_GM")
public class Re_CurrentV_GM extends robotMovements {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        setMode(TELEOP);

        while(opModeIsActive())
        {
            //Connecting to game controller elements
            double G1leftStickX =-gamepad1.left_stick_x;
            double G1rightStickX =-gamepad1.right_stick_x;
            double G1left_trigger = gamepad1.left_trigger;
            double G1right_trigger= gamepad1.right_trigger;

            if(G1left_trigger > 0.5) { //forwards
                reportTick();
//                moveForward();
//                /* setPower works
                robot.motorFL.setPower(G1left_trigger);
                robot.motorFR.setPower(G1left_trigger);
                robot.motorBR.setPower(G1left_trigger);
                robot.motorBL.setPower(G1left_trigger);
//                */
            } if (G1right_trigger > 0.5){ //backwards
//                moveBackward();
//                /* setPower works
                robot.motorFL.setPower(-G1right_trigger);
                robot.motorFR.setPower(-G1right_trigger);
                robot.motorBR.setPower(-G1right_trigger);
                robot.motorBL.setPower(-G1right_trigger);
//                 */

            } if(gamepad1.y){ //move claw down
                robot.foundationClaw.setPosition(0);

            } if(gamepad1.b){ //move claw 90 deg
                robot.foundationClaw.setPosition(0.5);

            } if(gamepad1.a) { //move claw up
                robot.foundationClaw.setPosition(1);
            }
            if(G1rightStickX > 0.3)
            {
//                moveRight();
//                setPower works
                robot.motorFL.setPower(G1rightStickX);
                robot.motorFR.setPower(-G1rightStickX);
                robot.motorBR.setPower(G1rightStickX);
                robot.motorBL.setPower(-G1rightStickX);
//
            }
            if(G1rightStickX < -0.3)
            {
//                moveLeft();
//                 setPower works
//                reportTick();
                robot.motorFL.setPower(-G1rightStickX);
                robot.motorFR.setPower(G1rightStickX);
                robot.motorBR.setPower(-G1rightStickX);
                robot.motorBL.setPower(G1rightStickX);
//
            }
            else {
                robot.motorFL.setPower(G1leftStickX);
                robot.motorFR.setPower(-G1leftStickX);
                robot.motorBR.setPower(-G1leftStickX);
                robot.motorBL.setPower(G1leftStickX);
            }

            /* IGNORE: this is for arm movemnt
            else if(gamepad1.x){ //auto pick up a stone
                //move servoArm up flat (0.5)
                robot.servoArm.setPosition(0.5);

                //move servoOrange out (1)
                robot.servoOrange.setPosition(1);

                //move servo2 out (0.3)
                robot.servo2.setPosition(0);

                //move servoArm down (0.4)
                robot.servoArm.setPosition(0.4);

                //move servoOrange out (0.6)
                robot.servoOrange.setPosition(0.6);

                //move servoArm down (0.7)
                robot.servoArm.setPosition(0.7);

                //move servo2 in (0.4)
                robot.servo2.setPosition(0.4);

                //move servoArm up
                robot.servoArm.setPosition(0.5);

                //move servoOrange in (0.5)
                robot.servoOrange.setPosition(0.5);
            }

            if(gamepad2.y){ //move orange servo up
                robot.servoOrange.setPosition(0);
            }
            if(gamepad2.a){ //move orange servo down
                robot.servoOrange.setPosition(1);
            }
            if(gamepad2.x){ //move servo2 up
                robot.servo2.setPosition(0);
            }
            if(gamepad2.b){ //move servo2 down
                robot.servo2.setPosition(1);
            }
            if(gamepad2.left_trigger > 0.3){ //move arm down
                robot.servoArm.setPosition(1);
            }
            if(gamepad2.right_trigger > 0.3){ //move arm up
                robot.servoArm.setPosition(0);
            }
            else if(G1rightStickY> -0.3)
            {
                moveRight(0);

            } else if(G1leftStickY>0.3)
            {
                moveRight(0);
                front wheels go forward

            }
            */
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
