package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by lecpie on 1/29/16.
 */
public class MailStatusRequestEvent extends AbstractEvent {
    public MailStatusRequestEvent() {
        super(EventTypeEnum.MAIL_STATUS_REQUEST);
    }
}
