package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by camille on 11/11/15.
 */
public class DeliverySuccessEvent extends AbstractEvent {

    public DeliverySuccessEvent(){
        super(EventTypeEnum.DELIVERY_SUCCESS);
    }

}
