/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
    Strafe left: BL = - BR = -
    Strafe right: FL = - FR = -
    Drive Back : FR = - BR = -
    Drive Forward: Fl = - Bl = -
    Turn Left: FL = - or all positive
    Turn Right = all negative
 */

// Still in progress
// Just for Saturday Meet


/*  1) back up
    2) left few sec
    3) drive forward
* */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="autoCode", group="auto")
//@Disabled

public class autoCode extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime     runtime = new ElapsedTime();
    DcMotor motorFL = null;
    DcMotor motorFR = null;
    DcMotor motorBL = null;
    DcMotor motorBR = null;
//    Servo servoMarker;

    static final double     FORWARD_SPEED = 0.4;
    static final double     FORWARD_SPEED2 = 0.05;
    static final double     STOP_SPEED = 0;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        motorFL = hardwareMap.get(DcMotor.class, "motor_fl");
        motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
        motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
        motorBR = hardwareMap.get(DcMotor.class, "motor_br");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
/*
        // strafe to the right
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            motorFL.setPower(-FORWARD_SPEED);
            motorFR.setPower(-FORWARD_SPEED);
            motorBR.setPower(FORWARD_SPEED);
            motorBL.setPower(FORWARD_SPEED);

            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            motorFL.setPower(STOP_SPEED);
            motorFR.setPower(STOP_SPEED);
            motorBR.setPower(STOP_SPEED);
            motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }
*/
        // Drive backward for 0.3 seconds
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.3)) {
            motorFL.setPower(FORWARD_SPEED);
            motorFR.setPower(-FORWARD_SPEED);
            motorBR.setPower(-FORWARD_SPEED);
            motorBL.setPower(FORWARD_SPEED);
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            motorFR.setPower(STOP_SPEED);
            motorFL.setPower(STOP_SPEED);
            motorBR.setPower(STOP_SPEED);
            motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }


        // strafe to the left
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.8)) {
            motorFL.setPower(FORWARD_SPEED);
            motorFR.setPower(-FORWARD_SPEED);
            motorBR.setPower(-FORWARD_SPEED);
            motorBL.setPower(FORWARD_SPEED);

            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.addData("Shaft Left",null);
            telemetry.update();
        }

        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            motorFL.setPower(STOP_SPEED);
            motorFR.setPower(STOP_SPEED);
            motorBR.setPower(STOP_SPEED);
            motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }

        //drive forward to crater
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3.0)) {
            motorFL.setPower(-FORWARD_SPEED);
            motorFR.setPower(FORWARD_SPEED);
            motorBR.setPower(FORWARD_SPEED);
            motorBL.setPower(-FORWARD_SPEED);
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            motorFR.setPower(STOP_SPEED);
            motorFL.setPower(STOP_SPEED);
            motorBR.setPower(STOP_SPEED);
            motorBL.setPower(STOP_SPEED);
            telemetry.addData("Motor", "Stopped");    //
            telemetry.update();
        }
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
  }
}



