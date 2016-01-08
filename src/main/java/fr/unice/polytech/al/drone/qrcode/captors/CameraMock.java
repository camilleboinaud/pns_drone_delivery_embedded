package fr.unice.polytech.al.drone.qrcode.captors;

import java.awt.image.BufferedImage;

/**
 * Created by lecpie on 1/8/16.
 */
public class CameraMock implements Camera {

    boolean qOn = false;

    public void on() {
        qOn = true;
    }

    public void off() {
        qOn = false;
    }

    public boolean isOn() {
        return qOn;
    }

    public BufferedImage getImage() {
        return null;
    }
}
