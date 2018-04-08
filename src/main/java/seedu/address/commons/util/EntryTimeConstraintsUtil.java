package seedu.address.commons.util;

import static java.time.temporal.ChronoUnit.MINUTES;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EndDate;
import seedu.address.model.event.EndTime;
import seedu.address.model.event.StartDate;
import seedu.address.model.event.StartTime;

/**
 * Helper functions for checking StartDate, EndDate, StartTime, EndTime of {@code CalendarEntry}.
 */
public class EntryTimeConstraintsUtil {

    public static final String EVENT_DURATION_CONSTRAINTS =
            "Event must last at least 15 minutes if ending in same day."; //Constraint of CalendarFX entries
    public static final String STANDARD_START_TIME = "00:00"; //Start Time of event if StartTime not given
    public static final String START_AND_END_DATE_CONSTRAINTS = "Start Date cannot be later than End Date.";
    public static final String START_AND_END_TIME_CONSTRAINTS =
            "Start Time cannot be later than End Time if Event ends on same date.";

    private static final int MINIMAL_DURATION = 15; //Constraint of CalendarFX entries

    /**
     * Returns true if duration between start time and end time is less than 15 minutes.
     * This is a constraint that CalendarFX has. Event duration must last at least 15 minutes.
     */
    private static boolean eventIsShorterThanFifteenMinutes(StartTime startTime, EndTime endTime) {
        requireAllNonNull(startTime, endTime);
        if (MINUTES.between(startTime.getLocalTime(), endTime.getLocalTime()) < MINIMAL_DURATION) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if given start time is later than end time.
     * Start time cannot be later than End time if event ends on the same date.
     */
    private static boolean startTimeLaterThanEndTime(StartTime startTime, EndTime endTime) {
        requireAllNonNull(startTime, endTime);
        return startTime.getLocalTime().isAfter(endTime.getLocalTime());
    }

    /**
     * Returns true if given start date is later than end date.
     * Start Date cannot be later than End Date as it violates the meaning of the terms.
     */
    private static boolean startDateLaterThanEndDate(StartDate startDate, EndDate endDate) {
        requireAllNonNull(startDate, endDate);
        return startDate.getLocalDate().isAfter(endDate.getLocalDate());
    }

    public static void checkCalendarEntryTimeConstraints
            (StartDate startDate, EndDate endDate, StartTime startTime, EndTime endTime) throws IllegalValueException {

        if (startDateLaterThanEndDate(startDate, endDate)) {
            throw new IllegalValueException(START_AND_END_DATE_CONSTRAINTS);
        }

        if (startDate.toString().equals(endDate.toString())) {
            if (startTimeLaterThanEndTime(startTime, endTime)) {
                throw new IllegalValueException(START_AND_END_TIME_CONSTRAINTS);
            }

            if (eventIsShorterThanFifteenMinutes(startTime, endTime)) {
                throw new IllegalValueException(EVENT_DURATION_CONSTRAINTS);
            }
        }
    }
}
