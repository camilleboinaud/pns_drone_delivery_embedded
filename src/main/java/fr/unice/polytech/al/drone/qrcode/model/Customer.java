package fr.unice.polytech.al.drone.qrcode.model;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 * Created by camille on 06/11/15.
 */
public class Customer {
    protected String id;

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected String name;
    protected Coordinate coordinates;

    public Customer(){
        name = "mockedName";
        coordinates = new Coordinate(48.8534100, 2.3488000);
    }

    public Customer(String id, String name, Coordinate coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        return !(coordinates != null ? !coordinates.equals(customer.coordinates) : customer.coordinates != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }
}
