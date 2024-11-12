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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;



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

    public void switchtoBlog(ArrayList<String> posts, ArrayList<String> posts_metadata, ArrayList<String> posts_topics, String ad_no, ActionEvent event) throws IOException {;
        VBox layout = new VBox(20);
        HBox dashboard = new HBox(10);
        HBox dash = new HBox(20);

        //Nav bar

        Button button2 = new Button("Post");
        Button button3 = new Button(ad_no);



        button2.setStyle("-fx-background-color: white;");
        button3.setStyle("-fx-background-color: #0c2e8a; -fx-text-fill: #e0e0e0;");
        button3.setMaxWidth(200);
        dash.getChildren().addAll(button2, button3);
        dash.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().add(dash);

        dashboard.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(100));

        dashboard.setStyle("-fx-font-size: 25;");


        Label blog_label = new Label("Blog");
        blog_label.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");


        VBox childVBox = new VBox(10);
        childVBox.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 30;");
        childVBox.setPadding(new Insets(20));
        childVBox.setPrefWidth(1100);
        layout.getChildren().add(childVBox);


        for (int i=0; i<posts.toArray().length; i++){
            System.out.println(posts_metadata.get(i));
            System.out.println(posts.get(i));
            System.out.println(posts_topics.get(i));
            //VBox childVBox = new VBox(10);
            VBox container = new VBox();
            Text text = new Text(posts.get(i));
            Label label1 = new Label(posts_topics.get(i));
            Label label2 = new Label(posts_metadata.get(i));
            text.setStyle("-fx-font-size: 18; -fx-text-fill: black;");
            label1.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-text-fill: black;");
            label2.setStyle("-fx-font-size: 15; -fx-text-fill: gray;");

            container.getChildren().add(label1);
            container.getChildren().add(text);
            container.getChildren().add(label2);
            container.setStyle("-fx-background-color: #cccccc; -fx-background-radius: 30;");
            container.setPadding(new Insets(20));
            childVBox.getChildren().add(container);

        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(1200);
        scrollPane.setMaxHeight(500);
        scrollPane.setContent(childVBox);

        scrollPane.setStyle("-fx-border-width: 0; -fx-background-color: #e0e0e0; -fx-background-radius: 30;");

        scrollPane.setFitToHeight(true);
        layout.getChildren().add(scrollPane);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void switchtoAccount(ActionEvent event, String ad_no, ArrayList<String> user_posts_comment, ArrayList<String> user_posts_topic) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        VBox layout = new VBox(20);

        Image prof = new Image(getClass().getResourceAsStream("profile4.png"));
        ImageView imageView = new ImageView(prof);

        HBox dashboard = new HBox(20);
        HBox dash = new HBox(20);

        layout.setPadding(new Insets(20));
        dash.setAlignment(Pos.TOP_CENTER);
        dashboard.setAlignment(Pos.CENTER);


        //dash
        Button button1 = new Button("Logout");
        Button button2 = new Button("Blog");
        Button button3 = new Button(ad_no);
        Button logout = new Button("Logout");

        button3.setGraphic(imageView);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        button2.setStyle("-fx-background-color: white;");
        button3.setStyle("-fx-background-color: #0c2e8a; -fx-text-fill: #e0e0e0;");
        dash.setStyle("-fx-background-colour: black; -fx-border-radius: 50px; -fx-font-size: 25;");
        button2.setOnAction(e -> {
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.returnPosts(conn, e, ad_no);
        });

        button3.setOnAction(e->{
            System.out.println("Blank button pressed...");
        });

        layout.getChildren().add(dash);
        dash.getChildren().addAll(button2, button3);

        //BOX FOR USER COMMENTS
        VBox childVBox = new VBox(30);
        childVBox.setStyle("-fx-pref-width: 200; -fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #e0e0e0;-fx-border-color: #e0e0e0; -fx-border-width: 1px; -fx-border-style: solid; -fx-text-fill: white;");
        childVBox.setPadding(new Insets(50));
        childVBox.setPrefWidth(800);
        childVBox.setPrefHeight(300);
        dashboard.getChildren().add(childVBox);

        if(user_posts_comment.toArray().length == 0){
            Text text_error = new Text("No posts yet...");
            childVBox.getChildren().add(text_error);
        }else{
        for(int i=0; i<user_posts_comment.toArray().length; i++){
            Text text = new Text(user_posts_comment.get(i));
            Label label = new Label(user_posts_topic.get(i));
            Line line = new Line();
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
                } catch (Exception f) {
                    System.out.println(f);
                }
            });

            btn2.setOnAction(e -> {
                try {
                    System.out.println("Editing...."+user_posts_comment.get(finalI));
                    Dbfunctions db = new Dbfunctions();
                    Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
                    db.edit_post(conn, user_posts_comment.get(finalI), "mtume", event);
                } catch (Exception f) {
                    System.out.println(f);
                }
            });

            text.setStyle("-fx-font-size: 15; -fx-text-fill: black; -fx-font-style: italic;");
            text.setWrappingWidth(200);
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: black; ");
            btn.setStyle("-fx-background-color: #ed1109; -fx-text-fill: white; -fx-pref-width: 50; -fx-border-width: 2px;");
            btn2.setStyle("-fx-background-color: #09ed74; -fx-text-fill: white; -fx-pref-width: 50; -fx-border-width: 2px;");

            buttons.getChildren().addAll(btn, btn2);
            childVBox.getChildren().add(label);
            childVBox.getChildren().add(text);
            childVBox.getChildren().addAll(buttons);
        }}

        //Post box
        VBox user = new VBox(10);
        Label username = new Label("Make a Post");
        username.setAlignment(Pos.CENTER);
        TextField topic = new TextField("Topic");
        TextField unit = new TextField("Unit");
        TextField course = new TextField("Course");
        TextField comment = new TextField("Comment");
        Button post = new Button("Post");


        logout.setOnAction(e -> {
            try {
                switchtoSignin(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        post.setOnAction(e -> {
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.createPost(conn, ad_no, topic.getText(), course.getText(), comment.getText(), unit.getText(), e);
        });


        //USER POST BOX
        comment.setPrefHeight(200);
        post.setMaxWidth(300);
        user.setMaxHeight(200);
        user.setPrefWidth(400);
        username.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        user.setStyle("-fx-background-radius: 20; -fx-border-radius: 20; -fx-background-color: #e0e0e0; -fx-border-color: #e0e0e0; -fx-border-width: 1px; -fx-border-style: solid;");
        user.setPadding(new Insets(50));


        user.getChildren().addAll(username, unit, topic, course, comment, post);
        dashboard.getChildren().add(user);
        layout.getChildren().add(dashboard);

        //SCROLLBAR FOR THE POST BOX
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(childVBox);
        scrollPane.setStyle("-fx-border-width: 0; -fx-background-color: #e0e0e0; -fx-background-radius: 30;");
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefWidth(500);
        dashboard.getChildren().add(scrollPane);


        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void switchtoPost(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Post.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 320,240);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoSignin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 320,240);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    public void switchtoRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 320,240);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void switchtoHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 320,240);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        //change name to home
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
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

