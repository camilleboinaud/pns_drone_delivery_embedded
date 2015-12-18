package fr.unice.polytech.al.drone.qrcode.control;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.control.states.AuthenticationState;
import fr.unice.polytech.al.drone.qrcode.control.states.SearchingQRCodeState;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import fr.unice.polytech.al.drone.qrcode.events.types.StartAuthenticationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by camille on 12/11/15.
 */
public class InitAuthentication {

    private static final Logger logger = LogManager.getLogger(InitAuthentication.class.getName());
    private Timer timer;

    public InitAuthentication(){
        timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        EventFactory.createAndPost(EventTypeEnum.INTERRUPTION, "timeout expired");
                    }
                },
                Context.instance().getFlightPlan().getTimeout()
        );
    }

    /**
     * Method used to init the first state machine's action and
     * launch research timer.
     */
    @Subscribe
    public void listenStartAuthenticationEvent(StartAuthenticationEvent event){
        logger.info(event.getType() + " has been received");
        Context.instance().init();
    }

}
