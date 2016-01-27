package fr.unice.polytech.al.drone.qrcode.events;

/**
 * Created by camille on 10/11/15.
 */
public enum EventTypeEnum {

    START_AUTHENTICATION,
    SUCCESSFULL_AUTHENTICATION,
    START_SEARCHING,
    STOP_SEARCHING,
    QR_CODE_FOUND,
    INTERRUPTION,
    DELIVERY_FAILED,
    DELIVERY_SUCCESS,
    PROOF_GATHERED,
    EMAIL_CONFIMATION,
    PROOF_ACK;

}
