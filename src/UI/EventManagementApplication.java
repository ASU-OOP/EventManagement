package UI;

import StaticResources.*;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import Users.Login;
import Users.Register;

public class EventManagementApplication extends Application{

    AdminsDatabase adminsDB = new AdminsDatabase();
    AttendeesDatabase attendeesDB = new AttendeesDatabase();
    CategoryDatabase categoryDB = new CategoryDatabase();
    OrganizersDatabase organizersDB = new OrganizersDatabase();
    RoomDatabase roomDB = new RoomDatabase();
    EventDatabase eventDB = new EventDatabase(organizersDB, roomDB, categoryDB);
    UsersDatabase usersDB = new UsersDatabase(adminsDB, attendeesDB, organizersDB);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Events Bro");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
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

        EventManagementApplication app = new EventManagementApplication();
        if (action == 1) {
            app.register(scanner);
        } else if (action == 2) {
            app.login(scanner);
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