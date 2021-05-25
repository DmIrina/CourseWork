package com.example.CourseWork.controller;

import com.example.CourseWork.helpers.Utils;
import com.example.CourseWork.model.MQList;
import com.example.CourseWork.model.MedicalQueue;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "QueueService", value = "/queueService")
public class QueueService extends HttpServlet {

    // викликати на прийом наступного пацієнта або закрити чергу для записів
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
        Utils.save(mqList);
        request.getSession().setAttribute("queue", queue.get());
        getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);
    }
}
