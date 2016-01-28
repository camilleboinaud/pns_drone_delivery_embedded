package fr.unice.polytech.al.drone.qrcode.communication.mapping;

import fr.unice.polytech.al.drone.qrcode.model.FlightPlan;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by camille on 06/11/15.
 */
public interface DataRequestMapperAPI {

    /**
     * This method allows to instantiate a JSONObject modelling a flight plan
     * in model layer.
     *
     * @return
     *      flight plan read from storage system
     */
    FlightPlan saveFlightPlan(JSONObject flightPlan);


    /**
     * This method allows to get request's body used to query server about
     * flight plan.
     *
     * @return
     *      json request
     */
    JSONObject getFlightPlanRequest();

    /**
     * This method allows to get request's body used to query server about
     * mail authentication.
     *
     * @return
     *      json request
     */
    JSONObject getMailAuthenticationRequest();

    /**
     * This method allows to get request's body used to query server about
     * delivery acknowledgement.
     *
     * @return
     *      map containing all fields required by multipart request
     */
    Map<String, Object> getDeliveryAcknowledgementRequest();

}
