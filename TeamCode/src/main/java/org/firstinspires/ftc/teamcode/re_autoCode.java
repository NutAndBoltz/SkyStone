package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous(name = "re_autoCode",group = "auto")
public class re_autoCode extends robotMovements {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        robot.runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 10.0)) {
            robot.motorFL.setPower(STOP_SPEED);
            robot.motorFR.setPower(STOP_SPEED);
            robot.motorBR.setPower(STOP_SPEED);
            robot.motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }

        // strafe to the left
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            robot.motorFL.setPower(auto_FORWARD_SPEED);
            robot.motorFR.setPower(auto_FORWARD_SPEED);
            robot.motorBR.setPower(-auto_FORWARD_SPEED);
            robot.motorBL.setPower(-auto_FORWARD_SPEED);

            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.addData("Shaft Left",null);
            telemetry.update();
        }

        while (opModeIsActive() && (runtime.seconds() < 8.0)) {
            robot.motorFL.setPower(STOP_SPEED);
            robot.motorFR.setPower(STOP_SPEED);
            robot.motorBR.setPower(STOP_SPEED);
            robot.motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }
        //drive backward to bridge
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() <1.0)) {
            robot.motorFL.setPower(auto_FORWARD_SPEED);
            robot.motorFR.setPower(-auto_FORWARD_SPEED);
            robot.motorBR.setPower(-auto_FORWARD_SPEED);
            robot.motorBL.setPower(auto_FORWARD_SPEED);
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        while (opModeIsActive() && (runtime.seconds() < 0.9)) {
            robot.motorFR.setPower(STOP_SPEED);
            robot.motorFL.setPower(STOP_SPEED);
            robot.motorBR.setPower(STOP_SPEED);
            robot.motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");
            telemetry.addData("0.9sec",null);
            telemetry.update();
        }
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
