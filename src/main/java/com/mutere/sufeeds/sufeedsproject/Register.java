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



public class Register extends Home{
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private Label welcomeText;
    public TextField name, ad_no, pass1, pass2;

    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void RegisterButtonClick(ActionEvent event) throws IOException {
        //Login validation
        if (Objects.equals(ad_no.getText(), "") || Objects.equals(pass1.getText(), "") || Objects.equals(name.getText(), "") || ad_no.getLength() != 6 || pass1.getLength() < 8){
            System.out.println("Ensure you have entered data into all fields");
            message_box("Invalid data! Ensure you have entered correct data into all fields","Try again");

        } else if (!Objects.equals(pass1.getText(), pass2.getText())) {
            System.out.println("Passwords are not equal");}

        else{
            System.out.println("Logged user: " + name.getText());
            //DIALOG MESSAGE SUCCESS
            message_box("User "+ ad_no.getText() +" created", "Ok");

            //ADDING DATA TO STUDENT TABLE
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.insertDataStudents(conn, ad_no.getText(), name.getText(), pass1.getText());
            switchtoSignin(event);
        }}
    }

