package com.example.CourseWork.controller;

import com.example.CourseWork.model.MQList;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    private MQList model;

    public void init() {
        model = new MQList();
        model.createQueue("Tom", "fff", 67, 5);
        model.createQueue("Jerry", "fff", 54, 2);
        model.createQueue("Levi", "gg", 67, 16);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("role");
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", user);
        if (user.equals("doctor")){
            if(model.getQueueByDoctor(username).isPresent()){
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
