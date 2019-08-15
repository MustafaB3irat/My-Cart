package com.example.icart.models.data;

public class Element {

    private String name, created_at, quantity, price, total, eid;


    public Element(String name, String created_at, String quantity, String price, String total, String eid) {
        this.name = name;
        this.created_at = created_at;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEid() {

        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
