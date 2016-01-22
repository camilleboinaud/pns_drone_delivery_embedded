package fr.unice.polytech.al.drone.qrcode.captors;

/**
 * Created by lecpie on 1/8/16.
 */
public class CameraAnalyserMock implements CameraAnalyser {

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    private String returnValue;

    public String analyze() {
        return returnValue;
    }
}
