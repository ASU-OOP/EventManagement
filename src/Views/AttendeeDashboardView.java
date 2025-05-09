package Views;

import Controllers.DashboardController;
import Models.Users.Attendee;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AttendeeDashboardView {
    DashboardController dashboardController = new DashboardController();

    public void attendeeDashboardView(DashboardView dashboardView, Attendee attendee, Stage stage) {
        stage.setTitle("Attendee Dashboard");

        // Create labels for username and password fields.
        Button showAttendingEventsButton = new Button("View Attending Events");
        Button showAttendableEventsButton = new Button("View Attend-able Events");
        Button updateUsernameButton = new Button("Update Username");
        Button updatePasswordButton = new Button("Update Password");
        Button deleteUserButton = new Button("Delete My User");

        // Set an action for the "Login" button to validate the credentials.
        showAttendingEventsButton.setOnAction(event -> {
            dashboardController.showAttendingEvents(dashboardView, attendee, stage);
        });

        showAttendableEventsButton.setOnAction(event -> {
            dashboardController.showAttendableEvents(dashboardView, attendee, stage);
        });

        updateUsernameButton.setOnAction(event -> {
            dashboardController.showUsernameUpdate(dashboardView, attendee, stage);
        });

        updatePasswordButton.setOnAction(event -> {
            dashboardController.showPasswordUpdate(dashboardView, attendee, stage);
        });

        deleteUserButton.setOnAction(event -> {
            dashboardController.deleteUser(attendee, stage);
        });

        // Create a layout (VBox) to arrange the elements.
        VBox rootView = new VBox(10);
        rootView.getChildren().addAll(showAttendingEventsButton, showAttendableEventsButton, updateUsernameButton, updatePasswordButton, deleteUserButton);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }
}