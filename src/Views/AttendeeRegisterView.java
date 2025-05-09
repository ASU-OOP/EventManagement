package Views;

import Controllers.RegisterController;
import Models.Users.Gender;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AttendeeRegisterView {
    RegisterController registerController = new RegisterController();

    public void attendeeRegisterMenu(Stage stage, String username, String password,
                                  LocalDate birthday) {

        stage.setTitle("Register Form");

        // Create labels for username and password fields.
        Label balanceLabel = new Label("Balance:");
        Label addressLabel = new Label("Address:");
        Label genderLabel = new Label("Gender:");
        Label interestsLabel = new Label("Interests:");

        // Create text input fields for username and password.
        RegisterController.NumFieldFX balanceField = new RegisterController.NumFieldFX();

        final ComboBox<Gender> genderBox = new ComboBox<>();
        genderBox.getItems().setAll(Gender.values());

        TextField addressField = new TextField();
        TextField interestsField = new TextField();

        // Create a label to display the register result.
        Label resultLabel = new Label();

        balanceField.addEventFilter(KeyEvent.KEY_TYPED, t -> {
            char[] ar = t.getCharacter().toCharArray();
            if (!t.getCharacter().isEmpty()) {
                char ch = ar[t.getCharacter().length() - 1];
                if (!(ch >= '0' && ch <= '9')) {
                    resultLabel.setText("Please enter a valid number");
                }
            }
        });

        // Create a "Register" button.
        Button registerButton = new Button("Register");

        // Set an action for the "Register" button to validate the credentials.
        registerButton.setOnAction(event -> {
            if (balanceField.getText().isEmpty()) {
                resultLabel.setText("Please enter a valid balance number");
            } else if (addressField.getText().isEmpty()) {
                resultLabel.setText("Please enter a valid address");
            } else if (genderBox.getSelectionModel().getSelectedItem() == null) {
                resultLabel.setText("Please select a gender");
            } else if (addressField.getText().isEmpty()) {
                resultLabel.setText("Please enter a valid address");
            } else {
                Double balance = Double.parseDouble(balanceField.getText());
                registerController.registerAttendeeUser(username, password, birthday,
                        balance, genderBox.getValue(), addressField.getText(), interestsField.getText());
                resultLabel.setText("User Registered Successfully");
                RegisterController.setRegisterButtonToContinue(registerButton, stage);
            }
        });

        // Create a layout (VBox) to arrange the elements.
        VBox rootView = new VBox(10);
        rootView.getChildren().addAll(balanceLabel, balanceField, genderLabel, genderBox, addressLabel, addressField, interestsLabel, interestsField, registerButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Set the title of the window.
        stage.setTitle("Register Form App");

        // Show the window.
        stage.show();
    }
}
