package com.example.CourseWork.controller;

import com.example.CourseWork.model.MQList;
import com.example.CourseWork.model.MedicalQueue;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.Optional;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    private MQList model;

    public void init() {
        model = MQList.getInstance();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("e://model.dat"))) {
            MQList model = (MQList) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


//        model.createQueue("Tom", "fff", "67a", 5);
//        MedicalQueue tomQ = model.getQueueByDoctor("Tom").get();
//        tomQ.addLast("A", "65636-2342");
//        tomQ.addLast("B", "636-52342");
//        tomQ.addLast("C", "757-222");
//        tomQ.addLast("G", "1112-57");
//        tomQ.addLast("V", "2222-57");
//        tomQ.addLast("N", "3333-57");
//        tomQ.addLast("M", "4444-57");
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("e://model.dat"))) {
//            oos.writeObject(model);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        model = MQList.getInstance();
        String user = request.getParameter("role");
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", user);
        if (user.equals("doctor")) {
            Optional<MedicalQueue> optQueue = model.getQueueByDoctor(username);
            if (optQueue.isPresent()) {
                session.setAttribute("queue", optQueue.get());
                getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/view/queueCreation.jsp").forward(request, response);
            }
        }
        int k = 1;
    }

    public void destroy() {

    }
}
