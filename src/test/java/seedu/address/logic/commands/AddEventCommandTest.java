package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.calendarfx.model.Calendar;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.CalendarManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCalendarManager;
import seedu.address.model.event.CalendarEntry;
import seedu.address.model.event.exceptions.DuplicateCalendarEntryException;
import seedu.address.model.order.Order;
import seedu.address.model.order.UniqueOrderList;
import seedu.address.model.order.exceptions.OrderNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Group;
import seedu.address.model.tag.Preference;
import seedu.address.model.tag.exceptions.GroupNotFoundException;
import seedu.address.model.tag.exceptions.PreferenceNotFoundException;
import seedu.address.testutil.CalendarEventBuilder;

public class AddEventCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddEventCommand(null);
    }

    @Test
    public void execute_calendarEventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCalendarEventAdded modelStub = new ModelStubAcceptingCalendarEventAdded();
        CalendarEntry validEvent = new CalendarEventBuilder().build();

        CommandResult commandResult = getAddEventCommandForCalendarEvent(validEvent, modelStub).execute();

        assertEquals(String.format(AddEventCommand.MESSAGE_ADD_EVENT_SUCCESS, validEvent),
                commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validEvent), modelStub.calendarEventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() throws Exception {
        ModelStub modelStub = new ModelStubThrowingDuplicateCalendarEventException();
        CalendarEntry validEvent = new CalendarEventBuilder().build();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddEventCommand.MESSAGE_DUPLICATE_EVENT);

        getAddEventCommandForCalendarEvent(validEvent, modelStub).execute();
    }

    @Test
    public void equals() {
        CalendarEntry meetBoss = new CalendarEventBuilder().withEventTitle("Meeting with boss").build();
        CalendarEntry getSupplies = new CalendarEventBuilder().withEventTitle("Get supplies").build();
        AddEventCommand addMeetBossCommand = new AddEventCommand(meetBoss);
        AddEventCommand addGetSuppliesCommand = new AddEventCommand(getSupplies);

        // same object -> returns true
        assertTrue(addMeetBossCommand.equals(addMeetBossCommand));

        // same values -> returns true
        AddEventCommand addMeetBossCommandCopy = new AddEventCommand(meetBoss);
        assertTrue(addMeetBossCommand.equals(addMeetBossCommandCopy));

        // different types -> returns false
        assertFalse(addMeetBossCommand.equals(1));

        // null -> returns false
        assertFalse(addMeetBossCommand.equals(null));

        // different person -> returns false
        assertFalse(addMeetBossCommand.equals(addGetSuppliesCommand));
    }

    /**
     * Generates a new AddEventCommand with the details of the given calendar event.
     */
    private AddEventCommand getAddEventCommandForCalendarEvent(CalendarEntry calEvent, Model model) {
        AddEventCommand command = new AddEventCommand(calEvent);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addPerson(Person person) throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlyAddressBook newData, ReadOnlyCalendarManager newCalendarData) {
            fail("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void deletePerson(Person target) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updatePerson(Person target, Person editedPerson)
                throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public ObservableList<CalendarEntry> getFilteredCalendarEventList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public Calendar getCalendar() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public ReadOnlyCalendarManager getCalendarManager() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateOrder(Order target, Order editedOrder)
                throws UniqueOrderList.DuplicateOrderException, OrderNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void updateFilteredCalendarEventList(Predicate<CalendarEntry> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group targetGroup) throws GroupNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void deletePreference(Preference targetPreference) throws PreferenceNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void addOrderToOrderList(Order orderToAdd) {
            fail("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order targetOrder) throws OrderNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void addCalendarEntry(CalendarEntry toAdd)
                throws DuplicateCalendarEntryException {
            fail("This method should not be called.");
        }
    }

    /**
     * A Model stub that always throws a DuplicateCalendarEntryException when trying to add a calendar event.
     */
    private class ModelStubThrowingDuplicateCalendarEventException extends ModelStub {

        @Override
        public void addCalendarEntry(CalendarEntry toAdd)
                throws DuplicateCalendarEntryException {

            throw new DuplicateCalendarEntryException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ReadOnlyCalendarManager getCalendarManager() { return new CalendarManager(); }
    }


    /**
     * A Model stub that always accepts the calendarEvent being added.
     */
    private class ModelStubAcceptingCalendarEventAdded extends ModelStub {
        final ArrayList<CalendarEntry> calendarEventsAdded = new ArrayList<>();

        @Override
        public void addCalendarEntry(CalendarEntry calendarEntry)
                throws DuplicateCalendarEntryException {
            requireNonNull(calendarEntry);
            calendarEventsAdded.add(calendarEntry);
        }

        /* To fix later on */
        @Override
        public ObservableList<CalendarEntry> getFilteredCalendarEventList() {
            return null;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ReadOnlyCalendarManager getCalendarManager() { return new CalendarManager(); }
    }
}
