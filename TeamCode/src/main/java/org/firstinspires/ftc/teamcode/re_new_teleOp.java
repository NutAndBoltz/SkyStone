package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "re_new_teleOp")
public class re_new_teleOp extends robotMovements {

    private float G1leftStickX;
    private float G1rightStickX;
    private float G1rightStickY;
    private float G1leftStickY;




//    public void setAngle(double x, double y)
//    {
//        int TargetDegree=(int)Math.toDegrees(Math.atan2(y,x));
//        TargetDegree+=90;//going Forward is 0 degree
//        if(TargetDegree>360)
//        {
//            TargetDegree-=360;
//        }
//
//
//        int CurrentDegree =robot.gyro.getIntegratedZValue();
//        while(TargetDegree==CurrentDegree)
//        {
//
//
//        }
//
//    }



    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        setMode(TELEOP);

G1rightStickY=0;
G1leftStickY=0;
G1rightStickX=0;
G1leftStickY=0;
        while(opModeIsActive())
        {
            G1leftStickX = gamepad1.left_stick_x;
            G1rightStickX = gamepad1.right_stick_x;
            G1rightStickY = -gamepad1.right_stick_y;
            G1leftStickY = -gamepad1.left_stick_y;
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
            if(G1leftStickY>0.3)
            {
                moveForward();

            }
            else if(G1leftStickY<-0.3)
            {
                moveBackward();
            }
            else if(G1leftStickX<-0.3)
            {
                moveLeft();
            }
            else if(G1leftStickX>0.3)
            {
                moveRight();
            }
            else if(G1rightStickX>0.3)
            {
                turnright(Math.abs(G1rightStickX));
            }
            else if(G1rightStickX<-0.3)
            {
                turnleft(Math.abs(G1rightStickX));
            }
            else
            {
                stopRobot();
            }




            telemetry.addData("Status", "Running");
            telemetry.addLine();
            telemetry.addLine("G1leftStick X : "+G1leftStickX+"\t"+"G1rightStick X : "+G1rightStickX);
            telemetry.addLine();
            telemetry.addLine("G1leftStick Y : "+G1leftStickY+"\t"+"G1rightStick Y : "+G1rightStickY);
            telemetry.update();

        }


    }
}
