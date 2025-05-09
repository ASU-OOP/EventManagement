package Models.MiscObjects;

import Models.Users.Attendee;
import Models.Users.Organizer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {
    String name;
    LocalDate date;
    Double price = 100.0;
    List<Attendee> attendees = new ArrayList<>();
    Organizer organizer;
    Room room;
    Category category;

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public Room getRoom() {
        return room;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public void addAttendee(Attendee attendee) {
        this.attendees.add(attendee);
    }

    public void removeAttendee(Attendee attendee) {
        this.attendees.remove(attendee);
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Event(String name, LocalDate date, Organizer organizer, Room room, Category category) {
        setName(name);
        setDate(date);
        setOrganizer(organizer);
        setRoom(room);
        setCategory(category);
    }
}
