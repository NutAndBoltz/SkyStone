package org.firstinspires.ftc.teamcode;

import android.transition.CircularPropagation;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

public interface RobotVariable {

    HardwarePushbot         robot   = new HardwarePushbot();   // Use a Pushbot's hardware
     ElapsedTime     runtime = new ElapsedTime();
    double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     CIRCUMFERENCE           = (WHEEL_DIAMETER_INCHES/2)*Math.PI;
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/CIRCUMFERENCE;
    double     INCH_PER_COUNT          = CIRCUMFERENCE/COUNTS_PER_MOTOR_REV;
    double     DRIVE_SPEED             = 0.6;
    double     TURN_SPEED              = 0.5;

    DcMotor motorFL= hardwareMap.get(DcMotor.class, "motor_fl");
    DcMotor motorFR;
    DcMotor motorBL;
    DcMotor motorBR;
    motorFL ;
    motorFR = hardwareMap.get(DcMotor.class, "motor_fr");
    motorBL = hardwareMap.get(DcMotor.class, "motor_bl");
    motorBR = hardwareMap.get(DcMotor.class, "motor_br");
        robot.init(hardwareMap);






}
