package com.example.organizer;

import com.example.organizer.Repositories.UserRepo;
import com.example.organizer.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button butSignIn;
    @FXML
    private Button butSignUp;
    @FXML
    private TextField twLogin;
    @FXML
    private TextField twName;
    @FXML
    private PasswordField twPassword;
    @FXML
    private PasswordField twSecondPassword;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butSignIn.setOnAction(SciencesController::toSignIn);
        butSignUp.setOnAction(event -> {
            if(Objects.equals(twLogin.getText(), "") || Objects.equals(twName.getText(), "") || Objects.equals(twPassword.getText(), "") || Objects.equals(twSecondPassword.getText(), "")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Введите все данные!");
                alert.show();
                return;
            }
            if (!Objects.equals(twPassword.getText(), twSecondPassword.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Пороли не совпадают!");
                alert.show();
                return;
            }
            if (twPassword.getText().length() < 3){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Пороль должен содержать не менее 2-х символов");
                alert.show();
                return;
            }
            if (UserRepo.userIsExistsByLogin(twLogin.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Пользователь с таким логином уже существует!");
                alert.show();
                return;
            }
            User user = new User(twLogin.getText(),twPassword.getText(),twName.getText());
            UserRepo.signUpUser(user);
            SciencesController.toSignIn(event);
        });

    }
}
