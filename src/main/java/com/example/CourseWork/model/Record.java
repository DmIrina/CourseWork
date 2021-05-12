package com.example.CourseWork.model;

import java.io.Serializable;

public class Record implements Serializable {
    private String name;
    private int numInQueue;
    private String phone;

    public Record(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Record(String name, String phone, int numInQueue) {
        this.name = name;
        this.phone = phone;
        this.numInQueue = numInQueue;
    }

    public String getName() {
        return name;
    }

    public int getNumInQueue() {
        return numInQueue;
    }

    public void setNumInQueue(int num){
        this.numInQueue = num;
    }

    public String getPhone() {
        return phone;
    }

}
