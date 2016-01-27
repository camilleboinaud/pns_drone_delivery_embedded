package fr.unice.polytech.al.drone.qrcode.control.states;

import com.google.common.eventbus.Subscribe;
import fr.unice.polytech.al.drone.qrcode.control.Context;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import fr.unice.polytech.al.drone.qrcode.events.types.FlightPlanAckEvent;

/**
 * Created by lecpie on 1/27/16.
 */
public class ReceiveMissionState implements State {

    boolean qrun = true;

    public void action() {

        // Send request to communicator
        EventFactory.createAndPost(EventTypeEnum.FLIGHT_PLAN_REQUEST);

        while (qrun) {

            //TODO Use a communicator instead

            try {
                Thread.sleep(3000);
                EventFactory.createAndPost(EventTypeEnum.FLIGHT_PLAN_ACK);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        EventBusService.instance().unRegisterSubscriber(this);

        State next = SearchingQRCodeState.instance();
        EventBusService.instance().registerSubscriber(next);
        Context.instance().setState(next);
    }

    @Subscribe
    public void receiveAck(FlightPlanAckEvent e) {
        Context.instance().reload();
        qrun = false;
    }
}
