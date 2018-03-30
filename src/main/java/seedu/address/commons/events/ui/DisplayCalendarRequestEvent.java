package seedu.address.commons.events.ui;

import javafx.collections.ObservableList;
import seedu.address.commons.events.BaseEvent;
import seedu.address.model.event.CalendarEntry;

/**
 * Indicates request to display calendar.
 */
public class DisplayCalendarRequestEvent extends BaseEvent {

    private final ObservableList<CalendarEntry> calendarEntries;

    public DisplayCalendarRequestEvent(ObservableList<CalendarEntry> calendarEntries) {
        this.calendarEntries = calendarEntries;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public ObservableList<CalendarEntry> getCalendarEntries() {
        return calendarEntries;
    }

}
