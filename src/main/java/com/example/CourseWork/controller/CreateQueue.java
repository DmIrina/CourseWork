package com.example.CourseWork.controller;

import com.example.CourseWork.helpers.Utils;
import com.example.CourseWork.model.MQList;
import com.example.CourseWork.model.MedicalQueue;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "CreateQueue", value = "/createQueue")
public class CreateQueue extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MQList mqList = MQList.getInstance();
        String username = String.valueOf(request.getSession().getAttribute("username"));
        String cabinet = request.getParameter("cabinet");
        int maxLength = Integer.parseInt(request.getParameter("maxLength"));
        String specialisation = request.getParameter("specialisation");
        MedicalQueue queue = mqList.createQueue(username, specialisation, cabinet, maxLength);
        Utils.save(mqList);
        if (queue != null) {
            request.getSession().setAttribute("queue", queue);
            getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("repeatCreation", true);
            getServletContext().getRequestDispatcher("/WEB-INF/view/queueCreation.jsp").forward(request, response);
        }
    }
}
