package Views;

import Controllers.RegisterController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class OrganizerRegisterView {
    RegisterController registerController = new RegisterController();

    public void organizerRegisterMenu(Stage stage, String username, String password,
                                     LocalDate birthday) {

        stage.setTitle("Register Form");

        // Create labels for username and password fields.
        Label balanceLabel = new Label("Balance:");

        // Create text input fields for username and password.
        RegisterController.NumFieldFX balanceField = new RegisterController.NumFieldFX();

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
            } else {
                Double balance = Double.parseDouble(balanceField.getText());
                registerController.registerOrganizerUser(username, password,
                        birthday, balance);
                resultLabel.setText("User Registered Successfully");
                RegisterController.setRegisterButtonToContinue(registerButton, stage);
            }
        });

        // Create a layout (VBox) to arrange the elements.
        VBox rootView = new VBox(10);
        rootView.getChildren().addAll(balanceLabel, balanceField, registerButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Set the title of the window.
        stage.setTitle("Register Form App");

        // Show the window.
        stage.show();
    }
}
