package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "re_CurrentV_GM")
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
