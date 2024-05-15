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

@WebServlet(name = "DeleteUserServlet", value = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            // 删除活动表中的用户活动记录
            String deleteActivitiesQuery = "DELETE FROM Activities WHERE user_id = ?";
            PreparedStatement deleteActivitiesPst = con.prepareStatement(deleteActivitiesQuery);
            deleteActivitiesPst.setString(1, userId);
            int activityRows = deleteActivitiesPst.executeUpdate();

            // 删除用户资料表中的用户信息
            String deleteUserProfileQuery = "DELETE FROM UserProfile WHERE user_id = ?";
            PreparedStatement deleteUserProfilePst = con.prepareStatement(deleteUserProfileQuery);
            deleteUserProfilePst.setString(1, userId);
            int profileRows = deleteUserProfilePst.executeUpdate();

            // 删除用户表中的用户信息
            String deleteUserQuery = "DELETE FROM Users WHERE user_id = ?";
            PreparedStatement deleteUserPst = con.prepareStatement(deleteUserQuery);
            deleteUserPst.setString(1, userId);
            int userRows = deleteUserPst.executeUpdate();

            deleteActivitiesPst.close();
            deleteUserProfilePst.close();
            deleteUserPst.close();
            con.close();

            // Debugging: print number of deleted rows
            System.out.println("Number of activities deleted: " + activityRows);
            System.out.println("Number of user profiles deleted: " + profileRows);
            System.out.println("Number of users deleted: " + userRows);

            response.sendRedirect("ViewAllUsersServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
