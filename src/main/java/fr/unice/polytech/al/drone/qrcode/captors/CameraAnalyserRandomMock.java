package fr.unice.polytech.al.drone.qrcode.captors;

import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

import java.util.Random;


/**
 * Created by camille on 11/11/15.
 */
public class CameraAnalyserRandomMock implements CameraAnalyser {

    private boolean isRunning;
    private String[] mock;

    public CameraAnalyserRandomMock(String... mock) {
        isRunning = true;
        this.mock = mock;
    }

    public void analyze() {
        double decision = Math.random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (decision > 0.9) {
            Thread.currentThread().interrupt();
            EventFactory.createAndPost(
                    EventTypeEnum.QR_CODE_FOUND,
                    mock[new Random().nextInt(mock.length)]
            );
        }
    }
}
