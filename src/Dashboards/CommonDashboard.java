package Dashboards;

import StaticResources.AdminsDatabase;
import StaticResources.AttendeesDatabase;
import StaticResources.OrganizersDatabase;
import Users.Admin;
import Users.Attendee;
import Users.Organizer;
import Users.User;

import java.util.Objects;
import java.util.Scanner;

public class CommonDashboard {
    Scanner scanner = new Scanner(System.in);

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
}
