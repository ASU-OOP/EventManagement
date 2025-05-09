package Controllers;

import Models.MiscObjects.Category;
import Models.MiscObjects.Event;
import Models.MiscObjects.Room;
import Models.Users.*;
import StaticResources.*;
import Utils.PrintUtils;
import Views.AdminDashboardView;
import Views.DashboardView;
import Views.MainView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DashboardController {
    AttendeesDatabase attendeesDatabase = new AttendeesDatabase();
    OrganizersDatabase organizersDatabase = new OrganizersDatabase();
    RoomDatabase roomDatabase = new RoomDatabase();
    CategoryDatabase categoryDatabase = new CategoryDatabase();
    EventDatabase eventDatabase = new EventDatabase(organizersDatabase, roomDatabase, categoryDatabase);

    RegisterController registerController = new RegisterController();

    Scanner scanner = new Scanner(System.in);

    PrintUtils printUtils = new PrintUtils();

    public void showAttendees(DashboardView dashboardView, Admin admin, Stage stage) {
        stage.setTitle("Attendee List");

        // Create labels for username and password fields.
        Button adminDashboardButton = new Button("Back to Dashboard");

        ObservableList<Attendee> data =
                FXCollections.observableArrayList(attendeesDatabase.getAttendees()
                );

        TableView<Attendee> attendeeTable = new TableView<Attendee>();

        // Set an action for the "Login" button to validate the credentials.
        adminDashboardButton.setOnAction(event -> {
            dashboardView.dashboardView(admin, stage);
        });

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        TableColumn<Attendee, String> usernameColumn = new TableColumn<>("User Name");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(attendee -> new ReadOnlyObjectWrapper(attendee.getValue().getUsername()));

        TableColumn<Attendee, String> birthdayColumn = new TableColumn<>("Date of Birth");
        birthdayColumn.setMinWidth(100);
        birthdayColumn.setCellValueFactory(attendee -> new ReadOnlyObjectWrapper(attendee.getValue().getDateOfBirth().toString()));

        TableColumn<Attendee, String> userTypeColumn = new TableColumn<>("User Type");
        userTypeColumn.setMinWidth(200);
        userTypeColumn.setCellValueFactory(attendee -> new ReadOnlyObjectWrapper(attendee.getValue().getUserType().toString()));

        TableColumn<Attendee, String> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setMinWidth(200);
        balanceColumn.setCellValueFactory(attendee -> new ReadOnlyObjectWrapper(attendee.getValue().getBalance().toString()));

        TableColumn<Attendee, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(attendee -> new ReadOnlyObjectWrapper(attendee.getValue().getAddress()));

        TableColumn<Attendee, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setMinWidth(200);
        genderColumn.setCellValueFactory(attendee -> new ReadOnlyObjectWrapper(attendee.getValue().getGender().toString()));

        TableColumn<Attendee, String> interestsColumn = new TableColumn<>("Interests");
        interestsColumn.setMinWidth(200);
        interestsColumn.setCellValueFactory(attendee -> new ReadOnlyObjectWrapper(attendee.getValue().getInterests()));

        attendeeTable.setEditable(false);
        attendeeTable.setItems(data);
        attendeeTable.getColumns().addAll(Arrays.asList(usernameColumn, birthdayColumn, userTypeColumn, balanceColumn, addressColumn, genderColumn, interestsColumn));
        attendeeTable.getSelectionModel().setCellSelectionEnabled(false);

        final VBox rootView = new VBox();
        rootView.setSpacing(5);
        rootView.setPadding(new Insets(10, 0, 0, 10));
        rootView.getChildren().addAll(label, attendeeTable, adminDashboardButton);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }

    public void showRooms(DashboardView dashboardView, Admin admin, Stage stage) {
        stage.setTitle("Room List");

        // Create labels for username and password fields.
        Button createRoomButton = new Button("Create Room");
        Button adminDashboardButton = new Button("Back to Dashboard");

        ObservableList<Room> data =
                FXCollections.observableArrayList(roomDatabase.getRooms()
                );

        TableView<Room> roomTable = new TableView<Room>();

        // Set an action for the "Login" button to validate the credentials.
        adminDashboardButton.setOnAction(event -> {
            dashboardView.dashboardView(admin, stage);
        });

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        TableColumn<Room, String> roomNameColumn = new TableColumn<>("Room Name");
        roomNameColumn.setMinWidth(100);
        roomNameColumn.setCellValueFactory(room -> new ReadOnlyObjectWrapper(room.getValue().getRoomName()));

        TableColumn<Room, String> availableHoursColumn = new TableColumn<>("Available Hours");
        availableHoursColumn.setMinWidth(100);
        availableHoursColumn.setCellValueFactory(room -> new ReadOnlyObjectWrapper(printUtils.getAvailableHours(room.getValue().getAvailableTimes())));

        TableColumn<Room, String> roomEventsColumn = new TableColumn<>("Room Events");
        roomEventsColumn.setMinWidth(200);
        roomEventsColumn.setCellValueFactory(room -> new ReadOnlyObjectWrapper(printUtils.getEvents(room.getValue().getEvents())));

        roomTable.setEditable(false);
        roomTable.setItems(data);
        roomTable.getColumns().addAll(Arrays.asList(roomNameColumn, availableHoursColumn, roomEventsColumn));
        roomTable.getSelectionModel().setCellSelectionEnabled(false);

        final VBox rootView = new VBox();
        rootView.setSpacing(5);
        rootView.setPadding(new Insets(10, 0, 0, 10));
        rootView.getChildren().addAll(label, roomTable, createRoomButton, adminDashboardButton);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }

    public void showEvents(DashboardView dashboardView, Admin admin, Stage stage) {
        stage.setTitle("Event List");

        // Create labels for username and password fields.
        Button createEventButton = new Button("Create Event");
        Button adminDashboardButton = new Button("Back to Dashboard");

        ObservableList<Event> data =
                FXCollections.observableArrayList(eventDatabase.getEvents()
                );

        TableView<Event> eventTable = new TableView<Event>();

        // Set an action for the "Login" button to validate the credentials.
        adminDashboardButton.setOnAction(event -> {
            dashboardView.dashboardView(admin, stage);
        });

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        TableColumn<Event, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setMinWidth(100);
        eventNameColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getName()));

        TableColumn<Event, String> eventDateColumn = new TableColumn<>("Event Date");
        eventDateColumn.setMinWidth(100);
        eventDateColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getDate().toString()));

        TableColumn<Event, String> eventPriceColumn = new TableColumn<>("Event Price");
        eventPriceColumn.setMinWidth(100);
        eventPriceColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getPrice().toString()));

        TableColumn<Event, String> eventOrganizerColumn = new TableColumn<>("Event Organizer");
        eventOrganizerColumn.setMinWidth(100);
        eventOrganizerColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getOrganizer().getUsername()));

        TableColumn<Event, String> eventRoomColumn = new TableColumn<>("Event Room");
        eventRoomColumn.setMinWidth(100);
        eventRoomColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getRoom().getRoomName()));

        TableColumn<Event, String> eventCategoryColumn = new TableColumn<>("Event Category");
        eventCategoryColumn.setMinWidth(100);
        eventCategoryColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getCategory().toString()));

        TableColumn<Event, String> eventAttendeesColumn = new TableColumn<>("Event Attendees");
        eventAttendeesColumn.setMinWidth(100);
        eventAttendeesColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(printUtils.getEventAttendees(event.getValue().getAttendees())));

        eventTable.setEditable(false);
        eventTable.setItems(data);
        eventTable.getColumns().addAll(Arrays.asList(eventNameColumn, eventDateColumn, eventPriceColumn, eventOrganizerColumn, eventRoomColumn, eventCategoryColumn, eventAttendeesColumn));
        eventTable.getSelectionModel().setCellSelectionEnabled(false);

        final VBox rootView = new VBox();
        rootView.setSpacing(5);
        rootView.setPadding(new Insets(10, 0, 0, 10));
        rootView.getChildren().addAll(label, eventTable, createEventButton, adminDashboardButton);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }

    public void showAttendingEvents(DashboardView dashboardView, Attendee attendee, Stage stage) {
        stage.setTitle("Event List");

        // Create labels for username and password fields.
        Button attendeeDashboardButton = new Button("Back to Dashboard");

        ObservableList<Event> data =
                FXCollections.observableArrayList(getEvents(attendee, eventDatabase.getEvents()));

        TableView<Event> eventTable = new TableView<Event>();

        // Set an action for the "Login" button to validate the credentials.
        attendeeDashboardButton.setOnAction(event -> {
            dashboardView.dashboardView(attendee, stage);
        });

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        TableColumn<Event, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setMinWidth(100);
        eventNameColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getName()));

        TableColumn<Event, String> eventDateColumn = new TableColumn<>("Event Date");
        eventDateColumn.setMinWidth(100);
        eventDateColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getDate().toString()));

        TableColumn<Event, String> eventPriceColumn = new TableColumn<>("Event Price");
        eventPriceColumn.setMinWidth(100);
        eventPriceColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getPrice().toString()));

        TableColumn<Event, String> eventOrganizerColumn = new TableColumn<>("Event Organizer");
        eventOrganizerColumn.setMinWidth(100);
        eventOrganizerColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getOrganizer().getUsername()));

        TableColumn<Event, String> eventRoomColumn = new TableColumn<>("Event Room");
        eventRoomColumn.setMinWidth(100);
        eventRoomColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getRoom().getRoomName()));

        TableColumn<Event, String> eventCategoryColumn = new TableColumn<>("Event Category");
        eventCategoryColumn.setMinWidth(100);
        eventCategoryColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getCategory().toString()));

        eventTable.setEditable(false);
        eventTable.setItems(data);
        eventTable.getColumns().addAll(Arrays.asList(eventNameColumn, eventDateColumn, eventPriceColumn, eventOrganizerColumn, eventRoomColumn, eventCategoryColumn));
        eventTable.getSelectionModel().setCellSelectionEnabled(false);

        final VBox rootView = new VBox();
        rootView.setSpacing(5);
        rootView.setPadding(new Insets(10, 0, 0, 10));
        rootView.getChildren().addAll(label, eventTable, attendeeDashboardButton);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }

    public void showAttendableEvents(DashboardView dashboardView, Attendee attendee, Stage stage) {
        stage.setTitle("Event List");

        // Create a label to display the login result.
        Label resultLabel = new Label();

        // Create labels for username and password fields.
        Button attendEventButton = new Button("Attend Event");
        Button attendeeDashboardButton = new Button("Back to Dashboard");

        ObservableList<Event> data =
                FXCollections.observableArrayList(getAttendableEvents(attendee, eventDatabase.getEvents()));

        TableView<Event> eventTable = new TableView<Event>();

        // Set an action for the "Login" button to validate the credentials.
        attendeeDashboardButton.setOnAction(event -> {
            dashboardView.dashboardView(attendee, stage);
        });

        attendEventButton.setOnAction(event -> {
            Event selectedEvent = eventTable.getFocusModel().getFocusedItem();
            if (selectedEvent.getPrice() > attendee.getBalance()) {
                resultLabel.setText("You don't have enough money!");
            } else {
                attendEvent(attendee, selectedEvent);
                showAttendableEvents(dashboardView, attendee, stage);
            }
        });

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        TableColumn<Event, String> eventNameColumn = new TableColumn<>("Event Name");
        eventNameColumn.setMinWidth(100);
        eventNameColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getName()));

        TableColumn<Event, String> eventDateColumn = new TableColumn<>("Event Date");
        eventDateColumn.setMinWidth(100);
        eventDateColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getDate().toString()));

        TableColumn<Event, String> eventPriceColumn = new TableColumn<>("Event Price");
        eventPriceColumn.setMinWidth(100);
        eventPriceColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getPrice().toString()));

        TableColumn<Event, String> eventOrganizerColumn = new TableColumn<>("Event Organizer");
        eventOrganizerColumn.setMinWidth(100);
        eventOrganizerColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getOrganizer().getUsername()));

        TableColumn<Event, String> eventRoomColumn = new TableColumn<>("Event Room");
        eventRoomColumn.setMinWidth(100);
        eventRoomColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getRoom().getRoomName()));

        TableColumn<Event, String> eventCategoryColumn = new TableColumn<>("Event Category");
        eventCategoryColumn.setMinWidth(100);
        eventCategoryColumn.setCellValueFactory(event -> new ReadOnlyObjectWrapper(event.getValue().getCategory().toString()));

        eventTable.setEditable(false);
        eventTable.setItems(data);
        eventTable.getColumns().addAll(Arrays.asList(eventNameColumn, eventDateColumn, eventPriceColumn, eventOrganizerColumn, eventRoomColumn, eventCategoryColumn));
        eventTable.getSelectionModel().setCellSelectionEnabled(false);

        final VBox rootView = new VBox();
        rootView.setSpacing(5);
        rootView.setPadding(new Insets(10, 0, 0, 10));
        rootView.getChildren().addAll(label, eventTable, attendEventButton, attendeeDashboardButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }

    public void showUsernameUpdate(DashboardView dashboardView, User user, Stage stage) {
        stage.setTitle("Update Username");

        // Create labels for username and password fields.
        Label usernameLabel = new Label("New Username:");
        Label passwordLabel = new Label("Password:");

        // Create text input fields for username and password.
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Create a label to display the login result.
        Label resultLabel = new Label();

        // Create a "Login" button.
        Button confirmButton = new Button("Confirm");

        // Set an action for the "Login" button to validate the credentials.
        confirmButton.setOnAction(event -> {
            if (registerController.checkUserExists(usernameField.getText())) {
                resultLabel.setText("Username already exists");
            } else if (usernameField.getText().isEmpty()) {
                resultLabel.setText("Username cannot be empty");
            } else if (passwordField.getText().isEmpty()) {
                resultLabel.setText("Password cannot be empty");
            } else if (!user.getPassword().equals(passwordField.getText())) {
                resultLabel.setText("Incorrect password");
            } else {
                user.setUsername(usernameField.getText());
                dashboardView.dashboardView(user, stage);
            }
        });

        // Create a layout (VBox) to arrange the elements.
        VBox rootView = new VBox(10);
        rootView.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, confirmButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }

    public void showPasswordUpdate(DashboardView dashboardView, User user, Stage stage) {
        stage.setTitle("Update Password");

        // Create labels for username and password fields.
        Label oldPasswordLabel = new Label("Old Password:");
        Label newPasswordLabel = new Label("New Password:");

        // Create text input fields for username and password.
        PasswordField oldPasswordField = new PasswordField();
        PasswordField newPasswordField = new PasswordField();

        // Create a label to display the login result.
        Label resultLabel = new Label();

        // Create a "Login" button.
        Button confirmButton = new Button("Confirm");

        // Set an action for the "Login" button to validate the credentials.
        confirmButton.setOnAction(event -> {
            if (newPasswordField.getText().isEmpty()) {
                resultLabel.setText("New password cannot be empty");
            } else if (!user.getPassword().equals(oldPasswordField.getText())) {
                resultLabel.setText("Incorrect password");
            } else {
                user.setPassword(newPasswordField.getText());
                dashboardView.dashboardView(user, stage);
            }
        });

        // Create a layout (VBox) to arrange the elements.
        VBox rootView = new VBox(10);
        rootView.getChildren().addAll(oldPasswordLabel, oldPasswordField, newPasswordLabel, newPasswordField, confirmButton, resultLabel);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(rootView, 400, 400);
        stage.setScene(scene);

        // Show the window.
        stage.show();
    }

    public void deleteUser(User user, Stage stage) {
        user.deactivateUser();
        MainView.mainMenu(stage);
    }

    private void createRoom(RoomDatabase roomDatabase) {
        System.out.print("Please input Room Name: ");
        String roomName = scanner.nextLine();
        System.out.print("Please input Room Available Times (comma separated): ");
        List<Time> availableTimes = new ArrayList<>();
        String[] roomAvailableTimes = scanner.nextLine().split(",");
        for (String roomAvailableTime : roomAvailableTimes) {
            LocalTime time = LocalTime.parse(roomAvailableTime);
            availableTimes.add(Time.valueOf(time));
        }

        Room room = new Room(roomName, availableTimes, new ArrayList<Event>());
        roomDatabase.addRoom(room);
    }

    public static List<Event> getEvents(Attendee attendee, List<Event> events) {
        List<Event> attendingEvents = new ArrayList<>();
        for (Event event : events) {
            for (Attendee eventAttendee : event.getAttendees()) {
                if (eventAttendee.equals(attendee)) {
                    attendingEvents.add(event);
                }
            }
        }
        return attendingEvents;
    }

    public List<Event> getAttendableEvents(Attendee attendee, List<Event> events) {
        List<Event> attendableEvents = new ArrayList<>();
        for (Event event : events) {
            for (Attendee eventAttendee : event.getAttendees()) {
                if (!eventAttendee.equals(attendee)) {
                    attendableEvents.add(event);
                }
            }
        }

        return attendableEvents;
    }

    private void attendEvent(Attendee attendee, Event event) {
            if (attendee.wallet.getBalance() >= event.getPrice()) {
                attendee.wallet.setBalance(attendee.wallet.getBalance() - event.getPrice());
                event.getOrganizer().setBalance(event.getOrganizer().getBalance() + event.getPrice());
                event.addAttendee(attendee);
            }
    }

    public void viewYourEvents(Organizer organizer, EventDatabase eventDatabase) {
        for (Event event : eventDatabase.getEvents()) {
            if (event.getOrganizer() == organizer) {
                /*
                printUtils.printEvent(event);

                 */
            }
        }
    }

    private void promptCreateEvent(Organizer organizer, List<Room> availableRooms, EventDatabase eventDatabase, CategoryDatabase categoryDatabase) {
        System.out.println("Want to create a new event? y/n");
        String selection = scanner.nextLine();

        switch (selection) {
            case "y":
                createEvent(organizer, availableRooms, eventDatabase, categoryDatabase);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection, please try again!");
                promptCreateEvent(organizer, availableRooms, eventDatabase, categoryDatabase);
        }
    }

    private void createEvent(Organizer organizer, List<Room> availableRooms, EventDatabase eventDatabase, CategoryDatabase categoryDatabase) {
        System.out.print("Please input Event Name: ");
        String eventName = scanner.nextLine();

        Date date = askForEventDate();

        Room room = askForRoom(availableRooms);

        Category category = askForCategory(categoryDatabase);

        Event event = new Event(eventName, date, organizer, room, category);
        room.addEvent(event);
        eventDatabase.addEvent(event);
    }

    private Date askForEventDate() {
        System.out.print("Please enter Event date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        try {
            return Utils.DateUtils.asDate(LocalDate.parse(date));
        }
        catch (Exception e) {
            System.out.println("Invalid Date of Birth!");
            return askForEventDate();
        }
    }

    private Room askForRoom(List<Room> availableRooms) {
        System.out.print("Please select Room number: ");
        for (int i = 1; i < availableRooms.size() + 1; i++) {
            System.out.println(i + ". " + availableRooms.get(i));
        }

        int roomNumber = scanner.nextInt();

        if (roomNumber > 0 && roomNumber < availableRooms.size()) {
            return availableRooms.get(roomNumber - 1);
        }
        else {
            System.out.println("Invalid Room number!");
            return askForRoom(availableRooms);
        }
    }

    private Category askForCategory(CategoryDatabase categoryDatabase) {
        System.out.print("Please select Category number: ");
        for (int i = 1; i < categoryDatabase.getCategories().size(); i++) {
            Category category = categoryDatabase.getCategories().get(i);
            System.out.println(i + ". " + category.getName());
        }

        int selectedCategory = scanner.nextInt();

        if (selectedCategory > 0 && selectedCategory < categoryDatabase.getCategories().size()) {
            return categoryDatabase.getCategories().get(selectedCategory - 1);
        }
        else {
            System.out.println("Invalid Category number!");
            return askForCategory(categoryDatabase);
        }
    }
}
