package fr.unice.polytech.al.drone.qrcode.communication;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.communication.http.HttpRequestUtils;
import fr.unice.polytech.al.drone.qrcode.communication.mapping.DataRequestMapper;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import fr.unice.polytech.al.drone.qrcode.events.types.FlightPlanRequestEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.SaveImageRequestEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.SuccessfullAuthenticationEvent;
import fr.unice.polytech.al.drone.qrcode.utils.StaticStorageUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * Created by cboinaud on 28/01/16.
 */
public class CommunicationHub {

    private DataRequestMapper dataRequestMapper = new DataRequestMapper();

    /**
     * If this event is being handled, then we will query server to get
     * the flight plan.
     * @param event
     */
    @Subscribe
    public void flightPlanQuery(FlightPlanRequestEvent event){
        try {

            StaticStorageUtils.FLIGHT_PLAN = dataRequestMapper.saveFlightPlan(
                    HttpRequestUtils.getRequest(StaticStorageUtils.SERVER_URL + "api/flightplan")
            );
            EventFactory.createAndPost(EventTypeEnum.FLIGHT_PLAN_ACK);

        } catch (RuntimeException e){
            e.printStackTrace();
            EventFactory.createAndPost(EventTypeEnum.INTERRUPTION);
        }

    }

    /**
     * If this event is being handled, then we will query server for
     * authenticate customer using its email.
     * @param event
     */
    @Subscribe
    public void mailAuthenticationQuery(SuccessfullAuthenticationEvent event){
        try {

            JSONObject result = HttpRequestUtils.postRequest(
                    StaticStorageUtils.SERVER_URL + "/apî/mailauth", dataRequestMapper.getMailAuthenticationRequest()
            );

            if ((Boolean) (((JSONObject) result.get("mailauth")).get("result"))) {
                EventFactory.createAndPost(EventTypeEnum.EMAIL_CONFIMATION);
            } else {
                //TODO talk with Pierre
            }

        } catch (RuntimeException e){
            e.printStackTrace();
            EventFactory.createAndPost(EventTypeEnum.INTERRUPTION);
        }

    }

    /**
     * If this event is being handled, then we will send image to server.
     * @param event
     */
    @Subscribe
    public void deliverySuccessAcknowledgementQuery(SaveImageRequestEvent event){
        try {

            Map<String, Object> data = dataRequestMapper.getDeliveryAcknowledgementRequest(event.getPath());
            JSONObject result = HttpRequestUtils.postJsonImageMultipartRequest(
                    StaticStorageUtils.SERVER_URL + "api/deliveryack", (JSONObject) data.get("json"), (File) data.get("image")
            );

            //TODO discuss sucess/fail return format

        } catch (RuntimeException e){
            e.printStackTrace();
            EventFactory.createAndPost(EventTypeEnum.INTERRUPTION);
        }

    }



}
