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

@WebServlet(name = "ViewAllUsersServlet", value = "/ViewAllUsersServlet")
public class ViewAllUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String query = "SELECT u.user_id, u.username, p.region, p.gender, p.age, p.weight, p.height " +
                    "FROM Users u " +
                    "LEFT JOIN UserProfile p ON u.user_id = p.user_id";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setRegion(rs.getString("region"));
                user.setGender(rs.getString("gender"));
                user.setAge(rs.getInt("age"));
                user.setWeight(rs.getDouble("weight"));
                user.setHeight(rs.getDouble("height"));
                users.add(user);
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
            return;
        }

        // Debugging: print users list size
        System.out.println("Number of users retrieved: " + users.size());

        request.setAttribute("users", users);
        request.getRequestDispatcher("viewAllUsers.jsp").forward(request, response);
    }

    public class User {
        private int userId;
        private String username;
        private String region;
        private String gender;
        private int age;
        private double weight;
        private double height;

        // Getters and Setters
        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }
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
    }
}
