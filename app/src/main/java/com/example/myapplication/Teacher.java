package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {


    String type="Teacher";

    public Teacher(String first_name,String last_name,String email,String password,String user_name, List<String> subject){
        this.password=password;
        this.email=email;
        this.name=first_name;
        this.price=last_name;
        this.user_name=user_name;
        this.subjects=new ArrayList<>(subject);
    }
}
