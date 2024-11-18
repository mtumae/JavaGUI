package com.mutere.sufeeds.sufeedsproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.ButtonBar;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;


public class Home extends Application {
    private Stage stage;
    private Scene scene;


    public void message_box(String error_type, String button_text){
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Alert");
        dialog.setContentText(error_type);
        ButtonType type = new ButtonType(button_text, ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.show();
    }


    public void switchtoPost2(ActionEvent event, String ad_no){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        VBox layout = new VBox(20);
        VBox container = new VBox();
        Label topic = new Label("Topic");
        TextField topic_fld = new TextField();
        Label course = new Label("Course");
        TextField course_fld = new TextField();
        Label unit = new Label("Unit");
        TextField unit_fld = new TextField();
        Label comment = new Label("Comment");
        TextArea comment_area= new TextArea();
        Button alr_posted = new Button("Already posted? See your posts");
        Button postbtn = new Button("Post");

        layout.setAlignment(Pos.CENTER);

        alr_posted.setStyle("-fx-background-color: #e0e0e0" );
        container.getChildren().addAll(topic, topic_fld, course, course_fld, unit, unit_fld, comment, comment_area, alr_posted, postbtn);
        container.setPadding(new Insets(20));
        layout.getChildren().add(container);

        postbtn.setOnAction(e-> {
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.createPost(conn, ad_no, topic_fld.getText(), course_fld.getText(), unit_fld.getText(), comment_area.getText(), e);
        });

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoAccount(ActionEvent event, String ad_no, ArrayList<String> user_posts_comment, ArrayList<String> user_posts_topic, ArrayList<String> user_posts_unit) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        VBox layout = new VBox(20);
        Image prof = new Image(getClass().getResourceAsStream("profile.png"));
        ImageView imageView = new ImageView(prof);
        HBox dashboard = new HBox(20);
        HBox dash = new HBox(20);
        layout.setPadding(new Insets(20));
        dash.setAlignment(Pos.CENTER);
        dashboard.setAlignment(Pos.CENTER);


        //dash
        Button button1 = new Button("Logout");
        Button button2 = new Button("Blog");
        Button button3 = new Button(ad_no);

        button3.setGraphic(imageView);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        button1.setStyle("-fx-background-color: #ed1109; -fx-text-fill: white;");
        button2.setStyle("-fx-background-color: white;");
        button3.setStyle("-fx-background-color: #0c2e8a; -fx-text-fill: #e0e0e0;");
        dash.setStyle("-fx-background-colour: black; -fx-border-radius: 50px; -fx-font-size: 25;");



        button1.setOnAction(e->{
            try {
                switchtoSignin(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        button2.setOnAction(e -> {
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.returnPosts(conn, e, ad_no);
        });

        button3.setOnAction(e->{
            message_box(ad_no+" is already logged in", "Ok");
        });

        layout.getChildren().add(dash);
        dash.getChildren().addAll(button2, button3, button1);

        //BOX FOR USER COMMENTS
        VBox childVBox = new VBox(30);
        childVBox.setStyle("-fx-pref-width: 200; -fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #e0e0e0;-fx-border-color: #e0e0e0; -fx-border-width: 1px; -fx-border-style: solid; -fx-text-fill: white;");
        childVBox.setPadding(new Insets(50));
        childVBox.setMaxHeight(300);
        childVBox.setPrefWidth(500);
        Label post_lbl = new Label("Your Posts");
        post_lbl.setAlignment(Pos.CENTER);
        childVBox.getChildren().add(post_lbl);
        dashboard.getChildren().add(childVBox);
        System.out.println(user_posts_unit);

        //CHECK IF LIST IS BLANK
        if( user_posts_comment.toArray().length == 0 ){
            Text text_error = new Text("No posts.Try making a post.");
            childVBox.getChildren().add(text_error);
        }else{
        for(int i=0; i<user_posts_comment.toArray().length; i++){
            VBox container = new VBox();
            container.setPadding(new Insets(50));
            container.setStyle("-fx-background-color: #cccccc; -fx-background-radius: 30;");
            Text text = new Text(user_posts_comment.get(i));
            Label label = new Label(user_posts_topic.get(i));
            HBox buttons = new HBox(10);
            Button btn = new Button("Del");
            Button btn2 = new Button("Edit");
            int finalI = i;
            btn.setOnAction(e -> {
                try {
                    System.out.println("Deleting...."+user_posts_comment.get(finalI));
                    Dbfunctions db = new Dbfunctions();
                    Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
                    db.delete_post(conn, user_posts_comment.get(finalI), event);
                    db.retrieve_User_Posts(conn, ad_no, e);
                } catch (Exception f) {
                    System.out.println(f);
                }
            });

            btn2.setOnAction(e -> {
                try {
                    System.out.println("Editing..."+user_posts_comment.get(finalI)+" for "+ad_no);
                    Dialog<String> dialog = new Dialog<String>();
                    dialog.setTitle("Edit your comment");
                    TextField edit_comment = new TextField("Enter new comment");

                    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                    dialog.getDialogPane().setContent(edit_comment);
                    dialog.setResultConverter(dialogButton -> {
                        if (dialogButton == ButtonType.OK) {
                            return edit_comment.getText();
                        }
                        return null;
                    });
                    dialog.showAndWait().ifPresent(result -> {
                        System.out.println(result);
                        Dbfunctions db = new Dbfunctions();
                        Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
                        db.edit_post(conn, user_posts_comment.get(finalI), edit_comment.getText(), ad_no, event);
                        db.retrieve_User_Posts(conn, ad_no, e);
                    });
                } catch (Exception f) {
                    System.out.println(f);
                }
            });

            text.setStyle("-fx-font-size: 15; -fx-text-fill: black; -fx-font-style: italic;");
            text.setWrappingWidth(400);
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: black; ");
            btn.setStyle("-fx-background-color: #ed1109; -fx-text-fill: white; -fx-pref-width: 50; -fx-border-width: 2px;");
            btn2.setStyle("-fx-background-color: #09ed74; -fx-text-fill: white; -fx-pref-width: 50; -fx-border-width: 2px;");
            buttons.getChildren().addAll(btn, btn2);
            container.getChildren().addAll(label, text, buttons);
            childVBox.getChildren().add(container);
        }}

        //USERS POSTS BOX
        VBox user = new VBox(10);
        Label username = new Label("Make a Post");
        username.setAlignment(Pos.CENTER);
        TextField topic = new TextField("Topic");
        TextField unit = new TextField("Unit");
        TextField course = new TextField("Course");
        TextField comment = new TextField("Comment");
        Button post = new Button("Post");

        comment.setPrefHeight(200);
        user.setMaxHeight(200);
        user.setPrefWidth(400);

        username.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        user.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #e0e0e0; -fx-border-color: #e0e0e0; -fx-border-width: 1px; -fx-border-style: solid;");
        user.setPadding(new Insets(50));
        user.getChildren().addAll(username, unit, topic, course, comment, post);
        dashboard.getChildren().add(user);
        layout.getChildren().add(dashboard);

        //CLASSES BOX
        VBox user_classes = new VBox(10);
        user_classes.setPadding(new Insets(20));
        user_classes.setStyle("-fx-pref-width: 200; -fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #e0e0e0;-fx-border-color: #e0e0e0; -fx-border-width: 1px; -fx-border-style: solid; -fx-text-fill: white;");
        Label title = new Label("Your classes");
        user_classes.getChildren().add(title);
        dashboard.getChildren().add(user_classes);


        Set<String> set = new HashSet<>(user_posts_unit);
        if( user_posts_unit.toArray().length == 0 ){
            Text text_error = new Text("No classes.Make a post to add classes.");
            childVBox.getChildren().add(text_error);
        }else{
        for(String sets : set){
            Text text_unit = new Text(sets);
            text_unit.setStyle("-fx-font-size: 15; -fx-text-fill: black; -fx-font-style: bold; ");
            user_classes.getChildren().add(text_unit);
        }


        post.setOnAction(e -> {
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.createPost(conn, ad_no, topic.getText(), course.getText(), comment.getText(), unit.getText(), e);
        });



        //SCROLLBAR FOR THE POST BOX
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(childVBox);
        scrollPane.setPrefWidth(500);
        scrollPane.setPrefHeight(300);
        scrollPane.setStyle("-fx-border-width: 0; -fx-background-color: #e0e0e0; -fx-background-radius: 30;");
        dashboard.getChildren().add(scrollPane);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }}

    public void switchtoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    };


    public void switchtoSignin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    public void switchtoRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void switchtoPost(ActionEvent event, String ad_no) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Post.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }



    @Override
    public void start(Stage stage) throws IOException {
        //change name to home
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("SUFeeds");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

