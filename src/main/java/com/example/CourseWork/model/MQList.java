package com.example.CourseWork.model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class MQList {
    private ArrayList<MedicalQueue> list = new ArrayList<>();

    public Optional<MedicalQueue> getQueueByDoctor(String doctorName) {
        return list.stream()
                .filter(medicalQueue -> medicalQueue.getDoctorName().equals(doctorName))
                .findFirst();
    }

    public Optional<MedicalQueue> getQueueByCabinet(int cabinet) {
        return list.stream()
                .filter(medicalQueue -> medicalQueue.getCabinet() == cabinet)
                .findFirst();
    }

    public ArrayList<MedicalQueue> getOpenQueues(){
        return (ArrayList<MedicalQueue>) list.stream()
                .filter(MedicalQueue::isOpen)
                .collect(Collectors.toList());
    }

    public void closeQueueByDoctor(String doctorName){
        getQueueByDoctor(doctorName).ifPresent(MedicalQueue::close);
    }

    public boolean createQueue(String doctorName, String specialisation, int cabinet, int maxLength){
        if (!getQueueByCabinet(cabinet).isPresent()){
            list.add(new MedicalQueue(doctorName, specialisation, cabinet, maxLength));
            return true;
        }
        return false;
    }

    public void setList(ArrayList<MedicalQueue> list) {
        this.list = list;
    }
}
