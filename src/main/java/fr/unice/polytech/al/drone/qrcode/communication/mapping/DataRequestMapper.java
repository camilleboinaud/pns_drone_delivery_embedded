package fr.unice.polytech.al.drone.qrcode.communication.mapping;

import fr.unice.polytech.al.drone.qrcode.model.FlightPlan;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by cboinaud on 28/01/16.
 */
public class DataRequestMapper implements DataRequestMapperAPI {

    /**
     * This method allows to instantiate a JSONObject modelling a flight plan
     * in model layer.
     *
     * @return
     *      flight plan read from storage system
     */
    public FlightPlan saveFlightPlan(JSONObject flightPlan) {
        return null;
    }

    /**
     * This method allows to get request's body used to query server about
     * flight plan.
     *
     * @return
     *      json request
     */
    public JSONObject getFlightPlanRequest() {
        return null;
    }

    /**
     * This method allows to get request's body used to query server about
     * mail authentication.
     *
     * @return
     *      json request
     */
    public JSONObject getMailAuthenticationRequest() {
        return null;
    }

    /**
     * This method allows to get request's body used to query server about
     * delivery acknowledgement.
     *
     * @return
     *      map containing all fields required by multipart request
     */
    public Map<String, Object> getDeliveryAcknowledgementRequest() {
        return null;
    }

}

