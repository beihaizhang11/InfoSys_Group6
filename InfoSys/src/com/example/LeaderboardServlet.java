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

@WebServlet(name = "LeaderboardServlet", value = "/LeaderboardServlet")
public class LeaderboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String region = request.getParameter("region");
        String gender = request.getParameter("gender");
        String ageRange = request.getParameter("ageRange");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String activityType = request.getParameter("activityType");

        List<User> users = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            StringBuilder query = new StringBuilder(
                    "SELECT U.username, UP.region, UP.gender, UP.age, UP.weight, UP.height, SUM(A.duration_minutes) AS total_duration " +
                            "FROM Users U " +
                            "JOIN Activities A ON U.user_id = A.user_id " +
                            "LEFT JOIN UserProfile UP ON U.user_id = UP.user_id " +
                            "WHERE 1=1 ");

            if (region != null && !region.isEmpty()) {
                query.append("AND UP.region = ? ");
            }
            if (gender != null && !gender.isEmpty()) {
                query.append("AND UP.gender = ? ");
            }
            if (ageRange != null && !ageRange.isEmpty()) {
                switch (ageRange) {
                    case "0-18":
                        query.append("AND UP.age BETWEEN 0 AND 18 ");
                        break;
                    case "19-30":
                        query.append("AND UP.age BETWEEN 19 AND 30 ");
                        break;
                    case "31-50":
                        query.append("AND UP.age BETWEEN 31 AND 50 ");
                        break;
                    case "51+":
                        query.append("AND UP.age >= 51 ");
                        break;
                }
            }
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.append("AND A.date BETWEEN ? AND ? ");
            }
            if (activityType != null && !activityType.isEmpty()) {
                query.append("AND A.activity_type = ? ");
            }

            query.append("GROUP BY U.username, UP.region, UP.gender, UP.age, UP.weight, UP.height " +
                    "ORDER BY total_duration DESC");

            PreparedStatement pst = con.prepareStatement(query.toString());
            int index = 1;
            if (region != null && !region.isEmpty()) {
                pst.setString(index++, region);
            }
            if (gender != null && !gender.isEmpty()) {
                pst.setString(index++, gender);
            }
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                pst.setString(index++, startDate);
                pst.setString(index++, endDate);
            }
            if (activityType != null && !activityType.isEmpty()) {
                pst.setString(index++, activityType);
            }

            // Print the generated SQL query
            System.out.println("Executing query: " + pst);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setRegion(rs.getString("region"));
                user.setGender(rs.getString("gender"));
                user.setAge(rs.getInt("age"));
                user.setWeight(rs.getDouble("weight"));
                user.setHeight(rs.getDouble("height"));
                user.setTotalDuration(rs.getInt("total_duration"));
                users.add(user);
            }

            // Print debug information
            System.out.println("Found " + users.size() + " users");

            request.setAttribute("users", users);
            request.getRequestDispatcher("leaderboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }

    public class User {
        private String username;
        private String region;
        private String gender;
        private int age;
        private double weight;
        private double height;
        private int totalDuration;

        // Getters and Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getRegion() { return region; }
        public void setRegion(String region) { this.region = region; }
        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }
        public double getHeight() { return height; }
        public void setHeight(double height) { this.height = height; }
        public int getTotalDuration() { return totalDuration; }
        public void setTotalDuration(int totalDuration) { this.totalDuration = totalDuration; }
    }
}
