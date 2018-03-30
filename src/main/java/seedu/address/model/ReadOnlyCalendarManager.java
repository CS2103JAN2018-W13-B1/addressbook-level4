package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.CalendarEntry;

/**
 * Unmodifiable view of an calendar manager.
 */
public interface ReadOnlyCalendarManager {

    /**
     * Returns an unmodifiable view of the calendar events list.
     * This list will not contain any duplicate calendar events.
     */
    ObservableList<CalendarEntry> getCalendarEntryList();
}
