package com.tvanhuu.poly.quanlychitieu.model;

/**
 * Created by thuu on 09/03/18.
 **/

public class ObjectKhoan {

    private int id;
    private String name, type;

    public ObjectKhoan() {
    }

    public ObjectKhoan(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ObjectKhoan(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
