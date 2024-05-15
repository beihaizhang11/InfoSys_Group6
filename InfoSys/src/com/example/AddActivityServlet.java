package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddActivityServlet", value = "/AddActivityServlet")
public class AddActivityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String activityType = request.getParameter("activityType");
        String date = request.getParameter("date");
        String startTime = request.getParameter("startTime");
        int duration = Integer.parseInt(request.getParameter("duration"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String query = "INSERT INTO Activities (user_id, activity_type, date, start_time, duration_minutes) VALUES ((SELECT user_id FROM Users WHERE username = ?), ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, activityType);
            pst.setString(3, date);
            pst.setString(4, startTime);
            pst.setInt(5, duration);
            pst.executeUpdate();

            response.sendRedirect("home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}

