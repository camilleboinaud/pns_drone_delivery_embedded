package fr.unice.polytech.al.drone.qrcode.events.types;


import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by lecpie on 1/27/16.
 */
public class EmailAuthentificationEvent extends AbstractEvent {
    public EmailAuthentificationEvent() {
        super(EventTypeEnum.EMAIL_CONFIMATION);
    }
}
