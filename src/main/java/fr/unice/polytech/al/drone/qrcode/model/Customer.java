package fr.unice.polytech.al.drone.qrcode.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 * Created by camille on 06/11/15.
 */
public class Customer {
    protected String name;
    protected Coordinate coordinates;

    public Customer(){
        name = "mockedName";
        coordinates = new Coordinate(48.8534100, 2.3488000);
    }

    public Customer(String name, Coordinate coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (getName() != null ? !getName().equals(customer.getName()) : customer.getName() != null) return false;
        return !(getCoordinates() != null ? !getCoordinates().equals(customer.getCoordinates()) : customer.getCoordinates() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCoordinates() != null ? getCoordinates().hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        JSONObject o = new JSONObject();

        o.put("name", name);
        o.put("coordinates", coordinates);

        return o.toJSONString();
    }
}
