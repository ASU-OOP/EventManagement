package Dashboards;

import MiscObjects.Event;
import StaticResources.EventDatabase;
import Users.Attendee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AttendeeDashboard {
    Scanner scanner = new Scanner(System.in);

    public void showAttendingEvents(Attendee attendee, EventDatabase eventDatabase) {
        for (Event event : eventDatabase.getEvents()) {
            for (Attendee eventAttendee : event.getAttendees()) {
                if (eventAttendee.equals(attendee)) {
                    System.out.println(event.getName());
                }
            }
        }
    }

    public void showAttendableEvents(Attendee attendee, EventDatabase eventDatabase) {
        List<Event> attendableEvents = new ArrayList<>();
        for (Event event : eventDatabase.getEvents()) {
            for (Attendee eventAttendee : event.getAttendees()) {
                if (!eventAttendee.equals(attendee)) {
                    attendableEvents.add(event);
                }
            }
        }

        if (!attendableEvents.isEmpty()) {
            for (int i = 1; i < attendableEvents.size() + 1; i++) {
                System.out.println(i + ". " + attendableEvents.get(i).getName());
            }

            promptAttendEvent(attendee, attendableEvents, eventDatabase);
        }
    }

    private void promptAttendEvent(Attendee attendee, List<Event> attendableEvents, EventDatabase eventDatabase) {
        System.out.println("Want to attend one of these events? y/n");
        String selection = scanner.nextLine();

        switch (selection) {
            case "y":
                attendEvent(attendee, attendableEvents);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection, please try again!");
                promptAttendEvent(attendee, attendableEvents, eventDatabase);
        }
    }

    private void attendEvent(Attendee attendee, List<Event> attendableEvents) {
        System.out.print("Please input Event Number: ");
        int eventNumber = scanner.nextInt();
        scanner.nextLine();

        if (eventNumber > 0 && eventNumber < attendableEvents.size()) {
            Event event = attendableEvents.get(eventNumber - 1);
            if (attendee.wallet.getBalance() >= event.getPrice()) {
                attendee.wallet.setBalance(attendee.wallet.getBalance() - event.getPrice());
                event.getOrganizer().setBalance(event.getOrganizer().getBalance() + event.getPrice());
                event.addAttendee(attendee);
            } else {
                System.out.println("Insufficient balance");
                attendEvent(attendee, attendableEvents);
            }
        }
        else {
            System.out.println("Invalid Event Number, please try again!");
            attendEvent(attendee, attendableEvents);
        }
    }
}
