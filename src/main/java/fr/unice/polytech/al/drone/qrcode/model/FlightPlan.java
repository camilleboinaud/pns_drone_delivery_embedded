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
    protected String id;

    public FlightPlan(){
        order = new Order();
        customer = new Customer();
        timeout = 30000; //20 minutes timeout
        id = "0"; //20 minutes timeout
    }

    public FlightPlan(Order order, Customer customer, String id){
        this.order = order;
        this.customer = customer;
        timeout = 30000;
        this.id = id;
    }

    public FlightPlan(Order order, Customer customer, long timeout, String id) {
        this.order = order;
        this.customer = customer;
        this.timeout = timeout;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setDroneID(String id) {
        this.id = id;
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
