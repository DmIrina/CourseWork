package com.example.CourseWork.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Optional;

public class MedicalQueue implements Serializable {
    private String doctorName;          // прізвище доктора - хазяїна
    private String specialisation;
    private String cabinet;                // номер кабінету прийому
    private int currentNum = 0;         // поточний номер черги при додаванні
    private int maxLength = 10;         // максимальна довжина черги
    private boolean closed = false;     // стан черги (відкрита по дефолту)
    private Record servedPatient;       // пацієнт на прийомі
    private LinkedList<Record> list;


    // створення нової черги, максимальна кількість пацієнтів - 10
    public MedicalQueue(String doctorName, String specialisation, String cabinet) {
        this.list = new LinkedList<>();
        this.doctorName = doctorName;
        this.specialisation = specialisation;
        this.cabinet = cabinet;
    }

    // створення нової черги, максимальна кількість пацієнтів задається в параметрі maxLength
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

    // заняття місця у черзі
    public int addLast(String name, String phone) {
        if (closed == false) {
            list.addLast(new Record(name, phone, ++currentNum));
            if (currentNum == maxLength) {
                closed = true;
            }
            return currentNum;
        } else {
            return 0;
        }
    }

    // команда «наступний» (видалити з голови черги) - викликати пацієнта на прийом
    public void next() {
        servedPatient = list.removeFirst();
    }

    // видалити з черги заданого пацієнта
    public void remove(String name) {
        Optional<Record> patient = findByName(name);
        patient.ifPresent(p -> list.remove(p));
    }

    // видалити з черги пацієнта за номером у черзі
    public void removeByNum(int num) {
        Optional<Record> patient = findByNum(num);
        patient.ifPresent(p -> list.remove(p));
    }

    // закрити чергу від можливості подальших записів
    public void close() {
        closed = true;
    }

    // перегляд інформації про своє місце в черзі
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

    // чи відкрита черга для запису?
    public boolean isOpen() {
        return !closed;
    }
}
