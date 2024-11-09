package com.mutere.sufeeds.sufeedsproject;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.sql.*;
import java.util.Objects;

//CRUD OPERATIONS( C:2 , R:3 , U:pending , D:1)


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


    //CREATE
    public void insertDataStudents(Connection conn, String ad_no, String name, String password) {
        Statement statement;
        try {
            String query = "insert into Students values('" + ad_no + "', '" + name + "', '" + password + "', current_date);";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //CREATE
    public void createPost(Connection conn, String ad_no, String topic, String course, String comment, String unit ) {
        Statement statement;
        try {
            String query = "insert into Posts values('" + ad_no + "', '" + topic + "',, '" + course + "' , '" + comment + "', '" + unit + "', current_date);";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //READ
    public void check_Student_ad_no(Connection conn, ActionEvent event, String ad_no, String pass) {
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
                    retrieve_User_Posts(conn, ad_no, event);
                }
                else{
                System.out.println(exists+" User exists but password is not correct");
                System.out.println(pass+" should equal "+result.getString("password")+" is your password");
                message_box("Password is incorrect","Try again");

                }
            }
            else{
                System.out.println(exists+" User does not exist");
                message_box("User does not exist. Try again!","Ok");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //READ
    public void retrieveUser(Connection conn, String ad_no) {
        Statement statement;
        try {
            String query = "select * from Students where ad_no='" + ad_no + "' limit 1;";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                System.out.println("User retrieved! " + result.getString("name"));
            };
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //READ
    public void returnPosts(Connection conn, ActionEvent event){
        ArrayList<String> posts = new ArrayList<>();
        ArrayList<String> posts_metadata = new ArrayList<>();
        ArrayList<String> posts_topic = new ArrayList<>();
        Statement statement;
        try {
            String query = "select * from Posts inner join Students using (ad_no)";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                posts.add(result.getString("comment"));
                posts_metadata.add("Made by "+result.getString("name")+" on "+result.getString("date_added"));
                posts_topic.add(result.getString("topic"));
            }

            switchtoBlog(posts, posts_metadata, posts_topic, event);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    //READ
    public void retrieve_User_Posts(Connection conn, String ad_no, ActionEvent event){
        ArrayList<String> user_posts_comment = new ArrayList<>();
        ArrayList<String> user_posts_topic = new ArrayList<>();
        Statement statement;
        try {
            String query = "select * from Posts where ad_no='"+ ad_no+"';";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                System.out.println(result.getString("topic"));
                user_posts_topic.add(result.getString("topic"));
                user_posts_comment.add(result.getString("comment"));
            }switchtoAccount(event, ad_no, user_posts_comment, user_posts_topic);

        }catch (Exception e){
            System.out.println(e);
        }

    }


    //UPDATE
    public void edit_post(Connection conn, String comment, String comment_update, ActionEvent event){
        Statement statement;
        try {
            String query = "update Posts set comment='"+comment_update+"' where comment='"+comment+"';";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Successfully edited Post!");
            message_box("Succesfully edited post!","Ok");

        } catch (Exception e) {
            System.out.println("EDIT aborted: "+e);
            System.out.println(e);
        }

    }

    //DELETE
    public void delete_post(Connection conn, String comment, ActionEvent event){
        Statement statement;
        try {
            String query = "delete from Posts where comment='"+comment+"';";
            statement = conn.createStatement();

            statement.executeUpdate(query);
            System.out.println("Successfully deleted Post!");
            message_box("Successfully deleted Post","Ok");
            retrieve_User_Posts(conn, comment, event);

        }catch (Exception e){
            System.out.println("DELETE aborted: "+e);
        }
    }
}
