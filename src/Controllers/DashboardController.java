package Controllers;

import Models.MiscObjects.Category;
import Models.MiscObjects.Event;
import Models.MiscObjects.Room;
import Models.Users.Admin;
import Models.Users.Attendee;
import Models.Users.Organizer;
import Models.Users.User;
import StaticResources.*;
import Utils.PrintUtils;
import Views.DashboardView;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DashboardController {
    Scanner scanner = new Scanner(System.in);

    PrintUtils printUtils = new PrintUtils();

    public void showAttendees(AttendeesDatabase attendeesDatabase) {
        for (Attendee attendee : attendeesDatabase.getAttendees()) {
            printUtils.printAttendee(attendee);
            System.out.println();
        }
    }

    public void showRooms(RoomDatabase roomDatabase) {
        for (Room room : roomDatabase.getRooms()) {
            printUtils.printRoom(room);
            System.out.println();
        }
        promptCreateRoom(roomDatabase);
    }

    public void showEvents(EventDatabase eventDatabase) {
        for (Event event : eventDatabase.getEvents()) {
            printUtils.printEvent(event);
            System.out.println();
        }
    }

    public void promptCreateRoom(RoomDatabase roomDatabase) {
        System.out.println("Want to create a new room? y/n");
        String selection = scanner.nextLine();

        switch (selection) {
            case "y":
                createRoom(roomDatabase);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection, please try again!");
                promptCreateRoom(roomDatabase);
        }
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

    public void showAttendingEvents(Attendee attendee, EventDatabase eventDatabase) {
        for (Event event : eventDatabase.getEvents()) {
            for (Attendee eventAttendee : event.getAttendees()) {
                if (eventAttendee.equals(attendee)) {
                    System.out.println(event.getName());
                }
            }
        }
    }

    public void showAttendableEvents(Attendee attendee, EventDatabase eventDatabase) {
        List<Event> attendableEvents = new ArrayList<>();
        for (Event event : eventDatabase.getEvents()) {
            for (Attendee eventAttendee : event.getAttendees()) {
                if (!eventAttendee.equals(attendee)) {
                    attendableEvents.add(event);
                }
            }
        }

        if (!attendableEvents.isEmpty()) {
            for (int i = 1; i < attendableEvents.size() + 1; i++) {
                System.out.println(i + ". " + attendableEvents.get(i).getName());
            }

            promptAttendEvent(attendee, attendableEvents, eventDatabase);
        }
    }

    private void promptAttendEvent(Attendee attendee, List<Event> attendableEvents, EventDatabase eventDatabase) {
        System.out.println("Want to attend one of these events? y/n");
        String selection = scanner.nextLine();

        switch (selection) {
            case "y":
                attendEvent(attendee, attendableEvents);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection, please try again!");
                promptAttendEvent(attendee, attendableEvents, eventDatabase);
        }
    }

    private void attendEvent(Attendee attendee, List<Event> attendableEvents) {
        System.out.print("Please input Event Number: ");
        int eventNumber = scanner.nextInt();
        scanner.nextLine();

        if (eventNumber > 0 && eventNumber < attendableEvents.size()) {
            Event event = attendableEvents.get(eventNumber - 1);
            if (attendee.wallet.getBalance() >= event.getPrice()) {
                attendee.wallet.setBalance(attendee.wallet.getBalance() - event.getPrice());
                event.getOrganizer().setBalance(event.getOrganizer().getBalance() + event.getPrice());
                event.addAttendee(attendee);
            } else {
                System.out.println("Insufficient balance");
                attendEvent(attendee, attendableEvents);
            }
        }
        else {
            System.out.println("Invalid Event Number, please try again!");
            attendEvent(attendee, attendableEvents);
        }
    }

    public void changeUsername(User user,
                               AdminsDatabase adminsDatabase,
                               AttendeesDatabase attendeesDatabase,
                               OrganizersDatabase organizersDatabase) {
        System.out.print("Please type your new username: ");
        String username = scanner.nextLine();

        boolean userExists = false;

        for (Admin admin: adminsDatabase.getAdmins()) {
            if (Objects.equals(admin.username, username)) {
                userExists = true;
                break;
            }
        }

        for (Attendee attendee: attendeesDatabase.getAttendees()) {
            if (Objects.equals(attendee.username, username)) {
                userExists = true;
                break;
            }
        }

        for (Organizer organizer: organizersDatabase.getOrganizers()) {
            if (Objects.equals(organizer.username, username)) {
                userExists = true;
                break;
            }
        }

        if (userExists) {
            System.out.println("User already exists, please try again!");
            changeUsername(user, adminsDatabase, attendeesDatabase, organizersDatabase);
        }
        else if (username.length() > 2) {
            user.setUsername(username);
            ;
        } else {
            System.out.println("Invalid Username, please try again!");
            changeUsername(user, adminsDatabase, attendeesDatabase, organizersDatabase);
        }
    }

    public void changePassword(User user) {
        System.out.print("Please enter your new password: ");
        String password = scanner.nextLine();
        if (password.length() > 6) {
            user.setPassword(password);
        }
        else {
            System.out.println("Password too short!");
            changePassword(user);
        }
    }

    public void deleteUserMenu(User user,
                               AdminsDatabase adminsDatabase,
                               AttendeesDatabase attendeesDatabase,
                               OrganizersDatabase organizersDatabase) {
        System.out.println("Are you sure you want to delete this user? y/n");
        String selection = scanner.nextLine();

        switch (selection) {
            case "y":
                user.setActiveStatus(false);
                deleteUser(user, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection, please try again!");
                deleteUserMenu(user, adminsDatabase, attendeesDatabase, organizersDatabase);
        }
    }

    private void deleteUser(User user,
                            AdminsDatabase adminsDatabase,
                            AttendeesDatabase attendeesDatabase,
                            OrganizersDatabase organizersDatabase) {

        switch (user.getUserType()) {
            case ADMIN:
                adminsDatabase.removeAdmin((Admin) user);
                break;
            case ATTENDEE:
                attendeesDatabase.removeAttendee((Attendee) user);
                break;
            case ORGANIZER:
                organizersDatabase.removeOrganizer((Organizer) user);
                break;
            default:
                System.out.println("Invalid user, please try again!");
        }
    }

    public void viewAvailableRooms(Organizer organizer, RoomDatabase roomDatabase, EventDatabase eventDatabase, CategoryDatabase categoryDatabase) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : roomDatabase.getRooms()) {
            if (!room.getAvailableTimes().isEmpty()) {
                availableRooms.add(room);
                printUtils.printRoom(room);
            }
        }

        if (!availableRooms.isEmpty()) {
            // something
            promptCreateEvent(organizer, availableRooms, eventDatabase, categoryDatabase);
        }
    }

    public void viewYourEvents(Organizer organizer, EventDatabase eventDatabase) {
        for (Event event : eventDatabase.getEvents()) {
            if (event.getOrganizer() == organizer) {
                printUtils.printEvent(event);
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
