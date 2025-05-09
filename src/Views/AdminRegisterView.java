package Views;

import Controllers.RegisterController;
import Models.Users.UserType;
import Utils.DateUtils;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminRegisterView {
    RegisterController registerController = new RegisterController();

    public void adminRegisterMenu(Stage stage, String username, String password,
                                  LocalDate birthday) {

        stage.setTitle("Register Form");

        // Create labels for username and password fields.
        Label roleLabel = new Label("User Role:");
        Label shiftStartTimeLabel = new Label("Shift Start Time:");
        Label shiftEndTimeLabel = new Label("Shift End Time:");

        // Create text input fields for username and password.
        TextField userRoleField = new TextField();

        final ComboBox<LocalTime> shiftStartTimeBox = new ComboBox<>();
        shiftStartTimeBox.getItems().addAll(DateUtils.getTimeRange());

        final ComboBox<LocalTime> shiftEndTimeBox = new ComboBox<>();
        shiftEndTimeBox.getItems().addAll(DateUtils.getTimeRange());

        // Create a label to display the register result.
        Label resultLabel = new Label();

        // Create a "Register" button.
        Button registerButton = new Button("Register");

        // Set an action for the "Register" button to validate the credentials.
        registerButton.setOnAction(event -> {
            if (userRoleField.getText().isEmpty()) {
                resultLabel.setText("Please enter a user role");
            } else if (shiftStartTimeBox.getSelectionModel().getSelectedItem() == null) {
                resultLabel.setText("Please select a shift start time");
            } else if (shiftEndTimeBox.getSelectionModel().getSelectedItem() == null) {
                resultLabel.setText("Please select a shift end time");
            } else if (!registerController.validateShiftTimes(shiftStartTimeBox.getValue(), shiftEndTimeBox.getValue())) {
                resultLabel.setText("Shift End Time is less than Shift Start Time");
            } else {
                registerController.registerAdminUser(username, password, birthday, userRoleField.getText(),
                        shiftStartTimeBox.getValue(), shiftEndTimeBox.getValue());
                resultLabel.setText("User Registered Successfully");
                RegisterController.setRegisterButtonToContinue(registerButton, stage);
            }
        });

        // Create a layout (VBox) to arrange the elements.
        VBox rootView = new VBox(10);
        rootView.getChildren().addAll(roleLabel, userRoleField, shiftStartTimeLabel, shiftStartTimeBox, shiftEndTimeLabel, shiftEndTimeBox, registerButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Set the title of the window.
        stage.setTitle("Register Form App");

        // Show the window.
        stage.show();
    }
}
