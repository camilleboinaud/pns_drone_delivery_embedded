package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by camille on 10/11/15.
 */
public class StartSearchingEvent extends AbstractEvent {

    public StartSearchingEvent(){
        super(EventTypeEnum.START_SEARCHING);
    }
}
