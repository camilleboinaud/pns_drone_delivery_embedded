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

    public FlightPlan(){
        order = new Order();
        customer = new Customer();
        timeout = 30000; //20 minutes timeout
    }

    public FlightPlan(Order order, Customer customer){
        this.order = order;
        this.customer = customer;
        timeout = 30000;
    }

    public FlightPlan(Order order, Customer customer, long timeout) {
        this.order = order;
        this.customer = customer;
        this.timeout = timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightPlan)) return false;

        FlightPlan that = (FlightPlan) o;

        if (getCustomer() != null ? !getCustomer().equals(that.getCustomer()) : that.getCustomer() != null)
            return false;
        if (getOrder() != null ? !getOrder().equals(that.getOrder()) : that.getOrder() != null) return false;
        return !(getTimeout() != 0 ? getTimeout() != that.getTimeout() : that.getTimeout() != 0);
    }

    @Override
    public int hashCode() {
        int result = getCustomer() != null ? getCustomer().hashCode() : 0;
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        result = 31 * result + (getTimeout() != 0 ? (int)getTimeout() : 31);
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

    @Override
    public String toString() {
        JSONObject o = new JSONObject();

        o.put("customer", customer);
        o.put("order", order);
        o.put("timeout", timeout);

        return o.toJSONString();
    }
}
