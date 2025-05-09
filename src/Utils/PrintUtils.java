package Utils;

import Models.MiscObjects.Event;
import Models.MiscObjects.Room;
import Models.Users.*;

import java.sql.Time;

public class PrintUtils {

    public void printUser(User user){
        System.out.println("Username: " + user.getUsername());
        System.out.println("Date of Birth: " + user.getDateOfBirth().toString());
        System.out.println("User Type: " + user.getUserType().toString());
    }

    public void printWalletUser(WalletUser user){
        printUser(user);
        System.out.println("Balance: " + user.wallet.getBalance().toString());
    }

    public void printAttendee(Attendee attendee){
        printWalletUser(attendee);
        System.out.println("Address: " + attendee.getAddress());
        System.out.println("Gender: " + attendee.getGender().toString());
        System.out.println("Interests: " + attendee.getInterests());
    }

    public void printOrganizer(Organizer organizer){
        printWalletUser(organizer);
    }

    public void printAdmin(Admin admin){
        printUser(admin);
        System.out.println("Role: " + admin.getRole());
        System.out.println("Working Hours: " + admin.getWorkingHours());
    }

    public void printRoom(Room room){
        System.out.println("Room Name: " + room.getRoomName());
        System.out.println("Available Hours: ");
        for (Time availableHour : room.getAvailableTimes()) {
            System.out.println("\t" + availableHour.toString());
        }
        System.out.println("Room Events: ");
        for (Event event : room.getEvents()) {
            System.out.println("\t" + event.getName());
        }
    }

    public void printEvent(Event event){
        System.out.println("Event Name: " + event.getName());
        System.out.println("Event Date: " + event.getDate().toString());
        System.out.println("Event Price: " + event.getPrice().toString());
        System.out.println("Event Organizer: " + event.getOrganizer().getUsername());
        System.out.println("Event Room: " + event.getRoom().getRoomName());
        System.out.println("Event Category: " + event.getCategory().getName());
        System.out.println("Event Attendees: ");
        for (Attendee attendee : event.getAttendees()) {
            System.out.println("\t" + attendee.getUsername());
        }
    }
}
