package fr.unice.polytech.al.drone.qrcode.captors;


/**
 * This class wraps a camera analyser in a thread and periodacally run it
 */
public class AnalyserRunnable implements Runnable {
    public static final long ANALYZE_PERIOD = 1000;

    boolean qrun = true;
    private CameraAnalyser cameraAnalyser;

    public AnalyserRunnable(CameraAnalyser cameraAnalyser) {
        this.cameraAnalyser = cameraAnalyser;
    }

    public void run() {
        while (qrun) {
            try {
                Thread.sleep(ANALYZE_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cameraAnalyser.analyze();
        }
    }
}
