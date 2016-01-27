package fr.unice.polytech.al.drone.qrcode.control.states;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.control.Context;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import fr.unice.polytech.al.drone.qrcode.events.types.ProofEvent;

/**
 * Created by lecpie on 1/27/16.
 */
public class DeliveryProofState implements State {

    private boolean qrun = true;

    public void action() {

        EventFactory.createAndPost(EventTypeEnum.SAVE_IMAGE_REQUEST, "img-gen/itheproof.png");
        while (qrun) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

        EventBusService.instance().unRegisterSubscriber(this);

        State next = new SendProofState();
        EventBusService.instance().registerSubscriber(next);
        Context.instance().setState(next);
    }

    @Subscribe
    public void tookPicture(ProofEvent e) {
        qrun = false;
    }
}
