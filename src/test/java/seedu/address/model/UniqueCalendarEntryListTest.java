package seedu.address.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalCalendarEntries.GET_STOCKS;
import static seedu.address.testutil.TypicalCalendarEntries.MEETING_BOSS;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.event.UniqueCalendarEntryList;
import seedu.address.model.event.exceptions.DuplicateCalendarEntryException;

public class UniqueCalendarEntryListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void equals() throws DuplicateCalendarEntryException {
        UniqueCalendarEntryList firstEventsList =  new UniqueCalendarEntryList();
        firstEventsList.add(MEETING_BOSS);
        UniqueCalendarEntryList secondEventsList = new UniqueCalendarEntryList();
        secondEventsList.add(GET_STOCKS);

        // Same object -> True
        assertTrue(firstEventsList.equals(firstEventsList));

        // different type -> false
        assertFalse(firstEventsList.equals(1));

        // different objects, same type -> false
        assertFalse(firstEventsList.equals(secondEventsList));
    }

    @Test
    public void asOrderInsensitiveList_compareListsWithSameItemsInDiffOrder_assertEqual()
            throws DuplicateCalendarEntryException {

        UniqueCalendarEntryList firstEventsList =  new UniqueCalendarEntryList();
        firstEventsList.add(MEETING_BOSS);
        firstEventsList.add(GET_STOCKS);
        UniqueCalendarEntryList secondEventsList = new UniqueCalendarEntryList();
        secondEventsList.add(GET_STOCKS);
        secondEventsList.add(MEETING_BOSS);

        assertTrue(firstEventsList.equalsOrderInsensitive(secondEventsList));
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueCalendarEntryList calendarEventList = new UniqueCalendarEntryList();
        thrown.expect(UnsupportedOperationException.class);
        calendarEventList.asObservableList().remove(0);
    }

    @Test
    public void asUniqueList_addDuplicateCalendarEvents_throwsDuplicateCalendarEventException()
            throws DuplicateCalendarEntryException {

        UniqueCalendarEntryList calendarEventList = new UniqueCalendarEntryList();
        thrown.expect(DuplicateCalendarEntryException.class);
        calendarEventList.add(MEETING_BOSS);
        calendarEventList.add(MEETING_BOSS);
    }
}
