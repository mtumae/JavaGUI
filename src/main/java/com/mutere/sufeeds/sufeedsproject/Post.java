package com.mutere.sufeeds.sufeedsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.util.Objects;

public class Post extends Home{
    @FXML
    private TextField ad_no, topic, course, unit;
    @FXML
    private TextArea comment;

    @FXML
    public void PostButtonClick(ActionEvent event){
        if(Objects.equals(topic.getText(), "")|| Objects.equals(course.getText(), "")||Objects.equals(unit.getText(), "")||Objects.equals(comment.getText(), "")){
            message_box("Please ensure you have entered data into all fields.","Ok");
        }else{
            Dbfunctions db = new Dbfunctions();
            Connection conn = db.connect_to_db("db_Mtume_Mutere_188916", "postgres", "");
            db.createPost(conn, ad_no.getText(), topic.getText(), course.getText(), comment.getText(), unit.getText(), event);
        }
    }


}
