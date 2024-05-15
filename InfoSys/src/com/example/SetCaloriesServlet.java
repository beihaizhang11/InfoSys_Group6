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

@WebServlet(name = "SetCaloriesServlet", value = "/SetCaloriesServlet")
public class SetCaloriesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityType = request.getParameter("activity_type");
        float caloriesPerMinute = Float.parseFloat(request.getParameter("calories_per_minute"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String query = "INSERT INTO ActivityCalories (activity_type, calories_per_minute) VALUES (?, ?) ON DUPLICATE KEY UPDATE calories_per_minute=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, activityType);
            pst.setFloat(2, caloriesPerMinute);
            pst.setFloat(3, caloriesPerMinute);
            pst.executeUpdate();

            response.sendRedirect("adminHome.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
