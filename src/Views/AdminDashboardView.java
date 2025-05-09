package Views;

import Controllers.DashboardController;
import Models.Users.Admin;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboardView {
    DashboardController dashboardController = new DashboardController();

    public void adminDashboardView(DashboardView dashboardView, Admin admin, Stage stage) {
        stage.setTitle("Admin Dashboard");

        // Create labels for username and password fields.
        Button showAttendeesButton = new Button("Show Attendees");
        Button showRoomsButton = new Button("Show Rooms");
        Button showEventsButton = new Button("Show Events");
        Button updateUsernameButton = new Button("Update Username");
        Button updatePasswordButton = new Button("Update Password");
        Button deleteUserButton = new Button("Delete My User");

        // Set an action for the "Login" button to validate the credentials.
        showAttendeesButton.setOnAction(event -> {
            dashboardController.showAttendees(dashboardView, admin, stage);
        });

        showRoomsButton.setOnAction(event -> {
            dashboardController.showRooms(dashboardView, admin, stage);
        });

        showEventsButton.setOnAction(event -> {
            dashboardController.showEvents(dashboardView, admin, stage);
        });

        updateUsernameButton.setOnAction(event -> {
            dashboardController.showUsernameUpdate(dashboardView, admin, stage);
        });

        updatePasswordButton.setOnAction(event -> {
            dashboardController.showPasswordUpdate(dashboardView, admin, stage);
        });

        deleteUserButton.setOnAction(event -> {
            dashboardController.deleteUser(admin, stage);
        });

        // Create a layout (VBox) to arrange the elements.
        VBox rootView = new VBox(10);
        rootView.getChildren().addAll(showAttendeesButton, showRoomsButton, showEventsButton, updateUsernameButton, updatePasswordButton, deleteUserButton);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }
}
