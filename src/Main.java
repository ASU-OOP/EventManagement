import StaticResources.*;

public class Main {
    public static void main(String[] args) {

        AdminsDatabase adminDB = new AdminsDatabase();
        AttendeesDatabase attendeesDB = new AttendeesDatabase();
        CategoryDatabase categoryDB = new CategoryDatabase();
        OrganizersDatabase organizersDB = new OrganizersDatabase();
        RoomDatabase roomDB = new RoomDatabase();
        EventDatabase eventDB = new EventDatabase(organizersDB, roomDB, categoryDB);
    }
}