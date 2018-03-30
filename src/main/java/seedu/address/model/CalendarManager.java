package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import com.calendarfx.model.Calendar;

import javafx.collections.ObservableList;
import seedu.address.commons.util.CalendarUtil;
import seedu.address.model.event.CalendarEntry;
import seedu.address.model.event.UniqueCalendarEntryList;
import seedu.address.model.event.exceptions.DuplicateCalendarEntryException;

/**
 * Manages {@code Calendar} as defined in CalendarFX and its related data.
 */
public class CalendarManager implements ReadOnlyCalendarManager {
    private final Calendar calendar;
    private final UniqueCalendarEntryList calendarEntryList = new UniqueCalendarEntryList();

    public CalendarManager() {
        calendar = new Calendar();
    }

    public CalendarManager(ReadOnlyCalendarManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code CalendarManager} with {@code newData}.
     */
    private void resetData(ReadOnlyCalendarManager newData) {
        requireNonNull(newData);

        List<CalendarEntry> calEntries = new ArrayList<>(newData.getCalendarEntryList());

        try {
            setCalendarEntries(calEntries);
        } catch (DuplicateCalendarEntryException dcee) {
            throw new AssertionError("Calendar Manager should not have duplicate calendar entries.");
        }
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

    // Managing CalendarEntries operations

    /**
     * Adds a calendar entries to list of calendar entries in address book.
     * @throws DuplicateCalendarEntryException
     * if there exist an equivalent calendar event in address book.
     */
    public void addCalendarEntry(CalendarEntry toAdd) throws DuplicateCalendarEntryException {
        calendarEntryList.add(toAdd);
        calendar.addEntries(CalendarUtil.convertToEntry(toAdd));
    }


}
