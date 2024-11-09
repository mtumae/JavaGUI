package com.mutere.sufeeds.sufeedsproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Objects;

public class Post extends Home{

    @FXML
    private TextField ad_no, topic, course, comment, unit;

    public void CreatePost(ActionEvent event){
        if(Objects.equals(ad_no.getText(), "")|| Objects.equals(topic.getText(), "")||Objects.equals(comment.getText(), "")||Objects.equals(unit.getText(), "")){
            message_box("Please ensure data is entered into all fields","Ok");
        }
    }
}
