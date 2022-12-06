package com.example.organizer;

import com.example.organizer.Service.ReminderService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        //long in = ReminderService.getMilSeconds("23:02","23:01");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle(Const.TITLE_SIGN_IN);
        stage.setScene(scene);
        stage.show();
    }
}