package seedu.address.testutil;

import seedu.address.model.event.CalendarEntry;
import seedu.address.model.event.EndDate;
import seedu.address.model.event.EndTime;
import seedu.address.model.event.EntryTitle;
import seedu.address.model.event.StartDate;
import seedu.address.model.event.StartTime;

/**
 * A utility class to help with building CalendarEntry objects.
 */
public class CalendarEventBuilder {

    public static final String DEFAULT_EVENT_TITLE = "Meeting with boss";
    public static final String DEFAULT_START_DATE = "10-10-2018";
    public static final String DEFAULT_END_DATE = "10-10-2018";
    public static final String DEFAULT_START_TIME = "10:00";
    public static final String DEFAULT_END_TIME = "12:00";

    private EntryTitle entryTitle;
    private StartDate startDate;
    private EndDate endDate;
    private StartTime startTime;
    private EndTime endTime;

    public CalendarEventBuilder() {
        entryTitle = new EntryTitle(DEFAULT_EVENT_TITLE);
        startDate = new StartDate(DEFAULT_START_DATE);
        endDate = new EndDate(DEFAULT_END_DATE);
        startTime = new StartTime(DEFAULT_START_TIME);
        endTime = new EndTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the CalendarEventBuilder with the data of {@code eventToCopy}.
     */
    public CalendarEventBuilder(CalendarEntry eventToCopy) {
        entryTitle = eventToCopy.getEntryTitle();
        startDate = eventToCopy.getStartDate();
        endDate = eventToCopy.getEndDate();
        startTime = eventToCopy.getStartTime();
        endTime = eventToCopy.getEndTime();
    }

    /**
     * Sets the {@code EntryTitle} of the {@code CalendarEntry} that we are building.
     */
    public CalendarEventBuilder withEntryTitle(String eventTitle) {
        this.entryTitle = new EntryTitle(eventTitle);
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code CalendarEntry} that we are building.
     */
    public CalendarEventBuilder withStartDate(String startDate) {
        this.startDate = new StartDate(startDate);
        return this;
    }

    /**
     * Sets the {@code EndDate} of the {@code CalendarEntry} that we are building.
     */
    public CalendarEventBuilder withEndDate(String endDate) {
        this.endDate = new EndDate(endDate);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code CalendarEntry} that we are building.
     */
    public CalendarEventBuilder withStartTime(String startTime) {
        this.startTime = new StartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code CalendarEntry} that we are building.
     */
    public CalendarEventBuilder withEndTime(String endTime) {
        this.endTime = new EndTime(endTime);
        return this;
    }

    public CalendarEntry build() {
        return new CalendarEntry(entryTitle, startDate, endDate, startTime, endTime);
    }
}
