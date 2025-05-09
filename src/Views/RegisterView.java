package Views;

import Controllers.RegisterController;
import Models.Users.*;

import java.time.LocalDate;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {

    RegisterController registerController = new RegisterController();

    public void registerMenu(Stage stage) {
        stage.setTitle("Register Form");

        // Create labels for username and password fields.
        Label userTypeLabel = new Label("User Type:");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        // Create text input fields for username and password.
        final ComboBox<UserType> userTypeBox = new ComboBox<>();
        userTypeBox.getItems().setAll(UserType.values());
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        final DatePicker birthdayDatePicker = new DatePicker();
        birthdayDatePicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = birthdayDatePicker.getValue();
                System.err.println("Selected date: " + date);
            }
        });

        // Create a label to display the login result.
        Label resultLabel = new Label();

        // Create a "Login" button.
        Button registerButton = new Button("Continue");

        // Set an action for the "Login" button to validate the credentials.
        registerButton.setOnAction(event -> {
            if (registerController.checkUserExists(usernameField.getText())) {
                resultLabel.setText("Username already exists");
            } else if (userTypeBox.getSelectionModel().getSelectedItem() == null){
                resultLabel.setText("Please select a user type");
            } else if (usernameField.getText().isEmpty()) {
                resultLabel.setText("Username cannot be empty");
            } else if (passwordField.getText().isEmpty()) {
                resultLabel.setText("Password cannot be empty");
            } else if (birthdayDatePicker.getValue() == null) {
                resultLabel.setText("Birthday cannot be empty");
            } else {
                registerController.showUserTypeSpecificOptions(stage, userTypeBox.getValue(),
                        usernameField.getText(), passwordField.getText(),
                        birthdayDatePicker.getValue());
            }
        });

        // Create a layout (VBox) to arrange the elements.
        VBox rootView = new VBox(10);
        rootView.getChildren().addAll(userTypeLabel, userTypeBox, usernameLabel, usernameField, passwordLabel, passwordField, birthdayDatePicker, registerButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Set the title of the window.
        stage.setTitle("Register Form App");

        // Show the window.
        stage.show();
    }
}
