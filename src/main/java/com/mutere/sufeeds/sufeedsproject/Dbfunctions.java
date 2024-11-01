package com.mutere.sufeeds.sufeedsproject;


import java.sql.*;

public class Dbfunctions {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }


    //INSERTING DATA INTO STUDENTS(admission number, name, password)
    public void insertDataStudents(Connection conn, String ad_no, String name, String password) {
        Statement statement;
        try {
            String query = "insert into Students values('" + ad_no + "', '" + name + "', '" + password + "');";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //INSERTING DATA INTO POSTS(ad_no, comment, course)
    public void insertDataPosts(Connection conn, String ad_no, String comment) {
        Statement statement;
        try {
            String query = "insert into Posts values('" + ad_no + "', '" + comment + "', sysdate);";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //QUERYING STUDENT TABLE FOR SIGN IN
    public void check_Student_ad_no(Connection conn, String ad_no) {
        Statement statement;
        try {
            String query = "select * from Students where ad_no='" + ad_no + "' limit 1;";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            boolean exists = result.next();
            System.out.println(exists+" User exists");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("USer must not exist!");
        }
    }
}