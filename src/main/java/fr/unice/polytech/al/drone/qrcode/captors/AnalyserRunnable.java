package fr.unice.polytech.al.drone.qrcode.captors;

import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * This class wraps a camera analyser in a thread and periodically run it
 * This class is responsible for posting the QR code found event when the analyser gets a result
 */
public class AnalyserRunnable implements Runnable {
    public static final long ANALYZE_PERIOD = 1000;

    boolean qrun = false;
    boolean shouldRun = false;
    private CameraAnalyser cameraAnalyser;

    public AnalyserRunnable(CameraAnalyser cameraAnalyser) {
        this.cameraAnalyser = cameraAnalyser;
    }

    public void run() {
        shouldRun = true;
        qrun = true;

        while (shouldRun) {
            try {
                Thread.sleep(ANALYZE_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String answer = cameraAnalyser.analyze();

            if (answer != null) {
                EventFactory.createAndPost(EventTypeEnum.QR_CODE_FOUND, answer);
            }
        }
        qrun = false;
    }

    public boolean isRunning() {
        return qrun;
    }

    public boolean stopping() {
        return shouldRun;
    }

    public void stop() {
        this.shouldRun = false;
    }
}
