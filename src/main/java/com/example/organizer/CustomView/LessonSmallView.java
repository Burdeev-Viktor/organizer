package com.example.organizer.CustomView;


import com.example.organizer.LessonEditController;
import com.example.organizer.Main;
import com.example.organizer.SciencesController;
import com.example.organizer.model.Lesson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LessonSmallView extends AnchorPane {
    @FXML
    private Label lbName;
    @FXML
    private Label lbRoom;
    @FXML
    private AnchorPane pane;
    private Lesson lesson;
    public LessonSmallView(Lesson lesson) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(
                "lesson-small.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.lesson = lesson;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        pane.setOnMouseClicked(mouseEvent -> {
            SciencesController.toEditLesson(lesson);
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

