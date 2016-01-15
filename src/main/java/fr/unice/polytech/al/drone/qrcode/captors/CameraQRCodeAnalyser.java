package fr.unice.polytech.al.drone.qrcode.captors;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.BufferedImage;

/**
 * Created by camille on 10/11/15.
 */
public class CameraQRCodeAnalyser implements CameraAnalyser {

    private static final int DELAY_BETWEEN_FRAMES = 200;
    private static final int CAMERA_NUMBER = 0;

    private Camera camera;

    public CameraQRCodeAnalyser(Camera camera) {
        this.camera = camera;
    }

    public String analyze() {
        Result result = null;
        BufferedImage image;

        // Anayze the camera only if it is already turned on
        if (camera.isOn()) {
            // Try to get an image from the camera
            if ((image = camera.getImage()) == null) { return null; }

            // Black magic try to read the image as QR code
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {}
        }

        // Return nothing if there is no answer
        if (result == null) {
            return null;
        }

        // Return the answer as a string
        return result.toString();
    }
}
