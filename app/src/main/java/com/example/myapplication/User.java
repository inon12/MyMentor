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
    protected String image="https://firebasestorage.googleapis.com/v0/b/myapplication-c864e.appspot.com/o/default_user.png?alt=media&token=257b33ff-4197-43b7-82af-74d6ad47e394";


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
