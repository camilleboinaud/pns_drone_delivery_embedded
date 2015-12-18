package fr.unice.polytech.al.drone.qrcode;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.types.AbstractEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.DeliveryFailedEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.DeliverySuccessEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by camille on 11/11/15.
 */
public class FinalListener {

    private static final Logger logger = LogManager.getLogger(FinalListener.class.getName());

    @Subscribe
    public void listenDeliverySuccessEvent(DeliverySuccessEvent event){
        logEvent(event);
    }

    @Subscribe
    public void listenDeliveryFailedEvent(DeliveryFailedEvent event){
        logEvent(event);
    }


    private void logEvent(AbstractEvent event){
        try {
            Thread.sleep(500);
            logger.info(event.getType() + " as been received");
            System.exit(0);
        } catch (InterruptedException e){
            e.printStackTrace();
            System.exit(0);
        }
    }


}
