package fr.unice.polytech.al.drone.qrcode.captors;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import fr.unice.polytech.al.drone.qrcode.events.types.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by camille on 10/11/15.
 */
public class CameraController {

    private static final Logger logger = LogManager.getLogger(CameraController.class.getName());
    private CameraAnalyser analyser;
    private Camera camera;

    private AnalyserRunnable analyserRunnable;
    private Thread runner;

    public CameraController(CameraAnalyser analyser, Camera camera) {
        this.camera   = camera;
        this.analyser = analyser;
    }

    public boolean saveImage(String path) {
        if (camera.isOn()) {
            BufferedImage image = camera.getImage();

            if (image != null) {
                try {
                    ImageIO.write(image, "PNG", new File(path));

                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    @Subscribe
    public void listenStartSearchingEvent(StartSearchingEvent event) {
        logger.info(event.getType().toString() + " event received : starting QRCode searching");

        analyserRunnable = new AnalyserRunnable(analyser);
        runner = new Thread(analyserRunnable);
        runner.start();

        camera.on();
    }

    @Subscribe
    public void listenStopSearchingEvent(StopSearchingEvent event) {
        logger.info(event.getType().toString() + " event received : stopping QRCode searching");
        analyserRunnable.stop();

        camera.off();
    }



    @Subscribe
    public void saveImage(SaveImageRequestEvent e) throws IOException {
        String path = e.getPath();
        if (!saveImage(e.getPath())) {
            if (!saveImage("proof.png")) {
                saveImage("/tmp/proof.png");
            }
        }

        EventFactory.createAndPost(EventTypeEnum.PROOF_GATHERED);

    }

}


