package com.mutere.sufeeds.sufeedsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.LabeledSkinBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class Blog extends Home{
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchtoBlog(ArrayList<String> posts, ArrayList<String> posts_metadata, ArrayList<String> posts_topics, String ad_no, ActionEvent event){;
        VBox layout = new VBox(20);
        HBox dashboard = new HBox(10);
        HBox dash = new HBox(20);
        Image prof = new Image(getClass().getResourceAsStream("profile.png"));
        ImageView imageView = new ImageView(prof);

        //Nav bar
        Button button1 = new Button("Logout");
        Button button2 = new Button("Post");
        Button button3 = new Button(ad_no);

        button1.setStyle("-fx-background-color: #ed1109; -fx-text-fill: white;");
        button3.setStyle("-fx-background-color: #0c2e8a; -fx-text-fill: #e0e0e0;");
        button3.setMaxWidth(200);
        button3.setGraphic(imageView);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        button2.setOnAction(e->{
            try {
                switchtoPost(e, ad_no);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        button1.setOnAction(e->{
            message_box("Logging you out...","Ok");
            try {
                switchtoSignin(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        button3.setOnAction(e->{
                Dbfunctions db = new Dbfunctions();
                Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
                db.retrieve_User_Posts(conn, ad_no, e);
        });


        dash.getChildren().addAll(button3, button1);
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
        childVBox.getChildren().add(blog_label);
        blog_label.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().add(childVBox);


        for (int i=0; i<posts.toArray().length; i++){
            System.out.println(posts_metadata.get(i));
            System.out.println(posts.get(i));
            System.out.println(posts_topics.get(i));
            VBox container = new VBox();
            Text text = new Text(posts.get(i));
            Label label1 = new Label(posts_topics.get(i));
            Label label2 = new Label(posts_metadata.get(i));
            text.setStyle("-fx-font-size: 18; -fx-text-fill: black;");
            label1.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-text-fill: black;");
            label2.setStyle("-fx-font-size: 15; -fx-text-fill: gray;");
            container.getChildren().addAll(label1, text, label2);
            //container.getChildren().add(text);
            //container.getChildren().add(label2);
            container.setStyle("-fx-background-color: #cccccc; -fx-background-radius: 30;");
            container.setPadding(new Insets(20));
            childVBox.getChildren().add(container);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(1200);
        scrollPane.setMaxHeight(500);
        scrollPane.setContent(childVBox);

        scrollPane.setStyle("-fx-border-width: 0; -fx-background-color: #e0e0e0; -fx-background-radius: 30;");

        //scrollPane.setFitToHeight(true);
        layout.getChildren().add(scrollPane);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


    @Override
    public void switchtoLogin(ActionEvent event) throws IOException {
        super.switchtoLogin(event);
    }

    public void switchtoSignin(ActionEvent event) throws IOException {
        super.switchtoSignin(event);
    }


}
