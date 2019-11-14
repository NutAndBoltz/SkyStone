package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous(name = "re_autoCode",group = "auto")
public class re_autoCode extends robotInitialize implements robotVariable {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 10.0)) {
            motorFL.setPower(STOP_SPEED);
            motorFR.setPower(STOP_SPEED);
            motorBR.setPower(STOP_SPEED);
            motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }

        // strafe to the left
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            motorFL.setPower(auto_FORWARD_SPEED);
            motorFR.setPower(auto_FORWARD_SPEED);
            motorBR.setPower(-auto_FORWARD_SPEED);
            motorBL.setPower(-auto_FORWARD_SPEED);

            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.addData("Shaft Left",null);
            telemetry.update();
        }

        while (opModeIsActive() && (runtime.seconds() < 8.0)) {
            motorFL.setPower(STOP_SPEED);
            motorFR.setPower(STOP_SPEED);
            motorBR.setPower(STOP_SPEED);
            motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }
        //drive backward to bridge
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() <1.0)) {
            motorFL.setPower(auto_FORWARD_SPEED);
            motorFR.setPower(-auto_FORWARD_SPEED);
            motorBR.setPower(-auto_FORWARD_SPEED);
            motorBL.setPower(auto_FORWARD_SPEED);
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        while (opModeIsActive() && (runtime.seconds() < 0.9)) {
            motorFR.setPower(STOP_SPEED);
            motorFL.setPower(STOP_SPEED);
            motorBR.setPower(STOP_SPEED);
            motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");
            telemetry.addData("0.9sec",null);
            telemetry.update();
        }
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
