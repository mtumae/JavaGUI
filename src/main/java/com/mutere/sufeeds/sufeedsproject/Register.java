package com.mutere.sufeeds.sufeedsproject;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;





public class Register extends Dbfunctions {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Label welcomeText;
    public TextField name, pass, ad_no;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void RegisterButtonClick(ActionEvent event) throws IOException {

        //Login validation
        if (Objects.equals(ad_no.getText(), "") || Objects.equals(pass.getText(), "") || Objects.equals(name.getText(), "") || ad_no.getLength() != 6 || pass.getLength() < 8) {
            System.out.println("Ensure you have entered data into all fields");
            //REMEMBER TO HAVE ONE DIALOG CLASS AND JUST CALL DIALOG OBJECT INSTEAD OF INSTANTIATING TWICE
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Error");
            dialog.setContentText("Invalid data! Ensure you have entered all data and");
            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();
        } else if (ad_no.getLength() == 6 && pass.getLength() >= 8) {
            System.out.println("Logged user: " + name.getText());

            //DIALOG MESSAGE SUCCESS
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Success");
            dialog.setContentText("User " + ad_no.getText() + " created");
            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.show();

            //ADDING DATA TO STUDENT TABLE
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.insertDataStudents(conn, ad_no.getText(), name.getText(), pass.getText());


        }
    }
}
