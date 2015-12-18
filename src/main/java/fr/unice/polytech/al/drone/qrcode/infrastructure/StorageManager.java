package fr.unice.polytech.al.drone.qrcode.infrastructure;

import fr.unice.polytech.al.drone.qrcode.model.FlightPlan;
import fr.unice.polytech.al.drone.qrcode.model.Order;

import java.io.FileNotFoundException;

/**
 * Created by camille on 06/11/15.
 */
public abstract class StorageManager {

    public static String FILE_PATH;


    /**
     * This method allows to serialize and write the flight plan object into storage system.
     */
    public abstract void writeFlightPlan(FlightPlan flightPlan);

    /**
     * This method allows to read flight plan and all its components from storage system.
     * It also stores it into associated objects.
     *
     * @return
     *      flight plan read from storage system
     */
    public abstract FlightPlan readFlightPlan();

}
