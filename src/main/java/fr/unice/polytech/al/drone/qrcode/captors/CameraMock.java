package fr.unice.polytech.al.drone.qrcode.captors;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    // Manually set the return image for this mock
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setReturnImage(BufferedImage image) {
        this.image = image;
    }

    public void setReturnImageFromFile(File imageFile) throws IOException {
        setReturnImage(ImageIO.read(imageFile));
    }
}
