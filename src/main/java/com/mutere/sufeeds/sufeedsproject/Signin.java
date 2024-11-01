package com.mutere.sufeeds.sufeedsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class Signin {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField name, pass1, pass2, ad_no;

    @FXML
    public void switchtoSignin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }



    @FXML
    private void SigninButtonClick(ActionEvent event){
        if(!Objects.equals(pass1.getText(), pass2.getText())){
            //REMEMBER TO HAVE ONE DIALOG CLASS AND JUST CALL DIALOG OBJECT INSTEAD OF INSTANTIATING TWICE
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Error");
            dialog.setContentText("Passwords do not match!");
            ButtonType type = new ButtonType("Try again", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();
        }
        else{
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.check_Student_ad_no(conn, ad_no.getText());
        }

    };
}


