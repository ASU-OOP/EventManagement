package Dashboards;

import MiscObjects.Event;
import MiscObjects.Room;
import StaticResources.AttendeesDatabase;
import StaticResources.EventDatabase;
import StaticResources.RoomDatabase;
import Users.Attendee;
import Utils.PrintUtils;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDashboard {
    Dashboard dashboard = new Dashboard();
    Scanner scanner = new Scanner(System.in);

    PrintUtils printUtils = new PrintUtils();

    public void showAttendees(AttendeesDatabase attendeesDatabase) {
        for (Attendee attendee : attendeesDatabase.getAttendees()) {
            printUtils.printAttendee(attendee);
            System.out.println();
        }
    }

    public void showRooms(RoomDatabase roomDatabase) {
        for (Room room : roomDatabase.getRooms()) {
            printUtils.printRoom(room);
            System.out.println();
        }
        promptCreateRoom(roomDatabase);
    }

    public void showEvents(EventDatabase eventDatabase) {
        for (Event event : eventDatabase.getEvents()) {
            printUtils.printEvent(event);
            System.out.println();
        }
    }

    public void promptCreateRoom(RoomDatabase roomDatabase) {
        System.out.println("Want to create a new room? y/n");
        String selection = scanner.nextLine();

        switch (selection) {
            case "y":
                createRoom(roomDatabase);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection, please try again!");
                promptCreateRoom(roomDatabase);
        }
    }

    private void createRoom(RoomDatabase roomDatabase) {
        System.out.print("Please input Room Name: ");
        String roomName = scanner.nextLine();
        System.out.print("Please input Room Available Times (comma separated): ");
        List<Time> availableTimes = new ArrayList<>();
        String[] roomAvailableTimes = scanner.nextLine().split(",");
        for (String roomAvailableTime : roomAvailableTimes) {
            LocalTime time = LocalTime.parse(roomAvailableTime);
            availableTimes.add(Time.valueOf(time));
        }

        Room room = new Room(roomName, availableTimes, new ArrayList<Event>());
        roomDatabase.addRoom(room);
    }
}
