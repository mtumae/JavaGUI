package com.mutere.sufeeds.sufeedsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class Blog extends Home{
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Label namelbl;
    private Text text;

    protected void onHelloButtonClick() {
        namelbl.setText("Welcome to JavaFX Application!");
    }


    @Override
    public void switchtoLogin(ActionEvent event) throws IOException {
        super.switchtoLogin(event);
    }

    public void switchtoSignin(ActionEvent event) throws IOException {
        super.switchtoSignin(event);
    }

    public void switchtoPost(ActionEvent event) throws IOException {
        super.switchtoPost(event);
    }


}
