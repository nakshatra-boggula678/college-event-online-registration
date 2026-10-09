package com.studentdetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CountServlet")
public class CountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String URL =
            "jdbc:mysql://localhost:3306/college_event";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Nakshatra@06";

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String sql = "SELECT COUNT(*) FROM registrations";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int total = rs.getInt(1);

                out.print(total);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            out.print("0");
        }
    }
}