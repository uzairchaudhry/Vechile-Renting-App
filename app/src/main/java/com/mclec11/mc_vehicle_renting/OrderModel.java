package com.mclec11.mc_vehicle_renting;

public class OrderModel {

    String order_id="";
    String order_name="";
    String order_placed="";
    String order_amount="";

    public OrderModel(String order_id, String order_name, String order_placed, String order_amount) {
        this.order_id = order_id;
        this.order_name = order_name;
        this.order_placed = order_placed;
        this.order_amount = order_amount;
    }

    public OrderModel(){

    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public void setOrder_placed(String order_placed) {
        this.order_placed = order_placed;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public String getOrder_placed() {
        return order_placed;
    }

    public String getOrder_amount() {
        return order_amount;
    }
}
