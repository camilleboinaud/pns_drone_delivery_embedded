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

    public String analyze() {
        double decision = Math.random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return answer randomly, possibly nothing
        return (decision > 0.9) ? mock[new Random().nextInt(mock.length)] : null;
    }
}
