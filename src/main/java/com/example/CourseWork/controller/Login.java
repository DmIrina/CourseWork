package com.example.CourseWork.controller;

import com.example.CourseWork.helpers.Utils;
import com.example.CourseWork.model.MQList;
import com.example.CourseWork.model.MedicalQueue;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    private MQList model;

    public void init() {
        model = Utils.load();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        model = MQList.getInstance();
        String user = request.getParameter("role");
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", user);
        if (username.equals("")) {
            username = null;
            session.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {

            if (user.equals("doctor")) {
                Optional<MedicalQueue> optQueue = model.getQueueByDoctor(username);
                if (optQueue.isPresent()) {
                    session.setAttribute("queue", optQueue.get());
                    getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);
                } else {
                    getServletContext().getRequestDispatcher("/WEB-INF/view/queueCreation.jsp").forward(request, response);
                }
            } else {
                ArrayList<MedicalQueue> patientList = model.getQueuesWithPatient(username);
                ArrayList<MedicalQueue> noPatientList = model.getQueuesWithoutPatient(username);
                session.setAttribute("patientList", patientList);
                session.setAttribute("noPatientList", noPatientList);
                getServletContext().getRequestDispatcher("/WEB-INF/view/patientInfo.jsp").forward(request, response);
            }
        }
        Utils.save(model);
        int k = 1;
    }

    public void destroy() {

    }
}
