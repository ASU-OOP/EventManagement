package Views;

import Models.Users.User;
import StaticResources.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Controllers.LoginController;

public class LoginView {

    LoginController loginController = new LoginController();

    public void loginMenu(Stage stage) {
        stage.setTitle("Login Form");

        // Create labels for username and password fields.
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        // Create text input fields for username and password.
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Create a label to display the login result.
        Label resultLabel = new Label();

        // Create a "Login" button.
        Button loginButton = new Button("Login");

        // Set an action for the "Login" button to validate the credentials.
        loginButton.setOnAction(event -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();
            User loggedInUser = loginController.validateCredentials(enteredUsername,
                    enteredPassword);

            if (loggedInUser != null) {
                resultLabel.setText("Login successful!");
            } else {
                resultLabel.setText("Login failed. Please check your credentials.");
            }
        });

        // Create a layout (VBox) to arrange the elements.
        VBox root = new VBox(10);
        root.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);

        // Set the title of the window.
        stage.setTitle("Login Form App");

        // Show the window.
        stage.show();
    }
}