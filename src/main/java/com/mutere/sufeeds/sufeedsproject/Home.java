package com.mutere.sufeeds.sufeedsproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class Home extends Application {
    private Stage stage;
    private Scene scene;
    private Parent root;



    public void message_box(String error_type, String button_text){
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Error");
        dialog.setContentText(error_type);
        ButtonType type = new ButtonType(button_text, ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.show();
    }


    public void populateData(ArrayList<String> posts, ArrayList<String> posts_metadata, ArrayList<String> posts_topics, ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        VBox layout = new VBox(20);
        HBox filters = new HBox(10);
        Label label = new Label("Blog");
        Line line = new Line(10, 0, 600, 0);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10));

        //Nav bar
        Button button1 = new Button("Home");
        Button button2 = new Button("Post");
        Button button3 = new Button("Blog");




        // Create a navbar and add the buttons
        HBox hbox = new HBox(10); // 10 is the spacing between buttons
        hbox.getChildren().addAll(button1, button2, button3);
        hbox.setAlignment(Pos.CENTER);


        label.setStyle("-fx-font-weight: bold; -fx-font-size: 25;");
        //layout.setStyle("-fx-background-color: #e0e0e0;" +
                //"-fx-border-color: black; " +
                //"-fx-border-width: 2px; " +
                //"-fx-border-style: solid; " +
                //
        // "-fx-text-fill: white;");

        layout.getChildren().add(hbox);
        layout.getChildren().add(label);
        layout.getChildren().add(filters);
        layout.getChildren().add(line);

        for (int i=0; i<posts.toArray().length; i++){
            System.out.println(posts_metadata.get(i));
            System.out.println(posts.get(i));
            System.out.println(posts_topics.get(i));
            VBox childVBox = new VBox(10);
            Text text = new Text(posts.get(i));
            Label label1 = new Label(posts_topics.get(i));
            Label label2 = new Label(posts_metadata.get(i));
            text.setStyle("-fx-font-size: 18; -fx-text-fill: black;");
            label1.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-text-fill: black;");
            label2.setStyle("-fx-font-size: 15; -fx-text-fill: gray;");
            line.setStrokeWidth(2);



            childVBox.getChildren().add(label1);
            childVBox.getChildren().add(text);
            childVBox.getChildren().add(label2);
            layout.getChildren().add(childVBox);
        }

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToHeight(true);
        Scene scene = new Scene(scrollPane);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Posts");
        stage.setScene(scene);
        stage.setMaximized(true);
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

    public void switchtoBlog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Blog.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 320,240);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }


    public void switchtoSignin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("Blog.fxml"));
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

