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

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("out rcv");


        EventBusService.instance().unRegisterSubscriber(this);

        State next = SearchingQRCodeState.instance();
        System.out.println("subscribe");

        EventBusService.instance().registerSubscriber(next);
        System.out.println("end rcv");
        Context.instance().setState(next);
    }

    @Subscribe
    public void receiveAck(FlightPlanAckEvent e) {
        System.out.println("the ack");

        Context.instance().reload();
        Context.instance().runTimer();
        qrun = false;
        System.out.println("I should kill the while");

    }
}
