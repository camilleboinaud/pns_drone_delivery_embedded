package fr.unice.polytech.al.drone.qrcode.control.states;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.control.Context;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import fr.unice.polytech.al.drone.qrcode.events.types.EmailAuthentificationEvent;

/**
 * Created by lecpie on 1/27/16.
 */
public class MailAuthentificationState implements State {

    private boolean qrun = true;

    public void action() {
        EventFactory.createAndPost(EventTypeEnum.REQUEST_MAIL_AUTH);
            while (qrun) {
                try {
                    EventFactory.createAndPost(EventTypeEnum.MAIL_STATUS_REQUEST);
                    Thread.sleep(5000);

                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        EventBusService.instance().unRegisterSubscriber(this);

        State next = new DeliverState();
        EventBusService.instance().registerSubscriber(next);
        Context.instance().setState(next);
    }

    @Subscribe
    public void receiveEmailValidation(EmailAuthentificationEvent e) {
        qrun = false;
    }



}
