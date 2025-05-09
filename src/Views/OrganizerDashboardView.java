package Views;

import Controllers.DashboardController;
import Models.Users.Attendee;
import Models.Users.Organizer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrganizerDashboardView {

    DashboardController dashboardController = new DashboardController();

    public void organizerDashboardView(DashboardView dashboardView, Organizer organizer, Stage stage) {
            stage.setTitle("Organizer Dashboard");

            // Create labels for username and password fields.
            Button showAvailableRoomsButton = new Button("View Available Rooms");
            Button showYourEventsButton = new Button("View your Events");
            Button updateUsernameButton = new Button("Update Username");
            Button updatePasswordButton = new Button("Update Password");
            Button deleteUserButton = new Button("Delete My User");

            // Set an action for the "Login" button to validate the credentials.
            showAvailableRoomsButton.setOnAction(event -> {
                dashboardController.viewAvailableRooms(dashboardView, organizer, stage);
            });

            showYourEventsButton.setOnAction(event -> {
                dashboardController.viewYourEvents(dashboardView, organizer, stage);
            });

            updateUsernameButton.setOnAction(event -> {
                dashboardController.showUsernameUpdate(dashboardView, organizer, stage);
            });

            updatePasswordButton.setOnAction(event -> {
                dashboardController.showPasswordUpdate(dashboardView, organizer, stage);
            });

            deleteUserButton.setOnAction(event -> {
                dashboardController.deleteUser(organizer, stage);
            });

            // Create a layout (VBox) to arrange the elements.
            VBox rootView = new VBox(10);
            rootView.getChildren().addAll(showAvailableRoomsButton, showYourEventsButton, updateUsernameButton, updatePasswordButton, deleteUserButton);

            // Create the scene and set it in the stage.
            Scene scene = new Scene(rootView, 400, 400);
            stage.setScene(scene);

            // Show the window.
            stage.show();
        }
    }