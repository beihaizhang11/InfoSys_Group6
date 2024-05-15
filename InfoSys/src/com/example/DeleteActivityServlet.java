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

@WebServlet(name = "DeleteActivityServlet", value = "/DeleteActivityServlet")
public class DeleteActivityServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activityId = request.getParameter("activityId");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String deleteActivityQuery = "DELETE FROM Activities WHERE activity_id = ?";
            PreparedStatement deleteActivityPst = con.prepareStatement(deleteActivityQuery);
            deleteActivityPst.setString(1, activityId);
            int activityRows = deleteActivityPst.executeUpdate();

            deleteActivityPst.close();
            con.close();

            // Debugging: print number of deleted rows
            System.out.println("Number of activities deleted: " + activityRows);

            response.sendRedirect("ViewAllActivitiesServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}

