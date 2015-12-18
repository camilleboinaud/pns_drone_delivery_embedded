package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by remy on 11/11/15.
 */
public class InterruptionEvent extends AbstractEvent {

    private String message;

    public InterruptionEvent(String message){
        super(EventTypeEnum.INTERRUPTION);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
