package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by camille on 10/11/15.
 */
public class AbstractEvent {

    private EventTypeEnum eventType;

    public AbstractEvent(EventTypeEnum eventType){
        this.eventType = eventType;
    }

    public EventTypeEnum getType(){
        return eventType;
    }
}
