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

@WebServlet(name = "EditProfileServlet", value = "/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String region = request.getParameter("region");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        double height = Double.parseDouble(request.getParameter("height"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String query = "UPDATE UserProfile SET region = ?, gender = ?, age = ?, weight = ?, height = ? WHERE user_id = (SELECT user_id FROM Users WHERE username = ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, region);
            pst.setString(2, gender);
            pst.setInt(3, age);
            pst.setDouble(4, weight);
            pst.setDouble(5, height);
            pst.setString(6, username);
            pst.executeUpdate();

            response.sendRedirect("home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
