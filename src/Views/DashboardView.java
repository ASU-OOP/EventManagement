package Views;

import Controllers.DashboardController;
import StaticResources.*;
import Models.Users.Admin;
import Models.Users.Attendee;
import Models.Users.Organizer;
import Models.Users.User;

import java.util.Scanner;

public class DashboardView {

    DashboardController dashboardController = new DashboardController();
    Scanner scanner = new Scanner(System.in);

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
                dashboardController.showAttendees(attendeesDatabase);
                break;
            case 2:
                // Show Rooms
                dashboardController.showRooms(roomDatabase);
                break;
            case 3:
                // Show Events
                dashboardController.showEvents(eventDatabase);
                break;
            case 4:
                // Update Username
                dashboardController.changeUsername(admin, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            case 5:
                // Update Password
                dashboardController.changePassword(admin);
                break;
            case 6:
                // Delete my User
                dashboardController.deleteUserMenu(admin, adminsDatabase, attendeesDatabase, organizersDatabase);
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
                dashboardController.showAttendingEvents(attendee, eventDatabase);
                break;
            case 2:
                // View Attendable Events
                dashboardController.showAttendableEvents(attendee, eventDatabase);
                break;
            case 3:
                // Update username
                dashboardController.changeUsername(attendee, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            case 4:
                // Update password
                dashboardController.changePassword(attendee);
                break;
            case 5:
                // Delete User
                dashboardController.deleteUserMenu(attendee, adminsDatabase, attendeesDatabase, organizersDatabase);
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
                dashboardController.changeUsername(organizer, adminsDatabase, attendeesDatabase, organizersDatabase);
                break;
            case 4:
                // Update password
                dashboardController.changePassword(organizer);
                break;
            case 5:
                // Delete User
                dashboardController.deleteUserMenu(organizer, adminsDatabase, attendeesDatabase, organizersDatabase);
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