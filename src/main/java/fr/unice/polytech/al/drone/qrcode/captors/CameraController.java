package fr.unice.polytech.al.drone.qrcode.captors;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.types.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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
        runner.interrupt();

        camera.off();
    }

}


