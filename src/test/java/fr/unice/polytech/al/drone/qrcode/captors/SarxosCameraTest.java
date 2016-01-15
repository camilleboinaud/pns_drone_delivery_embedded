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

    /**
     * This setup should check that there is an available camera to run the tests
     * Since these tests need a physical camera to be available on the hardware
     * but the integration server does not have one and the tests are expected to fail when this is the case
     *
     * In short, these tests only run when you have a camera.
     */
    @Before
    public void setup() {
        // No point trying to use a camera if there is none on the harwdare, ignore tests in this case
        boolean qcamera;
        try {
            Assume.assumeTrue(Webcam.getWebcams().size() > 0);
            qcamera = true;
        }
        catch (Exception e) {
            qcamera = false;
        }

        Assume.assumeTrue(qcamera);

        camera = new SarxosCamera();
    }

    @After
    public void clean() {
        if (camera != null && camera.isOn()) {
            camera.off();
        }

        camera = null;
    }

    /**
     * Test activating and deactivating the camera at high rate
     */
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

    /**
     * Test high camera capture rate
     */
    @Test
    public void testGetImageRush() {
        camera.on();

        int ntest = 1000;
        for (int i = 0; i < ntest; ++i) {
            Assert.assertNotNull("Did not get an image from webcam", camera.getImage());
        }

        camera.off();
    }

    /**
     * Test both activating and deactivating the camera at high rate and get a burst of images
     */
    @Test
    public void testgetImageStartupRush() {
        int nboot = 5;
        int ncapture = 10;

        for (int i = 0; i < nboot; ++i) {
            camera.on();
            for (int j = 0; j < ncapture; ++j) {
                Assert.assertNotNull("Did not get an image from webcam", camera.getImage());
            }
            camera.off();
        }
    }
}
