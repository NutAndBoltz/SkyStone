package org.firstinspires.ftc.teamcode;

public interface robotVariable {

    int AUTO=1;
    int TELEOP=0;

    double     teleOP_FORWARD_SPEED = 1;
    double     auto_FORWARD_SPEED = 0.4;
    double     STOP_SPEED = 0;
    double     MID_SERVO  =  0.5 ;


    //from Encoder Sample
    double     COUNTS_PER_MOTOR_REV    = 1120 ;
    double     DRIVE_GEAR_REDUCTION    = 1.0 ;
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    double     DRIVE_SPEED             = 0.5;
    double     TURN_SPEED              = 0.5;
    double     Counts_PER_DegreeTurn   = 24;

}
