package Views;

import Models.Users.Admin;
import Models.Users.Attendee;
import Models.Users.Organizer;
import Models.Users.User;
import StaticResources.*;

import java.util.Objects;
import java.util.Scanner;

public class LoginView {

    public void loginScreen(Scanner scanner,
                            AdminsDatabase adminsDatabase,
                            AttendeesDatabase attendeesDatabase,
                            OrganizersDatabase organizersDatabase,
                            CategoryDatabase categoryDatabase,
                            EventDatabase eventDatabase,
                            RoomDatabase roomDatabase) {
        DashboardView dashboardView = new DashboardView();
        // Clean scanner because it causes issues sometimes when the last thing we read was an int and not a line lol
        scanner.nextLine();

        User user = askForUsername(scanner,
                adminsDatabase,
                attendeesDatabase,
                organizersDatabase);
        Boolean authenticated = askForPassword(scanner, user);

        if (authenticated) {
            System.out.println("Logging in");
            dashboardView.printDashboard(user,
                    adminsDatabase,
                    attendeesDatabase,
                    organizersDatabase,
                    categoryDatabase,
                    roomDatabase,
                    eventDatabase);
        }
        else {
            System.out.println("Incorrect password");
        }
    }

    private User askForUsername(Scanner scanner,
                                AdminsDatabase adminsDatabase,
                                AttendeesDatabase attendeesDatabase,
                                OrganizersDatabase organizersDatabase) {

        User user = null;
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();
        boolean userExists = false;

        for (Admin admin: adminsDatabase.getAdmins()) {
            if (Objects.equals(admin.username, username)) {
                userExists = true;
                user = admin;
                break;
            }
        }

        for (Attendee attendee: attendeesDatabase.getAttendees()) {
            if (Objects.equals(attendee.username, username)) {
                userExists = true;
                user = attendee;
                break;
            }
        }

        for (Organizer organizer: organizersDatabase.getOrganizers()) {
            if (Objects.equals(organizer.username, username)) {
                userExists = true;
                user = organizer;
                break;
            }
        }

        if (!userExists) {
            System.out.println("User doesn't exist, please try again!");
            return askForUsername(scanner,
                    adminsDatabase,
                    attendeesDatabase,
                    organizersDatabase);
        }

        return user;
    }

    private Boolean askForPassword(Scanner scanner, User user) {
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        if (Objects.equals(user.password, password)) {
            System.out.println("Authenticated");
            return true;
        }
        else {
            return false;
        }
    }
}
