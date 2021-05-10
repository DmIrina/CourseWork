package com.example.CourseWork.model;

import java.util.LinkedList;
import java.util.Optional;

public class MedicalQueue {
    private String doctorName;          // прізвище доктора - хазяїна
    private String specialisation;
    private int cabinet;                // номер кабінету прийому
    private int currentNum = 0;         // поточний номер черги
    private int maxLength = 10;         // максимальна довжина черги
    private boolean closed = false;     // стан черги (відкрита по дефолту)
    private LinkedList<Record> list;


    // створення нової черги, максимальна кількість пацієнтів - 10
    public MedicalQueue(String doctorName, String specialisation, int cabinet) {
        this.list = new LinkedList<>();
        this.doctorName = doctorName;
        this.specialisation = specialisation;
        this.cabinet = cabinet;
    }

    // створення нової черги, максимальна кількість пацієнтів задається в параметрі maxLength
    public MedicalQueue(String doctorName, String specialisation, int cabinet, int maxLength) {
        this(doctorName, specialisation, cabinet);
        this.maxLength = maxLength;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public int getCabinet() {
        return cabinet;
    }

    public String getSpecialisation() {
        return specialisation;
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
        list.removeFirst();
    }

    // видалити з черги заданого пацієнта
    public void remove(String name) {
        Record patient = findByName(name).get();                // todo переделать после optional
        if (patient != null) {
            list.remove(patient);
        } else {
            throw new NullPointerException();
        }
    }

    // закрити чергу від можливості подальших записів
    public void close() {
        closed = true;
    }

    // перегляд інформації про своє місце в черзі
    public Optional<Record> findByName(String name) {
        Optional<Record> result = list.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst();
        return result;
    }

    // чи відкрита черга для запису?
    public boolean isOpen() {
        return !closed;
    }
}
