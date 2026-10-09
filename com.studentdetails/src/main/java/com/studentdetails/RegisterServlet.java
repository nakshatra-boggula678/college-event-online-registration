package com.studentdetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // ==============================
    // DATABASE DETAILS
    // ==============================

    private static final String URL =
            "jdbc:mysql://localhost:3306/college_event";

    private static final String USERNAME = "root";

    // CHANGE THIS TO YOUR MYSQL PASSWORD
    private static final String PASSWORD = "Nakshatra@06";


    // ==============================
    // POST - SAVE REGISTRATION
    // ==============================

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();


        // ==============================
        // GET DATA FROM HTML FORM
        // ==============================

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String roll = request.getParameter("roll");
        String department = request.getParameter("department");
        String year = request.getParameter("year");
        String gender = request.getParameter("gender");
        String event = request.getParameter("event");
        String message = request.getParameter("message");


        // ==============================
        // CHECK REQUIRED FIELDS
        // ==============================

        if (name == null || email == null ||
            phone == null || roll == null ||
            department == null || year == null ||
            gender == null || event == null) {

            out.println("<h2>Registration Failed</h2>");
            out.println("<p>Please fill all the required fields.</p>");

            return;
        }


        // ==============================
        // MYSQL INSERT QUERY
        // ==============================

        String sql =
                "INSERT INTO registrations "
                + "(name, email, phone, roll, department, year, gender, event, message) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");


            // Connect to database
            Connection con = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );


            // Prepare SQL statement
            PreparedStatement ps = con.prepareStatement(sql);


            // Set values
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, roll);
            ps.setString(5, department);
            ps.setString(6, year);
            ps.setString(7, gender);
            ps.setString(8, event);
            ps.setString(9, message);


            // Execute INSERT
            int result = ps.executeUpdate();


            // ==============================
            // SUCCESS
            // ==============================

            if (result > 0) {

                out.println("<!DOCTYPE html>");

                out.println("<html>");

                out.println("<head>");

                out.println("<title>Registration Successful</title>");

                out.println("<style>");

                out.println("body {"
                        + "font-family: Arial;"
                        + "background:#f5f7ff;"
                        + "text-align:center;"
                        + "padding-top:80px;"
                        + "}");

                out.println(".box {"
                        + "width:500px;"
                        + "margin:auto;"
                        + "background:white;"
                        + "padding:35px;"
                        + "border-radius:15px;"
                        + "box-shadow:0 10px 30px rgba(0,0,0,0.15);"
                        + "}");

                out.println("h1 {"
                        + "color:#4f46e5;"
                        + "}");

                out.println("p {"
                        + "font-size:16px;"
                        + "margin:10px;"
                        + "}");

                out.println(".btn {"
                        + "display:inline-block;"
                        + "margin-top:20px;"
                        + "padding:12px 25px;"
                        + "background:#5b4be7;"
                        + "color:white;"
                        + "text-decoration:none;"
                        + "border-radius:25px;"
                        + "}");

                out.println("</style>");

                out.println("</head>");

                out.println("<body>");

                out.println("<div class='box'>");

                out.println("<h1>Registration Successful!</h1>");

                out.println("<p><b>Name:</b> "
                        + name + "</p>");

                out.println("<p><b>Email:</b> "
                        + email + "</p>");

                out.println("<p><b>Phone:</b> "
                        + phone + "</p>");

                out.println("<p><b>Roll Number:</b> "
                        + roll + "</p>");

                out.println("<p><b>Department:</b> "
                        + department + "</p>");

                out.println("<p><b>Year:</b> "
                        + year + "</p>");

                out.println("<p><b>Gender:</b> "
                        + gender + "</p>");

                out.println("<p><b>Event:</b> "
                        + event + "</p>");

                out.println("<p>Your registration has been saved successfully.</p>");

                out.println("<a class='btn' href='register.html'>Register Another Student</a>");

                out.println("</div>");

                out.println("</body>");

                out.println("</html>");
            }


            // Close resources
            ps.close();
            con.close();


        } catch (ClassNotFoundException e) {

            out.println("<h1>MySQL Driver Not Found</h1>");

            out.println("<p>Please add MySQL Connector/J to your project.</p>");

            e.printStackTrace();


        } catch (Exception e) {

            out.println("<h1>Database Connection Failed</h1>");

            out.println("<p>Error: "
                    + e.getMessage()
                    + "</p>");

            e.printStackTrace();
        }
    }
}