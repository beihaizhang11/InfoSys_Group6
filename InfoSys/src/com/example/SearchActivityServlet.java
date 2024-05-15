package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SearchActivityServlet", value = "/SearchActivityServlet")
public class SearchActivityServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String activityType = request.getParameter("activityType");
        String date = request.getParameter("date");

        List<Activity> activities = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            StringBuilder query = new StringBuilder(
                    "SELECT activity_id, activity_type, date, start_time, duration_minutes " +
                            "FROM Activities WHERE user_id = (SELECT user_id FROM Users WHERE username = ?) ");
            if (activityType != null && !activityType.isEmpty()) {
                query.append("AND activity_type = ? ");
            }
            if (date != null && !date.isEmpty()) {
                query.append("AND date = ? ");
            }

            PreparedStatement pst = con.prepareStatement(query.toString());
            pst.setString(1, username);
            int index = 2;
            if (activityType != null && !activityType.isEmpty()) {
                pst.setString(index++, activityType);
            }
            if (date != null && !date.isEmpty()) {
                pst.setString(index, date);
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Activity activity = new Activity();
                activity.setActivityID(rs.getInt("activity_id"));
                activity.setActivityType(rs.getString("activity_type"));
                activity.setDate(rs.getString("date"));
                activity.setStartTime(rs.getString("start_time"));
                activity.setDuration(rs.getInt("duration_minutes"));
                activities.add(activity);
            }

            request.setAttribute("activities", activities);
            request.getRequestDispatcher("searchActivity.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }

    public class Activity {
        private int activityID;
        private String activityType;
        private String date;
        private String startTime;
        private int duration;

        // Getters and Setters
        public int getActivityID() { return activityID; }
        public void setActivityID(int activityID) { this.activityID = activityID; }
        public String getActivityType() { return activityType; }
        public void setActivityType(String activityType) { this.activityType = activityType; }
        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getStartTime() { return startTime; }
        public void setStartTime(String startTime) { this.startTime = startTime; }
        public int getDuration() { return duration; }
        public void setDuration(int duration) { this.duration = duration; }
    }
}

