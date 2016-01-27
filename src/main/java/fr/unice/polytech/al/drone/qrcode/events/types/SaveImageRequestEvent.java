package fr.unice.polytech.al.drone.qrcode.events.types;

import fr.unice.polytech.al.drone.qrcode.events.EventTypeEnum;

/**
 * Created by lecpie on 1/27/16.
 */
public class SaveImageRequestEvent extends AbstractEvent {
    private String path;

    public SaveImageRequestEvent(String path) {
        super(EventTypeEnum.SAVE_IMAGE_REQUEST);

        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
