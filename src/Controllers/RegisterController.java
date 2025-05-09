package Controllers;

import Models.Users.*;
import StaticResources.AdminsDatabase;
import StaticResources.AttendeesDatabase;
import StaticResources.OrganizersDatabase;
import Views.AdminRegisterView;
import Views.AttendeeRegisterView;
import Views.MainView;
import Views.OrganizerRegisterView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegisterController {
    static AdminsDatabase adminsDatabase = new AdminsDatabase();
    static AttendeesDatabase attendeesDatabase = new AttendeesDatabase();
    static OrganizersDatabase organizersDatabase = new OrganizersDatabase();

    public boolean validateShiftTimes(LocalTime shiftStart, LocalTime shiftEnd) {
        return !shiftEnd.isBefore(shiftStart) && shiftStart != shiftEnd;
    }

    public Boolean checkUserExists(String username) {
        return checkAdminUserExists(username) || checkAttendeeUserExists(username)
                || checkOrganizerUserExists(username);
    }

    public boolean checkAdminUserExists(String username) {
        for(int i = 0; i < adminsDatabase.getAdmins().size(); i++)
        {
            if (adminsDatabase.getAdmins().get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAttendeeUserExists(String username) {
        for(int i = 0; i < attendeesDatabase.getAttendees().size(); i++)
        {
            if (attendeesDatabase.getAttendees().get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOrganizerUserExists(String username) {
        for(int i = 0; i < organizersDatabase.getOrganizers().size(); i++)
        {
            if (organizersDatabase.getOrganizers().get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void registerAdminUser(String username, String password,
                                  LocalDate dateOfBirth, String role,
                                  LocalTime shiftStart, LocalTime shiftEnd) {
        Admin admin = new Admin(username, password, dateOfBirth, role, shiftStart, shiftEnd);
        adminsDatabase.addAdmin(admin);
    }

    public void registerAttendeeUser(String username, String password,
                                     LocalDate dateOfBirth, Double balance,
                                     Gender gender, String address, String interests) {
        Attendee attendee = new Attendee(username, password, dateOfBirth, balance, address, gender, interests);
        attendeesDatabase.addAttendee(attendee);
    }

    public void registerOrganizerUser(String username, String password,
                                      LocalDate dateOfBirth, Double balance) {
        Organizer organizer = new Organizer(username, password, dateOfBirth, balance);
        organizersDatabase.addOrganizer(organizer);
    }

    public void showUserTypeSpecificOptions(Stage stage,
                                            UserType userType,
                                            String username,
                                            String password,
                                            LocalDate birthday) {

        if (userType == UserType.ADMIN) {
            AdminRegisterView adminRegisterView = new AdminRegisterView();
            adminRegisterView.adminRegisterMenu(stage, username, password, birthday);
        } else if (userType == UserType.ATTENDEE) {
            AttendeeRegisterView attendeeRegisterView = new AttendeeRegisterView();
            attendeeRegisterView.attendeeRegisterMenu(stage, username, password, birthday);
        } else if (userType == UserType.ORGANIZER) {
            OrganizerRegisterView organizerRegisterView = new OrganizerRegisterView();
            organizerRegisterView.organizerRegisterMenu(stage, username, password, birthday);
        }
    }

    public static class NumFieldFX extends TextField {
        public NumFieldFX() {
            this.addEventFilter(KeyEvent.KEY_TYPED, t -> {
                char[] ar = t.getCharacter().toCharArray();
                if (!t.getCharacter().isEmpty()) {
                    char ch = ar[t.getCharacter().length() - 1];
                    if (!(ch >= '0' && ch <= '9')) {
                        System.out.println("The char you entered is not a number");
                        t.consume();
                    }
                }
            });
        }
    }

    public static void setRegisterButtonToContinue(Button button, Stage stage) {
        button.setText("Continue");
        button.setOnAction(event -> {
            MainView.mainMenu(stage);
        });
    }
}
