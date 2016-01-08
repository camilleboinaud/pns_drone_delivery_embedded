package fr.unice.polytech.al.drone.qrcode.captors;

import org.junit.Before;

/**
 * Created by lecpie on 1/8/16.
 */
public class CameraControllerTest {
    private Camera camera;
    private CameraController cameraController;
    private CameraAnalyser cameraAnalyser;

    @Before
    public void setup() {
        camera = new CameraMock();
        cameraAnalyser = new CameraAnalyserRandomMock();

        cameraController = new CameraController(cameraAnalyser, camera);
    }
}
