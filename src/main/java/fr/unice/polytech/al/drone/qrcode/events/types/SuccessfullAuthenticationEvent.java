package fr.unice.polytech.al.drone.qrcode.events.types;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by camille on 12/11/15.
 */
public class SuccessfullAuthenticationEvent extends AbstractEvent {

    public SuccessfullAuthenticationEvent(){
        super(EventTypeEnum.SUCCESSFULL_AUTHENTICATION);
    }

}
