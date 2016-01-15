package fr.unice.polytech.al.drone.qrcode;

import fr.unice.polytech.al.drone.qrcode.captors.*;
import fr.unice.polytech.al.drone.qrcode.control.InitAuthentication;
import fr.unice.polytech.al.drone.qrcode.events.EventBusService;
import fr.unice.polytech.al.drone.qrcode.events.EventFactory;
import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by camille on 10/11/15.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main (String[] args) throws Exception{

        Camera camera = new SarxosCamera();
        CameraAnalyser cameraAnalyser = new CameraQRCodeAnalyser(camera);
        CameraController cameraController = new CameraController(cameraAnalyser, camera);

        EventBusService.instance().registerSubscriber(new InitAuthentication());
        EventBusService.instance().registerSubscriber(cameraController);

        logger.info("Sending a START_AUTHENTICATION event");
        EventFactory.createAndPost(EventTypeEnum.START_AUTHENTICATION);

        EventBusService.instance().registerSubscriber(new FinalListener());
    }

}
