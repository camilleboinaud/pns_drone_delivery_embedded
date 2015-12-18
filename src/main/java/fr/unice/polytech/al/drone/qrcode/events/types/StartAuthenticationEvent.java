package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by camille on 11/11/15.
 */
public class StartAuthenticationEvent extends AbstractEvent {

    public StartAuthenticationEvent(){
        super(EventTypeEnum.START_AUTHENTICATION);
    }

}
