package com.example.servlet;

import com.example.dao.ActivityDAO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TotalDurationServlet", value = "/TotalDurationServlet")
public class TotalDurationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try {
            ActivityDAO activityDAO = new ActivityDAO();
            int totalDuration = activityDAO.getTotalDuration(username, startDate, endDate);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>TOTAL TIME</h2>");
            if (totalDuration > 0) {
                out.println("Total sport time from " + startDate + " to " + endDate + " is: " + totalDuration + " minutes<br>");
            } else {
                out.println("NO RECORD FOUND<br>");
            }
            out.println("<a href='home.jsp'>BACK TO HOME</a>");
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}


