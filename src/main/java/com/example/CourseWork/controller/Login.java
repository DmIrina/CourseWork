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
//        model.createQueue("Tom", "fff", 67, 5);
//        MedicalQueue tomQ = model.getQueueByDoctor("Tom").get();
//        tomQ.addLast("A", "65636");
//        tomQ.addLast("B", "636");
//        tomQ.addLast("C", "757");
//        model.createQueue("Jerry", "fff", 54, 2);
//        model.createQueue("Levi", "gg", 67, 16);

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("e://model.dat")))
        {
            MQList model = (MQList) ois.readObject();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
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
        if (user.equals("doctor")){
            Optional<MedicalQueue> optQueue = model.getQueueByDoctor(username);
            if(optQueue.isPresent()){
                session.setAttribute("queue", optQueue.get());
                getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);
            } else{
                getServletContext().getRequestDispatcher("/WEB-INF/view/queueCreation.jsp").forward(request, response);
            }
        }
        int k = 1;
    }

    public void destroy() {

    }
}
