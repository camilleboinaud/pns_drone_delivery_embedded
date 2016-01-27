package fr.unice.polytech.al.drone.qrcode.control.states;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.captors.CameraController;
import fr.unice.polytech.al.drone.qrcode.control.Context;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import fr.unice.polytech.al.drone.qrcode.events.types.InterruptionEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.QRCodeFoundEvent;
import fr.unice.polytech.al.drone.qrcode.events.types.SuccessfullAuthenticationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by remy on 06/11/15.
 */
public class SearchingQRCodeState implements State {

    private static final Logger logger = LogManager.getLogger(SearchingQRCodeState.class.getName());
    private static SearchingQRCodeState instance;

    private boolean runner;

    private SearchingQRCodeState() {

    }

//    private SearchingQRCodeState(){
//        EventBusService.instance().registerSubscriber(new CameraController());
//        runner = true;
//    }

    /**
     * Launch QRCode searching and wait for QRCode reading. Once
     * a QRCode value is available, it sends it to authentication
     * state. Researches continue until a QRCode is being found or
     * until research's timeout is being reached.
     */
    public void action() {
        logger.info("launching QRCode research");
        EventFactory.createAndPost(EventTypeEnum.START_SEARCHING);

        try {

            while (runner) {
                Thread.sleep(100);
            }

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static SearchingQRCodeState instance(){

        if (instance == null) {
            instance = new SearchingQRCodeState();
        }

        return instance;
    }

    public void init(){
        runner = true;
    }

    @Subscribe
    public void listenInterruptionEvent(InterruptionEvent event){
        runner = false;

        EventFactory.createAndPost(EventTypeEnum.STOP_SEARCHING);
        EventFactory.createAndPost(EventTypeEnum.DELIVERY_FAILED);

        logger.info(event.getType() + " has been received [" + event.getMessage() + "]");
    }

    @Subscribe
    public void listenQRCodeFoundEvent(QRCodeFoundEvent event){
        logger.info(event.getType() + " with the following value : "
                + event.getQrcode() + " has been received");
        Context.instance().setState(new AuthenticationState(event.getQrcode()));
    }

    @Subscribe
    public void listenSuccessfullAthenticationEvent(SuccessfullAuthenticationEvent event){
        runner = false;
        EventFactory.createAndPost(EventTypeEnum.STOP_SEARCHING);
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }

}
