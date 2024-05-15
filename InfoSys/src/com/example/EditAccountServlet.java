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

@WebServlet(name = "EditAccountServlet", value = "/EditAccountServlet")
public class EditAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String oldUsername = (String) session.getAttribute("username");

        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportActivityDB", "root", "20040422");

            String query = "UPDATE Users SET username = ?, password = ? WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, newUsername);
            pst.setString(2, newPassword);
            pst.setString(3, oldUsername);
            pst.executeUpdate();

            // 更新 session 中的用户名
            session.setAttribute("username", newUsername);

            response.sendRedirect("home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
