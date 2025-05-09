package Controllers;

import Models.Users.Admin;
import Models.Users.Attendee;
import Models.Users.Organizer;
import Models.Users.User;
import StaticResources.AdminsDatabase;
import StaticResources.AttendeesDatabase;
import StaticResources.OrganizersDatabase;

import java.util.Objects;

public class LoginController {
    AdminsDatabase adminsDatabase = new AdminsDatabase();
    AttendeesDatabase attendeesDatabase = new AttendeesDatabase();
    OrganizersDatabase organizersDatabase = new OrganizersDatabase();

    public User validateCredentials(String username, String password) {
        User user = null;

        for (Admin admin: adminsDatabase.getAdmins()) {
            if (Objects.equals(admin.username, username)) {
                user = admin;
                break;
            }
        }

        for (Attendee attendee: attendeesDatabase.getAttendees()) {
            if (Objects.equals(attendee.username, username)) {
                user = attendee;
                break;
            }
        }

        for (Organizer organizer: organizersDatabase.getOrganizers()) {
            if (Objects.equals(organizer.username, username)) {
                user = organizer;
                break;
            }
        }

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}