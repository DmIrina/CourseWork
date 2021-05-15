package com.example.CourseWork.controller;

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
        mqList.createQueue(username, specialisation, cabinet, maxLength);
        request.getSession().setAttribute("cabinet", cabinet);
        request.getSession().setAttribute("maxLength", maxLength);
        request.getSession().setAttribute("specialisation", specialisation);
        getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);
    }
}
