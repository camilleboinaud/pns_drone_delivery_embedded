package fr.unice.polytech.al.drone.qrcode.captors;

import java.awt.image.BufferedImage;

/**
 * Created by lecpie on 12/18/15.
 */
public interface Camera {

    void on();
    void off();

    boolean isOn();

    BufferedImage getImage();
}
