package com.example.CourseWork.model;

import java.io.Serializable;

public class Record implements Serializable {
    private String name;
    private int numInQueue;

    public Record(String name, int numInQueue) {
        this.name = name;
        this.numInQueue = numInQueue;
    }

    public String getName() {
        return name;
    }

    public int getNumInQueue() {
        return numInQueue;
    }
}
