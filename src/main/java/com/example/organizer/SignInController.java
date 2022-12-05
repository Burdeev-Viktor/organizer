package com.example.organizer;

import com.example.organizer.Repositories.UserRepo;
import com.example.organizer.SecondTherd.SystemTrayClass;
import com.example.organizer.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    private Button butSignIn;
    @FXML
    private Button butSignUp;
    @FXML
    private TextField twLogin;
    @FXML
    private PasswordField twPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butSignUp.setOnAction(SciencesController::toSignUp);
        butSignIn.setOnAction(event -> {
            if(twLogin.getText() == null || twPassword.getText() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Введите все данные!");
                alert.show();
                return;
            }
            if(!UserRepo.userIsExistsByUser(new User(twLogin.getText(),twPassword.getText()))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Ошибка входа, проверьте Логин или Пороль!");
                alert.show();
                return;
            }
            User user = UserRepo.getUserByLogin(twLogin.getText());
            SciencesController.setUser(user);
            SystemTrayClass.start();
            SciencesController.toMain(event,user);
        });

    }
}
