package fr.unice.polytech.al.drone.qrcode.communication;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.types.FlightPlanRequestEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.SaveImageRequestEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.SuccessfullAuthenticationEvent;

/**
 * Created by cboinaud on 28/01/16.
 */
public class CommunicationHub {

    /**
     * If this event is being handled, then we will query server to get
     * the flight plan.
     * @param event
     */
    @Subscribe
    public void flightPlanQuery(FlightPlanRequestEvent event){

    }

    /**
     * If this event is being handled, then we will query server for
     * authenticate customer using its email.
     * @param event
     */
    @Subscribe
    public void mailAuthenticationQuery(SuccessfullAuthenticationEvent event){

    }

    /**
     * If this event is being handled, then we will send image to server.
     * @param event
     */
    @Subscribe
    public void deliverySuccessAcknowledgementQuery(SaveImageRequestEvent event){

    }



}
