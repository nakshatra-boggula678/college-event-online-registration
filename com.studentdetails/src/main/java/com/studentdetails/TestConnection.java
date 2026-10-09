package com.studentdetails;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con != null) {

            System.out.println("College Fee Database Connected Successfully!");

            try {
                con.close();
                System.out.println("Connection Closed Successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            System.out.println("Database Connection Failed!");
        }
    }
}