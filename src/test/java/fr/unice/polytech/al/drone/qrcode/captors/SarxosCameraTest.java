package fr.unice.polytech.al.drone.qrcode.captors;

import com.github.sarxos.webcam.Webcam;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lecpie on 1/8/16.
 */
public class SarxosCameraTest {

    private Camera camera;

    @Before
    public void setup() {
        // No point trying to use a camera if there is none on the harwdare, ignore tests in this case
        Assume.assumeTrue(Webcam.getWebcams().size() > 0);

        camera = new SarxosCamera();
    }

    @After
    public void clean() {
        if (camera.isOn()) {
            camera.off();
        }
    }

    @Test
    public void testActivateRush() {
        int ntest = 5;

        for (int i = 0; i < ntest; ++i) {
            camera.on();
            Assert.assertTrue("Camera not on after on() call", camera.isOn());

            camera.off();
            Assert.assertFalse("Camera not off after off() call", camera.isOn());
        }
    }

    @Test
    public void testGetImageRush() {
        camera.on();

        int ntest = 10;
        for (int i = 0; i < ntest; ++i) {
            Assert.assertNotNull("Did not get an image from webcam", camera.getImage());
        }

        camera.off();
    }
}
