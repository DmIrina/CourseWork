package com.example.CourseWork.controller;

import com.example.CourseWork.helpers.Utils;
import com.example.CourseWork.model.MQList;
import com.example.CourseWork.model.MedicalQueue;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "RemovePatient", value = "/removePatient")
public class RemovePatient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // видалити з черги пацієнтів за позначеними номерами у черзі
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("id");
        MQList mqList = MQList.getInstance();
        String username = String.valueOf(request.getSession().getAttribute("username"));
        Optional<MedicalQueue> queue = mqList.getQueueByDoctor(username);
        if (ids != null && ids.length > 0) {
            for (String item: ids) {
                int num = Integer.parseInt(item);
                queue.ifPresent(q -> q.removeByNum(num));
            }
        }
        Utils.save(mqList);
        request.getSession().setAttribute("queue", queue.get());
        getServletContext().getRequestDispatcher("/WEB-INF/view/queueManaging.jsp").forward(request, response);
    }
}
