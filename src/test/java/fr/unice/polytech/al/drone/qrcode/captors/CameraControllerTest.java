package fr.unice.polytech.al.drone.qrcode.captors;

import fr.unice.polytech.al.drone.qrcode.TestResources;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by lecpie on 1/8/16.
 */
public class CameraControllerTest {
    private CameraMock camera;
    private CameraController cameraController;
    private CameraAnalyser cameraAnalyser;

    private static final String TESTOUTPUT = TestResources.GENIMAGE;


    @Before
    public void setup() {
        camera = new CameraMock();
        cameraAnalyser = new CameraAnalyserRandomMock();

        cameraController = new CameraController(cameraAnalyser, camera);

        File generatedFile = new File(TESTOUTPUT);

        if (generatedFile.exists()) {
            generatedFile.delete();
        }
    }

    @After
    public void cleanup() {
        if (camera.isOn()) {
            camera.off();
        }
    }

    @Test
    public void testCapture() throws IOException {
        camera.setReturnImageFromFile(new File(TestResources.YOUKNOWLIFESUCKS));

        camera.on();

        cameraController.saveImage(TESTOUTPUT);

        File generatedFile = new File(TESTOUTPUT);
        Assert.assertTrue("File save test did not create a new file", generatedFile.exists());
        long firstGen = generatedFile.lastModified();

        cameraController.saveImage(TESTOUTPUT);
        Assert.assertFalse("file save did not override existing file", firstGen != generatedFile.lastModified());

        camera.off();
    }
}
