package fr.unice.polytech.al.drone.qrcode.utils;

import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class used to post an event into bus (with its optionnal parameters)
 * after a delay.
 *
 * Created by camille on 11/11/15.
 */
public class EventTimer {


    private long delay;
    private static EventTypeEnum eventTypeEnum;
    private static Object[] eventParameters;

    private Timer timer;

    public EventTimer(long delay, EventTypeEnum eventTypeEnum, Object... eventParameters){
        this.delay = delay;
        this.eventTypeEnum = eventTypeEnum;
        this.eventParameters = eventParameters;

        timer = new Timer();
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    EventFactory.createAndPost(EventTimer.eventTypeEnum, EventTimer.eventParameters);
                }
            }, delay
        );

    }

}
