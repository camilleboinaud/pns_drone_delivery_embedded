package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by camille on 10/11/15.
 */
public class QRCodeFoundEvent extends AbstractEvent {

    private String qrcode;

    public QRCodeFoundEvent(String qrcode){
        super(EventTypeEnum.QR_CODE_FOUND);

        this.qrcode = qrcode;
    }

    public String getQrcode(){
        return qrcode;
    }

}
