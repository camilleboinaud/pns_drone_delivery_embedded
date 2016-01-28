package fr.unice.polytech.al.drone.qrcode.control;

import fr.unice.polytech.al.drone.qrcode.control.states.ReceiveMissionState;
import fr.unice.polytech.al.drone.qrcode.control.states.State;
import fr.unice.polytech.al.drone.qrcode.events.*;
import fr.unice.polytech.al.drone.qrcode.model.FlightPlan;
import fr.unice.polytech.al.drone.qrcode.utils.StaticStorageUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by remy on 06/11/15.
 */
public class Context {

    private static final Logger logger = LogManager.getLogger(Context.class.getName());

    private static Context instance = new Context();
    private static long timeout = 30000;

    private FlightPlan flightPlan;
    private State current;

    private Context(){
        reload();
    }

    public void reload() {
        flightPlan = StaticStorageUtils.FLIGHT_PLAN;
    }

    public static Context instance(){
        return instance;
    }

    public State getCurrent(){
        return current;
    }

    public void init(){

        State next = new ReceiveMissionState();
        EventBusService.instance().registerSubscriber(next);

        setState(next);
    }

    public void setState(State state){
        logger.info("state changed from '" + current + "' to '" + state + "'");
        current = state;
        current.action();
    }

    public FlightPlan getFlightPlan() {
        return flightPlan;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "[" + current + "]";
    }

}
