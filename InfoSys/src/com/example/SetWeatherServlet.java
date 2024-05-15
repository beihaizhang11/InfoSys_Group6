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

@WebServlet(name = "SetWeatherServlet", value = "/SetWeatherServlet")
public class SetWeatherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String weekday = request.getParameter("weekday");
        float temperature = Float.parseFloat(request.getParameter("temperature"));
        String weatherCondition = request.getParameter("weather_condition");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String query = "INSERT INTO Weather (date, weekday, temperature, weather_condition) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE weekday=?, temperature=?, weather_condition=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, date);
            pst.setString(2, weekday);
            pst.setFloat(3, temperature);
            pst.setString(4, weatherCondition);
            pst.setString(5, weekday);
            pst.setFloat(6, temperature);
            pst.setString(7, weatherCondition);
            pst.executeUpdate();

            response.sendRedirect("adminHome.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}

