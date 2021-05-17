package com.example.CourseWork.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class MQList implements Serializable {
    private static MQList instance = new MQList();

    private ArrayList<MedicalQueue> list;

    public static MQList getInstance() {
        return instance;
    }

    private MQList() {
        list = new ArrayList<>();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        instance = this;
    }

    private Object readResolve() {
        return instance;
    }

    public Optional<MedicalQueue> getQueueByDoctor(String doctorName) {
        return list.stream()
                .filter(medicalQueue -> medicalQueue.getDoctorName().equals(doctorName))
                .findFirst();
    }

    public Optional<MedicalQueue> getQueueByCabinet(String cabinet) {
        return list.stream()
                .filter(medicalQueue -> medicalQueue.getCabinet().equals(cabinet))
                .findFirst();
    }

    public ArrayList<MedicalQueue> getQueuesWithPatient(String name){
        ArrayList<MedicalQueue> patientList = new ArrayList<>();
        for (MedicalQueue queue : list) {
            Optional<Record> patient = queue.findByName(name);
            if (patient.isPresent()) {
                patientList.add(queue);
            }
        }
        return patientList;
    }

    public ArrayList<MedicalQueue> getQueuesWithoutPatient(String name){
        ArrayList<MedicalQueue> queuesWithoutPatient;
        queuesWithoutPatient = list.stream()
                .filter(queue -> !queue.getPatient(name).isPresent())
                .collect(Collectors.toCollection(ArrayList::new));
        return queuesWithoutPatient;
    }

    public ArrayList<MedicalQueue> getOpenQueues() {
        return (ArrayList<MedicalQueue>) list.stream()
                .filter(MedicalQueue::isOpen)
                .collect(Collectors.toList());
    }

    public void closeQueueByDoctor(String doctorName) {
        getQueueByDoctor(doctorName).ifPresent(MedicalQueue::close);
    }

    public boolean createQueue(String doctorName, String specialisation, String cabinet, int maxLength) {
        if (!getQueueByCabinet(cabinet).isPresent()) {
            list.add(new MedicalQueue(doctorName, specialisation, cabinet, maxLength));
            return true;
        }
        return false;
    }

    public void setList(ArrayList<MedicalQueue> list) {
        this.list = list;
    }
}
