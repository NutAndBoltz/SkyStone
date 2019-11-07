package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

public interface RobotVariable  {

    double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     CIRCUMFERENCE           = (WHEEL_DIAMETER_INCHES/2)*Math.PI;
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)/CIRCUMFERENCE;
    double     INCH_PER_COUNT          = CIRCUMFERENCE/COUNTS_PER_MOTOR_REV;
    double     DRIVE_SPEED             = 0.6;
    double     TURN_SPEED              = 0.5;









}
