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
public class WebcamCameraAnalyser implements CameraAnalyser {

    private static final Logger logger = LogManager.getLogger(WebcamCameraAnalyser.class.getName());

    private static final int DELAY_BETWEEN_FRAMES = 200;
    private static final int CAMERA_NUMBER = 0;

    private Webcam webcam;


    public WebcamCameraAnalyser(){
        webcam = Webcam.getWebcams().get(CAMERA_NUMBER);
        webcam.setViewSize(WebcamResolution.QVGA.getSize());
    }


    public void run() {
        webcam.open();

        while (true) {
            try{

                Result result = null;
                BufferedImage image;

                if (webcam.isOpen()) {

                    if ((image = webcam.getImage()) == null) { continue; }
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    try {
                        result = new MultiFormatReader().decode(bitmap);
                    } catch (NotFoundException e) {}

                }

                if (result != null) {
                    webcam.close();
                    Thread.currentThread().interrupt();

                    EventFactory.createAndPost(EventTypeEnum.QR_CODE_FOUND, result.toString());
                    break;
                }

                Thread.sleep(DELAY_BETWEEN_FRAMES);
            } catch (InterruptedException e) {
                webcam.close();
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
