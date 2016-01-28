package fr.unice.polytech.al.drone.qrcode.model;

import org.json.simple.JSONObject;

import java.io.Serializable;
import java.time.Duration;

/**
 * Created by camille on 06/11/15.
 */
public class FlightPlan {

    protected Customer customer;
    protected Order order;
    protected long timeout;
    protected long droneID;

    public FlightPlan(){
        order = new Order();
        customer = new Customer();
        timeout = 30000; //20 minutes timeout
        droneID = 0; //20 minutes timeout
    }

    public FlightPlan(Order order, Customer customer, long droneID){
        this.order = order;
        this.customer = customer;
        timeout = 30000;
        this.droneID = droneID;
    }

    public FlightPlan(Order order, Customer customer, long timeout, long droneID) {
        this.order = order;
        this.customer = customer;
        this.timeout = timeout;
        this.droneID = droneID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightPlan that = (FlightPlan) o;

        if (timeout != that.timeout) return false;
        if (droneID != that.droneID) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        return order != null ? order.equals(that.order) : that.order == null;

    }

    @Override
    public int hashCode() {
        int result = customer != null ? customer.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (int) (timeout ^ (timeout >>> 32));
        result = 31 * result + (int) (droneID ^ (droneID >>> 32));
        return result;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public long getDroneID() {
        return droneID;
    }

    public void setDroneID(long droneID) {
        this.droneID = droneID;
    }

    @Override
    public String toString() {
        JSONObject o = new JSONObject();

        o.put("customer", customer);
        o.put("order", order);
        o.put("timeout", timeout);

        return o.toJSONString();
    }
}
