package com.example.icart.models.data;


public class Catagory {

    String name, created_at, catagory_avatar;

    public Catagory(String name, String catagory_avatar, String created_at) {
        this.name = name;
        this.catagory_avatar = catagory_avatar;
        this.created_at = created_at;
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

    public String getCatagory_avatar() {
        return catagory_avatar;
    }

    public void setCatagory_avatar(String catagory_avatar) {
        this.catagory_avatar = catagory_avatar;
    }

}
