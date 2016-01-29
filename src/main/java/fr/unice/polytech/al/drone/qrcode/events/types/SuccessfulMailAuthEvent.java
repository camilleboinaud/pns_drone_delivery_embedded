package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by lecpie on 1/29/16.
 */
public class SuccessfulMailAuthEvent extends AbstractEvent {
    public SuccessfulMailAuthEvent() {
        super(EventTypeEnum.SUCCESSFUL_MAIL_AUTH);
    }
}
