package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by camille on 10/11/15.
 */
public class StopSearchingEvent extends AbstractEvent {

    public StopSearchingEvent(){
        super(EventTypeEnum.STOP_SEARCHING);
    }

}
