package com.example.CourseWork.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Optional;

public class MedicalQueue implements Serializable {
    private String doctorName;
    private String specialisation;
    private String cabinet;
    private int currentNum = 0;
    private int maxLength = 10;
    private boolean closed = false;
    private Record servedPatient;
    private LinkedList<Record> list;


    public MedicalQueue(String doctorName, String specialisation, String cabinet) {
        this.list = new LinkedList<>();
        this.doctorName = doctorName;
        this.specialisation = specialisation;
        this.cabinet = cabinet;
    }

    public MedicalQueue(String doctorName, String specialisation, String cabinet, int maxLength) {
        this(doctorName, specialisation, cabinet);
        this.maxLength = maxLength;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public Record getServedPatient() {
        return servedPatient;
    }

    public String getCabinet() {
        return cabinet;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public LinkedList<Record> getList() {
        return list;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public int addLast(String name) {
        if (closed == false) {
            list.addLast(new Record(name, ++currentNum));
            if (currentNum == maxLength) {
                closed = true;
            }
            return currentNum;
        } else {
            return 0;
        }
    }

    public void next() {
        servedPatient = list.removeFirst();
    }

    public void removeByNum(int num) {
        Optional<Record> patient = findByNum(num);
        patient.ifPresent(p -> list.remove(p));
    }

    public void close() {
        closed = true;
    }

    public Optional<Record> findByName(String name) {
        return list.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst();
    }

    public Optional<Record> findByNum(int num) {
        return list.stream()
                .filter(r -> r.getNumInQueue() == num)
                .findFirst();
    }

    public Optional<Record> getPatient(String name) {
        return list.stream()
                .filter(record -> record.getName().equals(name))
                .findFirst();
    }

    public int getNumInQueue(String name) {
        int num = 0;
        Optional<Record> patient = getPatient(name);
        if (patient.isPresent()) {
            num = patient.get().getNumInQueue();
        }
        return num;
    }

    public boolean isOpen() {
        return !closed;
    }
}
