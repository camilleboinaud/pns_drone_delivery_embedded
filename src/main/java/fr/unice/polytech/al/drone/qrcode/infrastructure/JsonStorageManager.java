package fr.unice.polytech.al.drone.qrcode.infrastructure;

import fr.unice.polytech.al.drone.qrcode.model.Coordinate;
import fr.unice.polytech.al.drone.qrcode.model.Customer;
import fr.unice.polytech.al.drone.qrcode.model.FlightPlan;

import fr.unice.polytech.al.drone.qrcode.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.*;
import java.io.*;
import java.nio.file.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by camille on 06/11/15.
 */
public class JsonStorageManager extends StorageManager{

    private final static Logger logger = LogManager.getLogger(JsonStorageManager.class.getName());

    protected JsonStorageManager() {
        FILE_PATH = System.getProperty("user.dir") + "/datasets/flight_plan.json";
    }

    public FlightPlan readFlightPlan() {

        try {
            String json = Files.readAllLines(Paths.get(FILE_PATH)).get(0);

            JSONObject jsonObject= (JSONObject)JSONValue.parse(json);
            JSONObject flightPlanJSON = (JSONObject)jsonObject.get("flightPlan");

            JSONObject customerJSON = (JSONObject)flightPlanJSON.get("customer");
            JSONObject coordinateJSON = (JSONObject)customerJSON.get("coordinates");

            Customer customer = new Customer(
                    (String)customerJSON.get("name"),
                    new Coordinate((Double)coordinateJSON.get("longitude"), (Double)coordinateJSON.get("latitude"))
            );

            JSONObject orderJSON = (JSONObject)flightPlanJSON.get("order");

            Order order = new Order(
                    LocalTime.ofNanoOfDay(TimeUnit.MILLISECONDS.toNanos((Long) orderJSON.get("deliveryTime"))),
                    (String)orderJSON.get("qrCodeValue")
            );

            return new FlightPlan(
                    order,
                    customer,
                    (Long)flightPlanJSON.get("timeout")
            );

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public void writeFlightPlan(FlightPlan flightPlan){
        JSONObject json = new JSONObject();
        json.put("flightPlan", flightPlan);

        try {

            File file = new File(FILE_PATH);

            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(json.toJSONString());

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        } finally {

        }

    }

}
