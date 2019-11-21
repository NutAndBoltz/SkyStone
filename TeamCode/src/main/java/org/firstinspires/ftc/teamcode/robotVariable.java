package org.firstinspires.ftc.teamcode;
public interface robotVariable {


    double     teleOP_FORWARD_SPEED = 1;
    double     auto_FORWARD_SPEED = 0.4;
    double     STOP_SPEED = 0;
    double MID_SERVO       =  0.5 ;


    //from Encoder Sample
    double     COUNTS_PER_MOTOR_REV    = 2240 ;    // eg: TETRIX Motor Encoder
    double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    double     DRIVE_SPEED             = 0.5;
    double     TURN_SPEED              = 0.5;
    double     Counts_PER_DegreeTurn   = 24;

}
