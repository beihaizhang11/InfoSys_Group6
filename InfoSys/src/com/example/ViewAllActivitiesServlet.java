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

@WebServlet(name = "ViewAllActivitiesServlet", value = "/ViewAllActivitiesServlet")
public class ViewAllActivitiesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Activity> activities = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String query = "SELECT activity_id, user_id, activity_type, date, start_time, duration_minutes FROM Activities";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Activity activity = new Activity();
                activity.setActivityId(rs.getInt("activity_id"));
                activity.setUserId(rs.getInt("user_id"));
                activity.setActivityType(rs.getString("activity_type"));
                activity.setDate(rs.getDate("date"));
                activity.setStartTime(rs.getTime("start_time"));
                activity.setDurationMinutes(rs.getInt("duration_minutes"));
                activities.add(activity);
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
            return;
        }

        request.setAttribute("activities", activities);
        request.getRequestDispatcher("viewAllActivities.jsp").forward(request, response);
    }

    public class Activity {
        private int activityId;
        private int userId;
        private String activityType;
        private java.sql.Date date;
        private java.sql.Time startTime;
        private int durationMinutes;

        // Getters and Setters
        public int getActivityId() { return activityId; }
        public void setActivityId(int activityId) { this.activityId = activityId; }
        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }
        public String getActivityType() { return activityType; }
        public void setActivityType(String activityType) { this.activityType = activityType; }
        public java.sql.Date getDate() { return date; }
        public void setDate(java.sql.Date date) { this.date = date; }
        public java.sql.Time getStartTime() { return startTime; }
        public void setStartTime(java.sql.Time startTime) { this.startTime = startTime; }
        public int getDurationMinutes() { return durationMinutes; }
        public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
    }
}
