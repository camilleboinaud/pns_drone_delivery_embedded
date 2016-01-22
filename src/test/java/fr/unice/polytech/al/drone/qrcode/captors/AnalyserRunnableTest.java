package fr.unice.polytech.al.drone.qrcode.captors;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.types.QRCodeFoundEvent;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lecpie on 1/22/16.
 */
public class AnalyserRunnableTest {

    private AnalyserRunnable runnable;
    private CameraAnalyserMock analyser;

    @Before
    public void setup() {
        this.analyser = new CameraAnalyserMock();
        this.runnable = new AnalyserRunnable(analyser);
    }

    @Test
    public void testFound() throws InterruptedException {

        final String first = new String();
        String second = new String();

        class Checker {

            public List<String> responses = new ArrayList<String>();

            @Subscribe
            public void receive(QRCodeFoundEvent e) {
                responses.add(e.getQrcode());
            }
        }

        Checker listener = new Checker();

        EventBusService.instance().registerSubscriber(listener);

        Thread t = new Thread(runnable);

        t.start();

        try {
            Thread.sleep(2000);
            analyser.setReturnValue("Hello");

            t.join(5000);
        } catch (Exception e) {

        }

        Assert.assertTrue(listener.responses.size() > 0);

        boolean oneisnull = false;

        for (String response : listener.responses) {
            if (response == null) {
                oneisnull = true;
            }
        }

        Assert.assertFalse(oneisnull);

        EventBusService.instance().unRegisterSubscriber(listener);
    }
}
