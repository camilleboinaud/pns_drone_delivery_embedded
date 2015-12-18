package fr.unice.polytech.al.drone.qrcode.events;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.types.AbstractEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.QRCodeFoundEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by camille on 10/11/15.
 */
public class EventBusServiceTest {

    private static final Logger logger = LogManager.getLogger(EventBusServiceTest.class.getName());

    SubscriberTest subscriberTest;

    @Before
    public void setUp(){
        logger.info("Setting up");
        subscriberTest = new SubscriberTest();
        EventBusService.instance().registerSubscriber(subscriberTest);
    }

    @After
    public void tearDown(){
        logger.info("Tearing down");
        EventBusService.instance().unRegisterSubscriber(subscriberTest);
        subscriberTest = null;
    }

    @Test
    public void eventSimpleListenerTest(){
        logger.info("Starting : eventSimpleListenerTest");

        assertFalse(subscriberTest.abstractListener);
        assertNull(subscriberTest.qrcodeListener);

        EventFactory.createAndPost(EventTypeEnum.START_AUTHENTICATION);

        try {
            Thread.sleep(100); // waiting 100ms to ensure event passed through the bus
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(subscriberTest.abstractListener);
        assertNull(subscriberTest.qrcodeListener);

        logger.info("Ending : eventSimpleListenerTest");
    }

    @Test
    public void eventDoubleListenerTest(){
        logger.info("Starting : eventDoubleListenerTest");

        assertFalse(subscriberTest.abstractListener);
        assertNull(subscriberTest.qrcodeListener);

        EventFactory.createAndPost(EventTypeEnum.QR_CODE_FOUND, "123456789");

        try {
            Thread.sleep(100); // waiting 100ms to ensure event passed through the bus
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(subscriberTest.abstractListener);
        assertEquals(subscriberTest.qrcodeListener, "123456789");

        logger.info("Ending : eventDoubleListenerTest");
    }



    class SubscriberTest {

        boolean abstractListener = false;
        String qrcodeListener;

        @Subscribe
        public void abstractEventListener(AbstractEvent event){
            abstractListener = true;
        }

        @Subscribe
        public void qrcodeEventListener(QRCodeFoundEvent event){
            qrcodeListener = event.getQrcode();
        }

    }

}
