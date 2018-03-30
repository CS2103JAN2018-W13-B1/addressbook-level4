package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.event.CalendarEntry;
import seedu.address.model.event.UniqueCalendarEntryList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A utility class containing a list of {@code CalendarEntry} objects to be used in tests.
 */
public class TypicalCalendarEvents {
    public static final CalendarEntry MEETING_BOSS = new CalendarEventBuilder()
            .withEventTitle("Meeting with boss")
            .withStartDate("06-06-2018")
            .withEndDate("06-06-2018")
            .withStartTime("10:00")
            .withEndTime("12:00").build();

    public static final CalendarEntry GET_STOCKS = new CalendarEventBuilder()
            .withEventTitle("Get stocks from supplier")
            .withStartDate("01-07-2018")
            .withEndDate("01-07-2018")
            .withStartTime("08:00")
            .withEndTime("12:30").build();

    public static final CalendarEntry ROAD_SHOW = new CalendarEventBuilder()
            .withEventTitle("Road Show at Orchard")
            .withStartDate("02-05-2018")
            .withEndDate("06-05-2018")
            .withStartTime("09:00")
            .withEndTime("19:00").build();

    public static final CalendarEntry WORKSHOP = new CalendarEventBuilder()
            .withEventTitle("Workshop")
            .withStartDate("28-05-2018")
            .withEndDate("29-05-2018")
            .withStartTime("10:00")
            .withEndTime("15:00").build();

    private TypicalCalendarEvents() {} // prevents instantiation


    public static AddressBook getTypicalAddressBookWithEvents() {
        AddressBook ab = new AddressBook();

        try {
            ab.addPerson(ALICE);
        } catch (DuplicatePersonException dpe) {
            throw new AssertionError("not possible");
        }

        for (CalendarEntry calEvent : getTypicalCalendarEvents()) {
            try {
                ab.addCalendarEntry(calEvent);
            } catch (UniqueCalendarEntryList.DuplicateCalendarEntryException dcee) {
                throw new AssertionError("not possible");
            }
        }
        return ab;
    }

    public static List<CalendarEntry> getTypicalCalendarEvents() {
        return new ArrayList<>(Arrays.asList(MEETING_BOSS, GET_STOCKS, ROAD_SHOW, WORKSHOP));
    }
}
