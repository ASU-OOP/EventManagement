package Users;

import StaticResources.AdminsDatabase;
import StaticResources.AttendeesDatabase;
import StaticResources.OrganizersDatabase;

import java.sql.Time;
import java.time.LocalDate;
import java.util.*;
import UI.EventManagementApplication;

public class Register {

    public void registerScreen(Scanner scanner,
                               AdminsDatabase adminsDatabase,
                               AttendeesDatabase attendeesDatabase,
                               OrganizersDatabase organizersDatabase) {
        boolean userCreated = false;
        UserType userType = askForUserType(scanner);

        // Clean scanner because it causes issues sometimes when the last thing we read was not a line lol
        scanner.nextLine();

        String username = askForUsername(scanner,
                adminsDatabase,
                attendeesDatabase,
                organizersDatabase);
        String password = askForPassword(scanner);
        Date dateOfBirth = askForDateOfBirth(scanner);
        Double balance = 0.0;
        String address = "";
        Gender gender;
        String interests = "";

        switch (userType) {
            case ADMIN:
                String role = askForRole(scanner);
                List<Time> workingHours = new ArrayList<>();
                Admin admin = new Admin(username, password, dateOfBirth, role, workingHours);
                adminsDatabase.addAdmin(admin);
                userCreated = true;
                break;
            case ATTENDEE:
                balance = askForBalance(scanner);

                // Clean scanner because it causes issues sometimes when the last thing we read was not a line lol
                scanner.nextLine();

                address = askForAddress(scanner);
                gender = askForGender(scanner);

                // Clean scanner because it causes issues sometimes when the last thing we read was not a line lol
                scanner.nextLine();

                interests = askForInterests(scanner);
                Attendee attendee = new Attendee(username,
                        password, dateOfBirth,
                        balance, address,
                        gender, interests);

                attendeesDatabase.addAttendee(attendee);
                userCreated = true;
                break;
            case ORGANIZER:
                balance = askForBalance(scanner);

                // Clean scanner because it causes issues sometimes when the last thing we read was not a line lol
                scanner.nextLine();

                Organizer organizer = new Organizer(username, password, dateOfBirth,balance);
                organizersDatabase.addOrganizer(organizer);
                userCreated = true;
                break;
        }

        if (userCreated) {
            System.out.println("User created successfully. Please login again.");
            EventManagementApplication.welcome(scanner);
        }
    }

    private UserType askForUserType(Scanner scanner) {
        System.out.println("Please select your User Type: ");
        System.out.println("1: Admin");
        System.out.println("2: Attendee");
        System.out.println("3: Organizer");
        int selection = scanner.nextInt();

        if (selection == 1) {
            return UserType.ADMIN;
        } else if (selection == 2) {
            return UserType.ATTENDEE;
        } else if (selection == 3) {
            return UserType.ORGANIZER;
        } else {
            System.out.println("Invalid Selection, Please try again");
            return askForUserType(scanner);
        }
    }

    private String askForUsername(Scanner scanner,
                                  AdminsDatabase adminsDatabase,
                                  AttendeesDatabase attendeesDatabase,
                                  OrganizersDatabase organizersDatabase) {

        System.out.print("Please enter your username: ");
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
            return askForUsername(scanner, adminsDatabase, attendeesDatabase, organizersDatabase);
        }
        else if (username.length() > 2) {
            return username;
        } else {
            System.out.println("Invalid Username, please try again!");
            return askForUsername(scanner, adminsDatabase, attendeesDatabase, organizersDatabase);
        }
    }

    private String askForPassword(Scanner scanner) {
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        if (password.length() > 6) {
            return password;
        }
        else {
            System.out.println("Password too short!");
            return askForPassword(scanner);
        }
    }

    private Date askForDateOfBirth(Scanner scanner) {
        System.out.print("Please enter your Date of Birth (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        try {
            return Utils.DateUtils.asDate(LocalDate.parse(date));
        }
        catch (Exception e) {
            System.out.println("Invalid Date of Birth!");
            return askForDateOfBirth(scanner);
        }
    }

    private String askForRole(Scanner scanner) {
        System.out.print("Please enter your Role: ");
        String role = scanner.nextLine();
        if (!role.isBlank()) {
            return role;
        } else {
            System.out.println("Invalid Role!");
            return askForRole(scanner);
        }
    }

    private String askForAddress(Scanner scanner) {
        System.out.print("Please enter your Address: ");
        String role = scanner.nextLine();
        if (!role.isBlank()) {
            return role;
        } else {
            System.out.println("Invalid Address!");
            return askForAddress(scanner);
        }
    }

    private Double askForBalance(Scanner scanner) {
        System.out.print("Please enter your Balance: ");
        Double balance = scanner.nextDouble();
        if (!balance.isInfinite() && !balance.isNaN()) {
            return balance;
        } else {
            System.out.println("Invalid Balance!");
            return askForBalance(scanner);
        }
    }

    private Gender askForGender(Scanner scanner) {
        System.out.println("Please enter your Gender: ");
        System.out.println("1: Male");
        System.out.println("2: Female");

        int selection = scanner.nextInt();
        if (selection == 1) {
            return Gender.MALE;
        } else if (selection == 2) {
            return Gender.FEMALE;
        } else {
            System.out.println("Invalid Gender!");
            return askForGender(scanner);
        }
    }

    private String askForInterests(Scanner scanner) {
        System.out.print("Please enter your Interests: ");
        String interests = scanner.nextLine();
        if (!interests.isBlank()) {
            return interests;
        } else {
            System.out.println("Invalid Interests!");
            return askForInterests(scanner);
        }
    }
}
