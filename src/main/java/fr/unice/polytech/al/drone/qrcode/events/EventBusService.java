package fr.unice.polytech.al.drone.qrcode.events;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import fr.unice.polytech.al.drone.qrcode.events.types.AbstractEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;

/**
 * Event bus used to design our application following
 * event driven architecture principles. This API is based
 * on google guava event bus.
 *
 * This class has been designed following Singleton's design
 * pattern.
 *
 * Created by camille on 10/11/15.
 */
public class EventBusService {

    private static final Logger logger = LogManager.getLogger(EventBusService.class.getName());

    private static EventBusService eventBusAPI = new EventBusService();
    private EventBus eventBus = null;

    private EventBusService(){
        eventBus = new AsyncEventBus("DRONE_EVENT_BUS", Executors.newCachedThreadPool());
    }

    /**
     * Static method used to acces a class instance
     * @return
     */
    public static EventBusService instance(){
        return eventBusAPI;
    }

    /**
     * Used to register a new subscriber to the bus
     * @param subscriber
     */
    public void registerSubscriber(Object subscriber) {
        eventBus.register(subscriber);
        logger.info(subscriber.getClass().getSimpleName()
                + " has been registered as a subscriber in event bus");
    }

    /**
     * Used to unregister a subscriber from the bus
     * @param subscriber
     */
    public void unRegisterSubscriber(Object subscriber) {
        eventBus.unregister(subscriber);
        logger.info(subscriber.getClass().getSimpleName()
                + " has been unregistered as a subscriber from event bus");
    }

    /**
     * Used to post an event through the bus
     * @param e
     */
    public void postEvent(Object e) {
        eventBus.post(e);
        logger.info(((AbstractEvent)e).getType().toString() + " has been posted into event bus");
    }

}
