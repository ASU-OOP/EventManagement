package Dashboards;

import MiscObjects.Category;
import MiscObjects.Event;
import MiscObjects.Room;
import StaticResources.CategoryDatabase;
import StaticResources.EventDatabase;
import StaticResources.RoomDatabase;
import Users.Organizer;
import Utils.PrintUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrganizerDashboard {
    Scanner scanner = new Scanner(System.in);

    PrintUtils printUtils = new PrintUtils();

    public void viewAvailableRooms(Organizer organizer, RoomDatabase roomDatabase, EventDatabase eventDatabase, CategoryDatabase categoryDatabase) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : roomDatabase.getRooms()) {
            if (!room.getAvailableTimes().isEmpty()) {
                availableRooms.add(room);
                printUtils.printRoom(room);
            }
        }

        if (!availableRooms.isEmpty()) {
            // something
            promptCreateEvent(organizer, availableRooms, eventDatabase, categoryDatabase);
        }
    }

    public void viewYourEvents(Organizer organizer, EventDatabase eventDatabase) {
        for (Event event : eventDatabase.getEvents()) {
            if (event.getOrganizer() == organizer) {
                printUtils.printEvent(event);
            }
        }
    }

    private void promptCreateEvent(Organizer organizer, List<Room> availableRooms, EventDatabase eventDatabase, CategoryDatabase categoryDatabase) {
        System.out.println("Want to create a new event? y/n");
        String selection = scanner.nextLine();

        switch (selection) {
            case "y":
                createEvent(organizer, availableRooms, eventDatabase, categoryDatabase);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection, please try again!");
                promptCreateEvent(organizer, availableRooms, eventDatabase, categoryDatabase);
        }
    }

    private void createEvent(Organizer organizer, List<Room> availableRooms, EventDatabase eventDatabase, CategoryDatabase categoryDatabase) {
        System.out.print("Please input Event Name: ");
        String eventName = scanner.nextLine();

        Date date = askForEventDate();

        Room room = askForRoom(availableRooms);

        Category category = askForCategory(categoryDatabase);

        Event event = new Event(eventName, date, organizer, room, category);
        room.addEvent(event);
        eventDatabase.addEvent(event);
    }

    private Date askForEventDate() {
        System.out.print("Please enter Event date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        try {
            return Utils.DateUtils.asDate(LocalDate.parse(date));
        }
        catch (Exception e) {
            System.out.println("Invalid Date of Birth!");
            return askForEventDate();
        }
    }

    private Room askForRoom(List<Room> availableRooms) {
        System.out.print("Please select Room number: ");
        for (int i = 1; i < availableRooms.size() + 1; i++) {
            System.out.println(i + ". " + availableRooms.get(i));
        }

        int roomNumber = scanner.nextInt();

        if (roomNumber > 0 && roomNumber < availableRooms.size()) {
            return availableRooms.get(roomNumber - 1);
        }
        else {
            System.out.println("Invalid Room number!");
            return askForRoom(availableRooms);
        }
    }

    private Category askForCategory(CategoryDatabase categoryDatabase) {
        System.out.print("Please select Category number: ");
        for (int i = 1; i < categoryDatabase.getCategories().size(); i++) {
            Category category = categoryDatabase.getCategories().get(i);
            System.out.println(i + ". " + category.getName());
        }

        int selectedCategory = scanner.nextInt();

        if (selectedCategory > 0 && selectedCategory < categoryDatabase.getCategories().size()) {
            return categoryDatabase.getCategories().get(selectedCategory - 1);
        }
        else {
            System.out.println("Invalid Category number!");
            return askForCategory(categoryDatabase);
        }
    }
}
