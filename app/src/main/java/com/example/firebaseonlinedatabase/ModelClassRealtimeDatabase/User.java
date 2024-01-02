package com.example.firebaseonlinedatabase.ModelClassRealtimeDatabase;

public class User {
    String userid;
    String name;
    String number;

    public User(String userid, String name, String number) {
        this.userid = userid;
        this.name = name;
        this.number = number;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
