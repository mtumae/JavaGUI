package com.mutere.sufeeds.sufeedsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;

public class Signin extends Home{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField name, ad_no, pass;


    public void SigninButtonClick(ActionEvent event) throws IOException {
        //VALIDATION
        Dbfunctions db = new Dbfunctions();
        Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
        db.check_Student_ad_no(conn, event, ad_no.getText(), pass.getText());
    }


    public void Blog(ActionEvent event) throws IOException {
        Dbfunctions db = new Dbfunctions();
        Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
        db.returnPosts(conn, event);
    }


}


