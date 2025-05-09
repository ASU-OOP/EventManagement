package Controllers;

import Views.LoginView;
import Views.RegisterView;
import javafx.stage.Stage;

public class MainController {

    public static void login(Stage stage) {
        LoginView loginView = new LoginView();
        loginView.loginMenu(stage);
    }

    public static void register(Stage stage) {
        RegisterView registerView = new RegisterView();
        registerView.registerMenu(stage);
    }
}
