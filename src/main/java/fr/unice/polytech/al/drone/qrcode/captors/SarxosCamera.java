package fr.unice.polytech.al.drone.qrcode.captors;

import com.github.sarxos.webcam.Webcam;

import java.awt.image.BufferedImage;

/**
 * Created by lecpie on 12/18/15.
 */
public class SarxosCamera implements Camera
{
    private Webcam camera;

    public SarxosCamera() {
        this.camera = Webcam.getDefault();
    }

    public SarxosCamera(int icamera) {
        this.camera = Webcam.getWebcams().get(icamera);
    }

    public void on() {
        camera.open();
    }

    public void off() {
        camera.close();
    }

    public boolean isOn() {
        return camera.isOpen();
    }

    public BufferedImage getImage() {
        return camera.getImage();
    }
}
