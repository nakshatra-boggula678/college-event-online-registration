package com.studentdetails;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection{

    private static final String URL =
            "jdbc:mysql://localhost:3306/college_event";

    private static final String USER = "root";

    private static final String PASSWORD = "Nakshatra@06";

    public static Connection getConnection() {

        Connection con = null;

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("MySQL Driver Loaded Successfully!");
            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return con;
    }

    public static void main(String[] args) {

        Connection con = getConnection();

        if (con != null) {

            System.out.println("College Exam Database Connected Successfully!");

            try {
                con.close();
                System.out.println("Connection Closed Successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            System.out.println("Connection Failed!");
        }
    }
}