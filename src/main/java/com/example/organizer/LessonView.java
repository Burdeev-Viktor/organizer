package com.example.organizer;


import com.example.organizer.model.Lesson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LessonView extends AnchorPane {
    @FXML
    private Label lbName;
    @FXML
    private Label lbRoom;
    @FXML
    private AnchorPane pane;
    private Lesson lesson;
    public LessonView(Lesson lesson) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(
                "lesson-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.lesson = lesson;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        pane.setOnMouseClicked(mouseEvent -> {

        });
        this.lbName.setText(lesson.getName());
        this.lbRoom.setText(lesson.getRoom());
        switch (lesson.getType()){
            case "Лекция":{
                this.pane.setStyle("-fx-background-color: #c94c4c");
                break;
            }
            case "Лабораторная":{
                this.pane.setStyle("-fx-background-color: #c9ac4cff");
                break;
            }
            case "Практика":{
                this.pane.setStyle("-fx-background-color: #674cc9");
                break;
            }
            case "Консультация":{
                this.pane.setStyle("-fx-background-color: #4cc5c9ff");
                break;
            }
        }
    }

}

