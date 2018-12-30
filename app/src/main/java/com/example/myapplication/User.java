package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    protected String password;
    protected String email;
    protected String price;
    protected String name;
    protected String user_name;
    protected String type;
    protected String image="https://firebasestorage.googleapis.com/v0/b/myapplication-c864e.appspot.com/o/default_user.png?alt=media&token=2ac9bd79-f370-4419-b56d-2fc96cf87e0e";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String type) {
        this.user_name = type;
    }
}
