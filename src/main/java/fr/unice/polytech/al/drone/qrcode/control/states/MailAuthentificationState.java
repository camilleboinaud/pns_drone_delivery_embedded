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
        System.out.println("Waiting for email confirmation, mocked 3 seconds");

        long start = System.currentTimeMillis();

        try {
            while (qrun) {
                // TODO Communication : wait for email confirmation
                // Mock communication time
                if (System.currentTimeMillis() > start + 3000) {
                    EventFactory.createAndPost(EventTypeEnum.EMAIL_CONFIMATION);
                }

                Thread.sleep(100);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        EventBusService.instance().unRegisterSubscriber(this);

        State next = new DeliveryProofState();
        EventBusService.instance().registerSubscriber(next);
        Context.instance().setState(next);
    }

    @Subscribe
    public void receiveEmailValidation(EmailAuthentificationEvent e) {
        qrun = false;
    }



}
