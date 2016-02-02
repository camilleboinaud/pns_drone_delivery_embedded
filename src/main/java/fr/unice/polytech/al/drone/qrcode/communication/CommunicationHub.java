package fr.unice.polytech.al.drone.qrcode.communication;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.communication.http.HttpRequestUtils;
import fr.unice.polytech.al.drone.qrcode.communication.mapping.DataRequestMapper;
import fr.unice.polytech.al.drone.qrcode.control.Context;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import fr.unice.polytech.al.drone.qrcode.events.types.*;
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
                    HttpRequestUtils.getRequest(StaticStorageUtils.SERVER_URL + "flightPlan/assign")
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
    public void mailAuthenticationQuery(RequestMailAuthEvent event){
        try {
            JSONObject result = HttpRequestUtils.postRequest(
                    StaticStorageUtils.SERVER_URL + "mail/authentication?userId=" + Context.instance().getFlightPlan().getCustomer().getId()
                            + "&transaction=" + Context.instance().getFlightPlan().getTransaction(), dataRequestMapper.getMailAuthenticationRequest()
            );

        } catch (RuntimeException e){
            e.printStackTrace();
            EventFactory.createAndPost(EventTypeEnum.INTERRUPTION);
        }

    }

    @Subscribe
    public void mailAuthenticationStatusQuery(MailStatusRequestEvent e) {
        JSONObject result = HttpRequestUtils.getRequest(StaticStorageUtils.SERVER_URL + "flightPlan/checkAcceptation?transaction=" + Context.instance().getFlightPlan().getTransaction());
        boolean status = (Boolean) ((JSONObject) result.get("mailauth")).get("result");

        if (status) {
            EventFactory.createAndPost(EventTypeEnum.EMAIL_CONFIMATION);
        }
    }

    /**
     * If this event is being handled, then we will send image to server.
     * @param event
     */
    @Subscribe
    public void deliverySuccessAcknowledgementQuery(ProofSendRequestEvent event){
        try {

            Map<String, Object> data = dataRequestMapper.getDeliveryAcknowledgementRequest("img-gen/itheproof.png");
            JSONObject result = HttpRequestUtils.postJsonImageMultipartRequest(
                    StaticStorageUtils.SERVER_URL + "file/upload", (JSONObject) data.get("json"), (File) data.get("image")
            );

            EventFactory.createAndPost(EventTypeEnum.PROOF_ACK);

        } catch (RuntimeException e){
            e.printStackTrace();
            EventFactory.createAndPost(EventTypeEnum.INTERRUPTION);
        }

    }



}
