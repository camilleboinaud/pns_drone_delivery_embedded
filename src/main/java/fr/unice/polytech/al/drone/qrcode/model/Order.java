package fr.unice.polytech.al.drone.qrcode.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by camille on 06/11/15.
 */
public class Order {

    protected Date deliveryTime;
    protected String qrCodeValue;

    public Order(){
        deliveryTime = new Date();
        //deliveryTime.plus(30, ChronoUnit.MINUTES);

        qrCodeValue = "#QRCodesAreAwesomes";
    }

    public Order(Date deliveryTime, String qrCodeValue) {
        this.deliveryTime = deliveryTime;
        this.qrCodeValue = qrCodeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getDeliveryDate() != null ? !getDeliveryDate().equals(order.getDeliveryDate()) : order.getDeliveryDate() != null)
            return false;
        return !(getQrCodeValue() != null ? !getQrCodeValue().equals(order.getQrCodeValue()) : order.getQrCodeValue() != null);

    }

    @Override
    public int hashCode() {
        int result = getDeliveryDate() != null ? getDeliveryDate().hashCode() : 0;
        result = 31 * result + (getQrCodeValue() != null ? getQrCodeValue().hashCode() : 0);
        return result;
    }

    public Date getDeliveryDate() {
        return deliveryTime;
    }

    public void setDeliveryDate(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getQrCodeValue() {
        return qrCodeValue;
    }

    public void setQrCodeValue(String qrCodeValue) {
        this.qrCodeValue = qrCodeValue;
    }

    @Override
    public String toString() {
        JSONObject o = new JSONObject();

        o.put("deliveryTime", deliveryTime.getTime());
        o.put("qrCodeValue", qrCodeValue);

        return o.toJSONString();
    }
}
