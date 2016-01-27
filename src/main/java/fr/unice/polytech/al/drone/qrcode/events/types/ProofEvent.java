package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by lecpie on 1/27/16.
 */
public class ProofEvent extends AbstractEvent {

    private String path;

    public ProofEvent(String path) {
        super(EventTypeEnum.PROOF_GATHERED);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
