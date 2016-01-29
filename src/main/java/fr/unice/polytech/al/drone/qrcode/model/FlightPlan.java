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
    protected String transaction;

    public FlightPlan(){
        order = new Order();
        customer = new Customer();
        timeout = 30000; //20 minutes timeout
        transaction = "0"; //20 minutes timeout
    }

    public FlightPlan(Order order, Customer customer, String transaction){
        this.order = order;
        this.customer = customer;
        timeout = 30000;
        this.transaction = transaction;
    }

    public FlightPlan(Order order, Customer customer, long timeout, String transaction) {
        this.order = order;
        this.customer = customer;
        this.timeout = timeout;
        this.transaction = transaction;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "FlightPlan{" +
                "customer=" + customer +
                ", order=" + order +
                ", timeout=" + timeout +
                ", transaction='" + transaction + '\'' +
                '}';
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

}
