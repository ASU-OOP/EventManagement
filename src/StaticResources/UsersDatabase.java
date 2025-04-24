package StaticResources;

import Users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersDatabase {

    public static final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void refreshUsers(AdminsDatabase adminsDatabase, AttendeesDatabase attendeesDatabase, OrganizersDatabase organizersDatabase) {
        users.clear();

        users.addAll(adminsDatabase.getAdmins());
        users.addAll(attendeesDatabase.getAttendees());
        users.addAll(organizersDatabase.getOrganizers());

        Collections.sort(users);
    }

    public UsersDatabase(AdminsDatabase adminsDatabase, AttendeesDatabase attendeesDatabase, OrganizersDatabase organizersDatabase) {
        refreshUsers(adminsDatabase, attendeesDatabase, organizersDatabase);
    }
}
