package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

public interface robotVariable {

    int AUTO = 1;
    int TELEOP = 0;

    double     MID_SERVO  =  0.5 ;


    //from Encoder Sample
    double     COUNTS_PER_MOTOR_REV    = 1120 ;
    double     DRIVE_GEAR_REDUCTION    = 1.0 ;
    double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /(WHEEL_DIAMETER_INCHES * Math.PI);
    double     DRIVE_SPEED             = 0.5;
    double     teleOP_FORWARD_SPEED    = 1;

    
    //Vuforia Variables
    // IMPORTANT:  For Phone Camera, set 1) the camera source and 2) the orientation, based on how your phone is mounted:
    // 1) Camera Source.  Valid choices are:  BACK (behind screen) or FRONT (selfie side)
    // 2) Phone Orientation. Choices are: PHONE_IS_PORTRAIT = true (portrait) or PHONE_IS_PORTRAIT = false (landscape)
    //
    // NOTE: If you are running on a CONTROL HUB, with only one USB WebCam, you must select CAMERA_CHOICE = BACK; and PHONE_IS_PORTRAIT = false;
    //
     VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
     boolean PHONE_IS_PORTRAIT = false  ;

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
     String VUFORIA_KEY =
            "AV2BXJD/////AAABmcpiiweem0wwrt9f0UbVq5xY7zw2pWY0l6FVDthBpGXx5pMt8hZ92jzwz0U7GXRlmRVN3yLTGXevMsX/8dzp8+iS413YFnRhrCpd2W90/NvhX4UXk9CTqjb8SmH9BnAp72GrFBkaUMI/GHsS77eFTPxMw5gW5Hen01HqkszGE/mHg0MDonBUCKx4T3V5opfD/aCJesDjePoWVO/O8D4bAqGKo7IIx+IgSo9zHWWciQiACobgpzHoXog+wLIr+NTJgGPQKL5CVzKKcPnBKqTUpj1Uo9xxkL70anbmo6sMQOKuSvHV7hY093Gp8Zbh9pulihTmLYlJQ6HZgyfLPzhDVZeXp2pLjx1BSSRkot2auSC5";

    // Since ImageTarget trackables use mm to specifiy their dimensions, we must use mm for all the physical dimension.
    // We will define some constants and conversions here
     float mmPerInch        = 25.4f;
     float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    // Constant for Stone Target
     float stoneZ = 2.00f * mmPerInch;

    // Constants for the center support targets
    //XYZ all 0 ==> Center of Field
     float bridgeZ = 6.42f * mmPerInch;
     float bridgeY = 23 * mmPerInch;
     float bridgeX = 5.18f * mmPerInch;
     float bridgeRotY = 59;                                 // Units are degrees
     float bridgeRotZ = 180;

    // Constants for perimeter targets
     float halfField = 72 * mmPerInch;
     float quadField  = 36 * mmPerInch;



    // Next, translate the camera lens to where it is on the robot.
    // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
     float CAMERA_FORWARD_DISPLACEMENT  = 4.0f * mmPerInch;   // eg: Camera is 4 Inches in front of robot center
     float CAMERA_VERTICAL_DISPLACEMENT = 8.0f * mmPerInch;   // eg: Camera is 8 Inches above ground
     float CAMERA_LEFT_DISPLACEMENT     = 0;     // eg: Camera is ON the robot's center line
    

    

}
