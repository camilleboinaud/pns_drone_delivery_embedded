package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by remy on 11/11/15.
 */
public class DeliveryFailedEvent extends AbstractEvent {

    public DeliveryFailedEvent(){
        super(EventTypeEnum.DELIVERY_FAILED);
    }

}
