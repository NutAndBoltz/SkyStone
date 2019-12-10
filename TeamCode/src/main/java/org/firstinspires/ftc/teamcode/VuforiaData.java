package org.firstinspires.ftc.teamcode;

public class VuforiaData
{
    String trackableName="";
    double posX=0,posY=0,posZ=0;
    double firstAngle=0,secondAngle=0,thirdAngle=0;
    void VuforiaData(String trackableName, double posX,double posY, double posZ, double firstAngle, double secondAngle, double thirdAngle)
    {
        this.firstAngle=firstAngle;
        this.secondAngle=secondAngle;
        this.thirdAngle=thirdAngle;
        this.posX=posX;
        this.posY=posY;
        this.posZ=posZ;
        this.trackableName=trackableName;
    }

    public String getTrackableName() {
        return trackableName;
    }

    public void setTrackableName(String trackableName) {
        this.trackableName = trackableName;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosZ() {
        return posZ;
    }

    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    public double getFirstAngle() {
        return firstAngle;
    }

    public void setFirstAngle(double firstAngle) {
        this.firstAngle = firstAngle;
    }

    public double getSecondAngle() {
        return secondAngle;
    }

    public void setSecondAngle(double secondAngle) {
        this.secondAngle = secondAngle;
    }

    public double getThirdAngle() {
        return thirdAngle;
    }

    public void setThirdAngle(double thirdAngle) {
        this.thirdAngle = thirdAngle;
    }
}