package fr.unice.polytech.al.drone.qrcode.events;

import fr.unice.polytech.al.drone.qrcode.events.types.*;

/**
 * Created by camille on 10/11/15.
 */
public class EventFactory {

    /**
     * Creates a new event based on event type given as parameter.
     *
     * @param eventTypeEnum
     * @param params : optional arguments required by specific
     *               events constructors.
     * @return
     */
    public static AbstractEvent create(EventTypeEnum eventTypeEnum, Object... params){

        switch (eventTypeEnum){
            case START_AUTHENTICATION:
                return new StartAuthenticationEvent();
            case START_SEARCHING:
                return new StartSearchingEvent();
            case STOP_SEARCHING:
                return new StopSearchingEvent();
            case QR_CODE_FOUND:
                if(params.length == 1 && params[0] instanceof String){
                    return new QRCodeFoundEvent((String)params[0]);
                }
                break;
            case INTERRUPTION:
                if(params.length == 1 && params[0] instanceof String){
                    return new InterruptionEvent((String)params[0]);
                }
                return new InterruptionEvent("");
            case DELIVERY_FAILED:
                return new DeliveryFailedEvent();
            case DELIVERY_SUCCESS:
                return new DeliverySuccessEvent();
            case SUCCESSFULL_AUTHENTICATION:
                return new SuccessfullAuthenticationEvent();
        }

        return null;

    }

    /**
     * Creates a new event based on event type given as parameter
     * and posts it into event bus.
     *
     * @param eventTypeEnum
     * @param params : optional arguments required by specific
     *               events constructors.
     * @return
     */
    public static AbstractEvent createAndPost(EventTypeEnum eventTypeEnum, Object... params){

        AbstractEvent event = create(eventTypeEnum, params);

        if(event != null){
            EventBusService.instance().postEvent(event);
        }

        return event;
    }


}
