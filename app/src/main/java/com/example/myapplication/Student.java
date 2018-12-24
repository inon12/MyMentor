package com.example.myapplication;

public class Student extends User {

    String type="student";

    public Student(String name,String price,String email,String password,String user_name){
        this.password=password;
        this.email=email;
        this.name=name;
        this.price=price;
        this.user_name=user_name;
    }


}
