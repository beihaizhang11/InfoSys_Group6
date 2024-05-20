package com.example.servlet;

import com.example.dao.ActivityDAO;
import com.example.model.Activity;
import com.example.util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ShareActivityServlet", value = "/ShareActivityServlet")
public class ShareActivityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityIdStr = request.getParameter("activityId");
        int activityId = Integer.parseInt(activityIdStr);

        try {
            ActivityDAO activityDAO = new ActivityDAO();
            Activity activity = activityDAO.getActivityById(activityId);

            if (activity != null) {
                String activityType = activity.getActivityType();
                int duration = activity.getDurationMinutes();
                String date = activity.getDate().toString();
                String startTime = activity.getStartTime().toString();
                float temperature = 0;
                String weatherCondition = "Unknown";

                try (Connection con = DBUtil.getConnection()) {
                    String weatherQuery = "SELECT temperature, weather_condition FROM Weather WHERE date = ?";
                    try (PreparedStatement weatherPst = con.prepareStatement(weatherQuery)) {
                        weatherPst.setString(1, date);
                        try (ResultSet weatherRs = weatherPst.executeQuery()) {
                            if (weatherRs.next()) {
                                temperature = weatherRs.getFloat("temperature");
                                weatherCondition = weatherRs.getString("weather_condition");
                            }
                        }
                    }

                    String caloriesQuery = "SELECT calories_per_minute FROM ActivityCalories WHERE activity_type = ?";
                    try (PreparedStatement caloriesPst = con.prepareStatement(caloriesQuery)) {
                        caloriesPst.setString(1, activityType);
                        try (ResultSet caloriesRs = caloriesPst.executeQuery()) {
                            float caloriesPerMinute = 0;
                            if (caloriesRs.next()) {
                                caloriesPerMinute = caloriesRs.getFloat("calories_per_minute");
                            }

                            float totalCaloriesBurned = caloriesPerMinute * duration;

                            request.setAttribute("activityType", activityType);
                            request.setAttribute("duration", duration);
                            request.setAttribute("date", date);
                            request.setAttribute("startTime", startTime);
                            request.setAttribute("temperature", temperature);
                            request.setAttribute("weatherCondition", weatherCondition);
                            request.setAttribute("totalCaloriesBurned", totalCaloriesBurned);

                            request.getRequestDispatcher("shareActivityResult.jsp").forward(request, response);
                        }
                    }
                }
            } else {
                response.sendRedirect("error.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
