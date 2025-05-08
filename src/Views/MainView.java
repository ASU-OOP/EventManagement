package Views;

import StaticResources.*;

import java.util.Scanner;

public class MainView {
    // Using Scanner for Getting Input from User
    static Scanner scanner = new Scanner(System.in);

    AdminsDatabase adminsDB = new AdminsDatabase();
    AttendeesDatabase attendeesDB = new AttendeesDatabase();
    CategoryDatabase categoryDB = new CategoryDatabase();
    OrganizersDatabase organizersDB = new OrganizersDatabase();
    RoomDatabase roomDB = new RoomDatabase();
    EventDatabase eventDB = new EventDatabase(organizersDB, roomDB, categoryDB);
    UsersDatabase usersDB = new UsersDatabase(adminsDB, attendeesDB, organizersDB);

    public static void welcome() {
        System.out.println("Welcome to the Event Management Software");
        System.out.println("Please type the number of your appropriate action");
        printWelcomeActions();
        parseWelcomeActions();
    }

    private static void printWelcomeActions() {
        System.out.println("Please select an action:");
        System.out.println("1: Register");
        System.out.println("2: Login");
    }

    private static void parseWelcomeActions() {
        int action = scanner.nextInt();

        MainView mainView = new MainView();
        if (action == 1) {
            mainView.register(scanner);
        } else if (action == 2) {
            mainView.login(scanner);
        } else {
            System.out.println("Invalid Selection, please one or two");
            printWelcomeActions();
            parseWelcomeActions();
        }
    }

    private void login(Scanner scanner) {
        LoginView loginView = new LoginView();
        loginView.loginScreen(scanner, adminsDB, attendeesDB, organizersDB, categoryDB, eventDB, roomDB);
    }

    private void register(Scanner scanner) {
        RegisterView registerView = new RegisterView();
        registerView.registerScreen(scanner, adminsDB, attendeesDB, organizersDB);
    }
}
