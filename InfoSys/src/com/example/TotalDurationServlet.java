package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet(name = "TotalDurationServlet", value = "/TotalDurationServlet")
public class TotalDurationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String query = "SELECT SUM(duration_minutes) AS total_duration FROM Activities WHERE user_id = (SELECT user_id FROM Users WHERE username = ?) AND date BETWEEN ? AND ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, startDate);
            pst.setString(3, endDate);
            ResultSet rs = pst.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>TOTAL TIME</h2>");
            if (rs.next()) {
                out.println("Total sport time in " + startDate + " to " + endDate + " is: " + rs.getInt("total_duration") + " minutes<br>");
            } else {
                out.println("NO RECORD FOUND<br>");
            }
            out.println("<a href='home.jsp'>BACK TO HOME</a>");
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}

