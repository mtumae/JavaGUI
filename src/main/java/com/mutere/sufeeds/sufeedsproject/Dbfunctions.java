package com.mutere.sufeeds.sufeedsproject;


import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Objects;


public class Dbfunctions extends Home{
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
    public void check_Student_ad_no(Connection conn, String ad_no, String pass) {
        Statement statement;
        try {
            String query = "select * from Students where ad_no='" + ad_no + "' limit 1;";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            boolean exists = result.next();
            if(exists){
                if(Objects.equals(pass, result.getString("password"))){
                    System.out.println(exists+" User exists and password is correct" );
                    message_box("Signed in successfully","Ok");

                }
                else{
                System.out.println(exists+" User exists but password is not correct");
                System.out.println(pass+" should equal "+result.getString("password")+" is your password");
                message_box("Password is incorrect","Try again");
                }
            }
            else{
                System.out.println(exists+" User does not exist");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void retrieveUser(Connection conn, ActionEvent event, String ad_no) {
        Statement statement;
        try {
            String query = "select * from Students where ad_no='" + ad_no + "' limit 1;";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                System.out.println("User retrieved! " + result.getString("name"));
                //setNamelbl(result.getString("name"));
                //setAd_nolbl(result.getString("ad_no"));
            };
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void returnPosts(Connection conn, ActionEvent event){
        ArrayList<String> posts = new ArrayList<String>();
        ArrayList<String> posts_metadata = new ArrayList<String>();
        ArrayList<String> posts_topic = new ArrayList<String>();
        Statement statement;
        try {
            String query = "select * from Posts inner join Students using (ad_no)";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                posts.add(result.getString("comment"));
                posts_metadata.add(result.getString("ad_no")+" "+result.getString("name")+" "+result.getString("date_added"));
                posts_topic.add(result.getString("topic"));
            }
            populateData(posts, posts_metadata, posts_topic, event);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
