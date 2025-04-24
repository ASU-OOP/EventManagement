import StaticResources.*;

import java.util.Scanner;

public class Main {

    AdminsDatabase adminsDB = new AdminsDatabase();
    AttendeesDatabase attendeesDB = new AttendeesDatabase();
    CategoryDatabase categoryDB = new CategoryDatabase();
    OrganizersDatabase organizersDB = new OrganizersDatabase();
    RoomDatabase roomDB = new RoomDatabase();
    EventDatabase eventDB = new EventDatabase(organizersDB, roomDB, categoryDB);
    UsersDatabase usersDB = new UsersDatabase(adminsDB, attendeesDB, organizersDB);

    public static void main(String[] args) {
        // Using Scanner for Getting Input from User
        Scanner scanner = new Scanner(System.in);

        welcome(scanner);
    }

    public static void welcome(Scanner scanner) {
        System.out.println("Welcome to the Event Management Software");
        System.out.println("Please type the number of your appropriate action");
        printWelcomeActions();
        parseWelcomeActions(scanner);
    }

    private static void printWelcomeActions() {
        System.out.println("Please select an action:");
        System.out.println("1: Register");
        System.out.println("2: Login");
    }

    private static void parseWelcomeActions(Scanner scanner) {
        int action = scanner.nextInt();

        Main main = new Main();
        if (action == 1) {
            main.register(scanner);
        } else if (action == 2) {
            main.login(scanner);
        } else {
            System.out.println("Invalid Selection, please one or two");
            printWelcomeActions();
            parseWelcomeActions(scanner);
        }
    }
    private void login(Scanner scanner) {
        Login login = new Login();
        login.loginScreen(scanner, adminsDB, attendeesDB, organizersDB, categoryDB, eventDB, roomDB);
    }

    private void register(Scanner scanner) {
        Register register = new Register();
        register.registerScreen(scanner, adminsDB, attendeesDB, organizersDB);
    }
}