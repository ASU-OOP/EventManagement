package Models.MiscObjects;

import java.time.LocalTime;

public class OpeningTimes {
    private LocalTime from;
    private LocalTime to;

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }
}
