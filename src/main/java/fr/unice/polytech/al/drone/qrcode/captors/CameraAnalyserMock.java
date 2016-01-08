package fr.unice.polytech.al.drone.qrcode.captors;

import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

import java.util.Random;


/**
 * Created by camille on 11/11/15.
 */
public class CameraAnalyserMock implements CameraAnalyser {

    private boolean isRunning;
    private String[] mock;

    public CameraAnalyserMock(String... mock) {
        isRunning = true;
        this.mock = mock;
    }

    public void run() {

        double decision;

        while (isRunning) {
            try {
                decision = Math.random();
                Thread.sleep(1000);

                if (decision > 0.9) {
                    Thread.currentThread().interrupt();
                    EventFactory.createAndPost(
                            EventTypeEnum.QR_CODE_FOUND,
                            mock[new Random().nextInt(mock.length)]
                    );
                    break;
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public Camera getCamera() {
        return null;
    }

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

}
