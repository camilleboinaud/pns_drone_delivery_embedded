package fr.unice.polytech.al.drone.qrcode.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * Created by camille on 06/11/15.
 */
public class Coordinate {

    protected double longitude;
    protected double latitude;

    public Coordinate(double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate that = (Coordinate) o;

        if (Double.compare(that.getLongitude(), getLongitude()) != 0) return false;
        return Double.compare(that.getLatitude(), getLatitude()) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getLongitude());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        JSONObject o = new JSONObject();

        o.put("longitude", longitude);
        o.put("latitude", latitude);

        return o.toJSONString();
    }


}
