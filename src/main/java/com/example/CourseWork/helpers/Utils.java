package com.example.CourseWork.helpers;

import com.example.CourseWork.model.MQList;
import java.io.*;

public class Utils {
    public final static String filePath = "e://model.dat";

    public static void save(MQList model){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static MQList load() {
        MQList model = MQList.getInstance();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Utils.filePath))) {
            model = (MQList) ois.readObject();
            return model;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Utils.save(model);
            return model;
        }
    }
}
