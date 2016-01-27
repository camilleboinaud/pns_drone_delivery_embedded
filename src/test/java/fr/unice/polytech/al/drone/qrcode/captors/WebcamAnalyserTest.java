package fr.unice.polytech.al.drone.qrcode.captors;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.TestResources;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.types.QRCodeFoundEvent;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by lecpie on 1/15/16.
 */
public class WebcamAnalyserTest {
    private CameraMock camera;
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
    public void testAnalyze() throws IOException {
        camera.on();

        File first = new File(TestResources.YOUKNOWLIFESUCKS);
        File second = new File(TestResources.QRCODESAREAWESOME);

        camera.setReturnImageFromFile(first);
        String res1 = analyser.analyze();

        camera.setReturnImageFromFile(second);
        String res2 = analyser.analyze();

        Assert.assertEquals("#YouKnowLifeSucksTryAgain", res1);
        Assert.assertEquals("#QRCodesAreAwesomes", res2);

        camera.off();
    }
}
