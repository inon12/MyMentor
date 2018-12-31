package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

    protected float rank;
    protected List<String> subjects;
    protected int count;

    public Teacher(){}
    public Teacher(String first_name, String last_name, String email, String password, String user_name, List<String> subject,float rank,String type){
        this.password=password;
        this.email=email;
        this.name=first_name;
        this.price=last_name;
        this.user_name=user_name;
        this.subjects=new ArrayList<>(subject);
        this.rank=rank;
        this.type=type;
    }
   public float getRank() {
        return rank;
  }
    public void setRank(float rank) {
       this.rank = rank;
    }
    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
