package com.example.CourseWork.controller;

import com.example.CourseWork.model.MQList;
import com.example.CourseWork.model.MedicalQueue;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

@WebServlet(name = "QueueService", value = "/queueService")
public class QueueService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MQList mqList = MQList.getInstance();
        String username = String.valueOf(request.getSession().getAttribute("username"));
        Optional<MedicalQueue> queue = mqList.getQueueByDoctor(username);
        if (request.getParameter("next") != null) {
            queue.ifPresent(MedicalQueue::next);
        } else if (request.getParameter("close") != null) {
            queue.ifPresent(MedicalQueue::close);
        }
        request.getSession().setAttribute("queue", queue.get());
        getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("e://model.dat")))
        {
            oos.writeObject(mqList);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);
    }
}
