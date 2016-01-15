package fr.unice.polytech.al.drone.qrcode.captors;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.types.QRCodeFoundEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lecpie on 1/15/16.
 */
public class WebcamAnalyserTest {
    private Camera camera;
    private CameraQRCodeAnalyser analyser;

    private Object listener;

    @Before
    public void setup() {
        camera = new CameraMock();
        analyser = new CameraQRCodeAnalyser(camera);

        
    }

    @After
    public void cleanup() {

    }

    @Test
    public void testAnalyze() {

        boolean failTime = true;

        listener = new Object() {
            @Subscribe
            public void listenQRCode(QRCodeFoundEvent e) {

            }
        };

        EventBusService.instance().registerSubscriber(listener);

        camera.on();
        analyser.analyze();
        camera.off();

    }
}
