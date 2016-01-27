package fr.unice.polytech.al.drone.qrcode.control.states;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.types.ProofAckEvent;

/**
 * Created by lecpie on 1/27/16.
 */
public class SendProofState implements State {
    private boolean qrun = true;

    private static final int RETRY = 2000;

    public void action() {

        long lastsend = 0;

        while (qrun) {
            if (System.currentTimeMillis() > lastsend + RETRY) {
                // TODO Send image here
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        EventBusService.instance().unRegisterSubscriber(this);
    }

    @Subscribe
    public void proofAck(ProofAckEvent e) {
        qrun = false;
    }
}
