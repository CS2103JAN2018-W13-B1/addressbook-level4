package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.calendarfx.model.Calendar;

import javafx.collections.ObservableList;
import seedu.address.commons.util.CalendarUtil;
import seedu.address.model.event.CalendarEntry;
import seedu.address.model.event.UniqueCalendarEntryList;
import seedu.address.model.event.exceptions.CalendarEntryNotFoundException;
import seedu.address.model.event.exceptions.DuplicateCalendarEntryException;

/**
 * Manages {@code Calendar} as defined in CalendarFX and its related data.
 */
public class CalendarManager implements ReadOnlyCalendarManager {
    private final Calendar calendar;
    private final UniqueCalendarEntryList calendarEntryList;

    public CalendarManager() {
        calendarEntryList = new UniqueCalendarEntryList();
        calendar = new Calendar();
        calendar.setStyle(Calendar.Style.STYLE1);
    }

    public CalendarManager(ReadOnlyCalendarManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code CalendarManager} with {@code newData}.
     */
    public void resetData(ReadOnlyCalendarManager newData) {
        requireNonNull(newData);

        List<CalendarEntry> calEntries = new ArrayList<>(newData.getCalendarEntryList());

        try {
            setCalendarEntries(calEntries);
        } catch (DuplicateCalendarEntryException dcee) {
            throw new AssertionError("Calendar Manager should not have duplicate calendar entries.");
        }
        updateCalendar();
    }

    /**
     * Updates Calendar with entries converted from {@code calendarEntryList}.
     */
    private void updateCalendar() {
        calendar.clear();
        calendar.addEntries(CalendarUtil.convertEntireListToEntries(calendarEntryList.asObservableList()));
    }

    /**
     * Sets {@code calendarEntryList} to match the given list of calendar entries.
     */
    private void setCalendarEntries(List<CalendarEntry> calEntries)
            throws DuplicateCalendarEntryException {
        calendarEntryList.setCalEntryList(calEntries);
    }

    @Override
    public ObservableList<CalendarEntry> getCalendarEntryList() {
        return calendarEntryList.asObservableList();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    // Managing CalendarEntries operations

    /**
     * Adds a calendar entries to list of calendar entries in calendar manager.
     * @throws DuplicateCalendarEntryException
     * if there exist an equivalent calendar entry in calendar manager.
     */
    public void addCalendarEntry(CalendarEntry toAdd) throws DuplicateCalendarEntryException {
        calendarEntryList.add(toAdd);
        updateCalendar();
    }

    /**
     * Removes an existing calendar entry in list of calendar entries and from the calendar itself.
     * @throws CalendarEntryNotFoundException
     * if given calendar entry does not exist in list of calendar entry
     */
    public void deleteCalendarEntry(CalendarEntry entryToDelete) throws CalendarEntryNotFoundException {
        if (!calendarEntryList.remove(entryToDelete)) {
            throw new CalendarEntryNotFoundException();
        } else {
            updateCalendar();
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarManager // instanceof handles nulls
                && this.calendarEntryList.equals(((CalendarManager) other).calendarEntryList));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(calendar, calendarEntryList);
    }


}