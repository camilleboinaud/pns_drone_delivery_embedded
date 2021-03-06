package fr.unice.polytech.al.drone.qrcode.control.states;

import fr.unice.polytech.al.drone.qrcode.control.Context;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by remy on 06/11/15.
 */
public class DeliverState implements State {

    private static final Logger logger = LogManager.getLogger(DeliverState.class.getName());

    /**
     * This method simulate package delivering and notify system
     * of delivery success.
     */
    public void action() {
        logger.info("Package is being delivered");
        EventFactory.createAndPost(EventTypeEnum.DELIVERY_SUCCESS);

        State next = new DeliveryProofState();
        EventBusService.instance().unRegisterSubscriber(this);
        EventBusService.instance().registerSubscriber(next);
        Context.instance().setState(next);
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}


