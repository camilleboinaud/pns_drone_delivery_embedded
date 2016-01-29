package fr.unice.polytech.al.drone.qrcode.control.states;

import fr.unice.polytech.al.drone.qrcode.control.Context;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by camille on 11/11/15.
 */
public class AuthenticationState implements State {

    private static final Logger logger = LogManager.getLogger(AuthenticationState.class.getName());

    private String qrcode;

    public AuthenticationState(String qrcode){
        this.qrcode = qrcode;
    }

    /**
     * Used to authenticate users identity. If QRCode value matches
     * then delivery is allowed, else we continue searching.
     */
    public void action() {
        EventBusService.instance().unRegisterSubscriber(SearchingQRCodeState.instance());

        if(qrcode.equals(Context.instance().getFlightPlan().getOrder().getQrCodeValue())){
            EventFactory.createAndPost(EventTypeEnum.SUCCESSFULL_AUTHENTICATION);
            logger.info("User has been successfully authenticated with the received QRCode [value="
                    + qrcode + "]");

            State next = new MailAuthentificationState();
            EventBusService.instance().registerSubscriber(next);

            Context.instance().setState(next);
        } else {
            logger.info("Received QRCode [value=" + qrcode + "] does not match with user's one");
            SearchingQRCodeState.instance().init();
            EventBusService.instance().registerSubscriber(SearchingQRCodeState.instance());
            Context.instance().setState(SearchingQRCodeState.instance());
        }
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }

}
