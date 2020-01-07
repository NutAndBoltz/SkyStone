/* Copyright (c) 2019 FIRST. All rights reserved.
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

package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

/**
 * This 2019-2020 OpMode illustrates the basics of using the Vuforia localizer to determine
 * positioning and orientation of robot on the SKYSTONE FTC field.
 * The code is structured as a LinearOpMode
 *
 * When images are located, Vuforia is able to determine the position and orientation of the
 * image relative to the camera.  This sample code then combines that information with a
 * knowledge of where the target images are on the field, to determine the location of the camera.
 *
 * From the Audience perspective, the Red Alliance station is on the right and the
 * Blue Alliance Station is on the left.

 * Eight perimeter targets are distributed evenly around the four perimeter walls
 * Four Bridge targets are located on the bridge uprights.
 * Refer to the Field Setup manual for more specific location details
 *
 * A final calculation then uses the location of the camera on the robot to determine the
 * robot's location and orientation on the field.
 *
 * @see VuforiaLocalizer
 * @see VuforiaTrackableDefaultListener
 * see  skystone/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */


@Autonomous(name="VuforiaAuto", group ="auto")

public class VuforiaAuto extends robotMovements {

    private static final double UNKNOWNSPEED = 0;
    private static final int UNKNOWNDISTANCE = 0;


    //current coodination of robot to use in vuforia
    /**
     * coordination system in Vuforia: 0,0,0 => center of field
     * unit: mm, but we can get inch data and multiply mmperInch when using it
     * todo figure out orientation of coordinate
     * todo get Initial Robot position (IMPORTANT: NEED SPECIFICALLY SINCE IT MIGHT MESS UP WHOLE MOVEMENTS IF NOT ACCURATE)
     *
     */


    int CurrentX=0,CurrentY=0; //Initial position: 60cm from left wall, wheel touching the back wall as well

    Vuforia_Thread vuforia_thread;
    Thread thread;
    Boolean STOP_VUFORIA_THREAD=false;





    public void moveFoundation()
    {
        //move foundation
        //Move servo arm up
        robot.foundationClaw.setPosition(0);
        //Move forward to a little bit before the edge of the foundation
        moveBackward(32);
        //VuforiaTrackableDefaultListener vuforiaTrackableDefaultListener =new VuforiaTrackableDefaultListener();


        vuforiaData.getCurrentposition();

        //Strafe left
        moveLeft(16);

        CurrentX+=16; // TODO: 2019-12-11 figure out exact position

        //Move servo arm down to latch onto foundation squares
        robot.foundationClaw.setPosition(0.6);
        //delay
        stopRobot(2);
        //Move backward into the depot (leave enough space for robot)
        moveForward(36);
        CurrentY+=36;// TODO: 2019-12-11 figure out exact position
        //delay
        stopRobot(1);
        //Move servo arm up
        robot.foundationClaw.setPosition(0);

    }



    @Override public void runOpMode() throws InterruptedException {
        super.runOpMode();



        setMode(AUTO);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();


        resetEncoder();
        startEncoderMode();

        //place the claw at its start position so the robot satisfies measurement requirements
        robot.foundationClaw.setPosition(1);

        Log.i("thread", "before thread initialization");
        vuforia_thread = new Vuforia_Thread();
        Log.i("thread", "Vuforia_Thread vuforia_thread =new Vuforia_Thread();");
        thread = new Thread(vuforia_thread);
        Log.i("thread", "Thread thread =new Thread(vuforia_thread);");
        thread.start();
        Log.i("thread", "thread.start();");


        waitForStart();
        runtime.reset();
        while(!isStopRequested()) {
            moveFoundation();
            while(robot.runtime.seconds()<27)
            {

            }





            /**
             * this will scan for images in any time and update vuforiaData at robotMovement.class in realtime
             */





         }
        STOP_VUFORIA_THREAD=true;
        requestOpModeStop();


    }
    public void Trashbin()
    {
        while (runtime.seconds() < 27) {


            moveFoundation();

            // TODO: 2019-12-21 adjust with newly added vuforia thread
            //turn right
            turnright(UNKNOWNSPEED);
            //Move forward to stones
            moveForward(UNKNOWNDISTANCE);
            CurrentX += UNKNOWNDISTANCE;// TODO: 2019-12-11 figure out exact position
            //Turn left so camera is facing stones
            turnleft(UNKNOWNSPEED);
            int UNKOWN_DISTANCE_SENSOR_VALUE = 0;
            while (UNKOWN_DISTANCE_SENSOR_VALUE < 3)   //While there are stones
            {
                // Move forward to stones
                moveForward(UNKNOWNDISTANCE);
                CurrentY += UNKNOWNDISTANCE;// TODO: 2019-12-11 figure out exact position

            }

            //First SkyStone
//            while(vuforiaData.getTrackableName().equals("Stone Target") && (vuforiaData.getThirdAngle() == 0))
//            {
//                moveRight(UNKNOWNDISTANCE);
//                // TODO: 2019-12-11 figure out exact position
//            }
//            VuforiaTrackable SkyStone1 = targetsSkyStone.get(13);
//            SkyStone1.setName("SkyStone1");
//            SkyStone1.setLocation(OpenGLMatrix
//                    .translation(CurrentX, CurrentY, stoneZ) // TODO: 2019-12-11 figure out exact position comparing with the other vuforia objects
//                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));//this is good

            //Second SkyStone
            while (vuforiaData.getTrackableName().equals("Stone Target") && vuforiaData.getThirdAngle() == 0) {
                moveRight(UNKNOWNDISTANCE);
                // TODO: 2019-12-11 figure out exact position
            }
            /*
            VuforiaTrackable SkyStone2 = targetsSkyStone.get(13);
            SkyStone2.setName("SkyStone2");
            SkyStone2.setLocation(OpenGLMatrix
                    .translation(CurrentX, CurrentY, stoneZ) // TODO: 2019-12-11 figure out exact position comparing with the other vuforia objects
                    .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));//this is good

             */
            // TODO: 2019-12-11 No need to save 2nd, since we'll grab it right away

            grabStone();
            //go to Foundation, whereever
            releaseStone();
            //go back to first SkyStone

            grabStone();    //first Stone grabbed
            //go to Foundation, whereever
            releaseStone();
            //go back to first SkyStone


        }
        //then, park
        //find placement on field
        //find directions to midfield line
        // move there

        stopRobot();



    }

}
