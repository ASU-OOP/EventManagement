package Dashboards;

import StaticResources.*;
import Users.Admin;
import Users.Attendee;
import Users.Organizer;
import Users.User;

import java.util.Scanner;

public class Dashboard {

    CommonDashboard commonDashboard = new CommonDashboard();

    public void printDashboard(User user, AdminsDatabase adminsDatabase,
                               AttendeesDatabase attendeesDatabase,
                               OrganizersDatabase organizersDatabase,
                               CategoryDatabase categoryDatabase,
                               RoomDatabase roomDatabase,
                               EventDatabase eventDatabase) {

        switch (user.getUserType()) {
            case ADMIN:
                printAdminDashboard((Admin) user,
                        adminsDatabase,
                        attendeesDatabase,
                        organizersDatabase,
                        categoryDatabase,
                        roomDatabase,
                        eventDatabase);
                break;
            case ATTENDEE:
                printAttendeeDashboard((Attendee) user,
                        adminsDatabase,
                        attendeesDatabase,
                        organizersDatabase,
                        categoryDatabase,
                        roomDatabase,
                        eventDatabase);
                break;
            case ORGANIZER:
                printOrganizerDashboard((Organizer) user,
                        adminsDatabase,
                        attendeesDatabase,
                        organizersDatabase,
                        categoryDatabase,
                        roomDatabase,
                        eventDatabase);
                break;
        }
    }

    private void printAdminDashboard(Admin admin, AdminsDatabase adminsDatabase,
                                     AttendeesDatabase attendeesDatabase,
                                     OrganizersDatabase organizersDatabase,
                                     CategoryDatabase categoryDatabase,
                                     RoomDatabase roomDatabase,
                                     EventDatabase eventDatabase) {
        AdminDashboard adminDashboard = new AdminDashboard();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select an action:");
        System.out.println("1: Show Attendees");
        System.out.println("2: Show Rooms");
        System.out.println("3: Show Events");
        System.out.println("4: Update Username");
        System.out.println("5: Update Password");
        System.out.println("5: Delete my User");

        int selection = scanner.nextInt();

        switch (selection) {
            case 1:
                // Show Attendees
                adminDashboard.showAttendees(attendeesDatabase);
                break;
            case 2:
                // Show Rooms
                adminDashboard.showRooms(roomDatabase);
                break;
            case 3:
                // Show Events
                adminDashboard.showEvents(eventDatabase);
                break;
            case 4:
                // Update Username
                commonDashboard.changeUsername(admin, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            case 5:
                // Update Password
                commonDashboard.changePassword(admin);
                break;
            case 6:
                // Delete my User
                commonDashboard.deleteUserMenu(admin, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            default:
                System.out.println("Invalid selection, please try again");
                printAdminDashboard(admin,
                        adminsDatabase,
                        attendeesDatabase,
                        organizersDatabase,
                        categoryDatabase,
                        roomDatabase,
                        eventDatabase);
        }
    }

    private void printAttendeeDashboard(Attendee attendee, AdminsDatabase adminsDatabase,
                                     AttendeesDatabase attendeesDatabase,
                                     OrganizersDatabase organizersDatabase,
                                     CategoryDatabase categoryDatabase,
                                     RoomDatabase roomDatabase,
                                     EventDatabase eventDatabase) {
        AttendeeDashboard attendeeDashboard = new AttendeeDashboard();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select an action:");
        System.out.println("1: View Attending Events");
        System.out.println("2: View Attend-able Events");
        System.out.println("3: Update Username");
        System.out.println("4: Update Password");
        System.out.println("5: Delete my User");

        int selection = scanner.nextInt();

        switch (selection) {
            case 1:
                // View Attending Events
                attendeeDashboard.showAttendingEvents(attendee, eventDatabase);
                break;
            case 2:
                // View Attendable Events
                attendeeDashboard.showAttendableEvents(attendee, eventDatabase);
                break;
            case 3:
                // Update username
                commonDashboard.changeUsername(attendee, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            case 4:
                // Update password
                commonDashboard.changePassword(attendee);
                break;
            case 5:
                // Delete User
                commonDashboard.deleteUserMenu(attendee, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            default:
                System.out.println("Invalid selection, please try again");
                printAttendeeDashboard(attendee,
                        adminsDatabase,
                        attendeesDatabase,
                        organizersDatabase,
                        categoryDatabase,
                        roomDatabase,
                        eventDatabase);
        }
    }

    private void printOrganizerDashboard(Organizer organizer, AdminsDatabase adminsDatabase,
                                     AttendeesDatabase attendeesDatabase,
                                     OrganizersDatabase organizersDatabase,
                                     CategoryDatabase categoryDatabase,
                                     RoomDatabase roomDatabase,
                                     EventDatabase eventDatabase) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select an action:");
        System.out.println("1: View Available Rooms");
        System.out.println("2: View Yours Events");
        System.out.println("3: Update Username");
        System.out.println("4: Update Password");
        System.out.println("5: Delete my User");

        int selection = scanner.nextInt();

        switch (selection) {
            case 1:
                // View Available Rooms
                break;
            case 2:
                // View Yours Events
                break;
            case 3:
                // Update username
                commonDashboard.changeUsername(organizer, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            case 4:
                // Update password
                commonDashboard.changePassword(organizer);
                break;
            case 5:
                // Delete User
                commonDashboard.deleteUserMenu(organizer, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            default:
                System.out.println("Invalid selection, please try again");
                printOrganizerDashboard(organizer,
                        adminsDatabase,
                        attendeesDatabase,
                        organizersDatabase,
                        categoryDatabase,
                        roomDatabase,
                        eventDatabase);
        }
    }
}