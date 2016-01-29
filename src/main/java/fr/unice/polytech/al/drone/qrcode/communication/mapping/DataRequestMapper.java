package fr.unice.polytech.al.drone.qrcode.communication.mapping;

import fr.unice.polytech.al.drone.qrcode.control.Context;
import fr.unice.polytech.al.drone.qrcode.model.Coordinate;
import fr.unice.polytech.al.drone.qrcode.model.Customer;
import fr.unice.polytech.al.drone.qrcode.model.FlightPlan;
import fr.unice.polytech.al.drone.qrcode.model.Order;
import fr.unice.polytech.al.drone.qrcode.utils.StaticStorageUtils;
import org.json.simple.JSONObject;

import java.io.File;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

        JSONObject flightPlanJSON = (JSONObject)flightPlan.get("flightPlan");
        String droneID = (String)flightPlan.get("droneId") ;
        JSONObject customerJSON = (JSONObject)flightPlanJSON.get("customer");

        JSONObject coordinateJSON = (JSONObject)customerJSON.get("coordinates");

        Customer customer = new Customer(
                (String) customerJSON.get("id"),
                (String) customerJSON.get("name"),
                new Coordinate((Double)coordinateJSON.get("longitude"), (Double)coordinateJSON.get("latitude"))
        );

        JSONObject orderJSON = (JSONObject)flightPlanJSON.get("order");

        Order order = new Order(
                new Date((Long) orderJSON.get("deliveryTime")),
                /*LocalTime.ofNanoOfDay(TimeUnit.MILLISECONDS.toNanos((Long) orderJSON.get("deliveryTime"))),*/
                (String)orderJSON.get("qrCodeValue")
        );

        return new FlightPlan(order, customer, (Long)flightPlanJSON.get("timeout"), droneID);
    }


    /**
     * This method allows to get request's body used to query server about
     * mail authentication.
     *
     * @return
     *      json request
     */
    public JSONObject getMailAuthenticationRequest() {

        JSONObject result = new JSONObject();
        result.put("droneID", StaticStorageUtils.FLIGHT_PLAN.getId());

        return result;
    }

    /**
     * This method allows to get request's body used to query server about
     * delivery acknowledgement.
     *
     * @return
     *      map containing all fields required by multipart request
     */
    public Map<String, Object> getDeliveryAcknowledgementRequest(String imagePath) {

        Map<String, Object> data = new HashMap<String, Object>();
        JSONObject request = new JSONObject();
        request.put("droneID", StaticStorageUtils.FLIGHT_PLAN.getId());

        File image = new File(imagePath);

        data.put("json", request);
        data.put("image", image);

        return data;

    }

}

