package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;
import seedu.address.commons.util.CollectionUtil;

/**
 * A list of {@code CalendarEntry} that enforces no nulls and uniqueness between its elements.
 *
 * Supports minimal set of list operations for the app's features.
 *
 * @see CalendarEntry#equals(Object)
 */
public class UniqueCalendarEntryList implements Iterable<CalendarEntry> {
    private final ObservableList<CalendarEntry> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty UniqueCalendarEntryList.
     */
    public UniqueCalendarEntryList() {}

    /**
     * Creates a UniqueCalendarEntryList using given calendar events.
     * Enforces no nulls.
     */
    public UniqueCalendarEntryList(Set<CalendarEntry> calendarEntries) {
        requireAllNonNull(calendarEntries);
        internalList.addAll(calendarEntries);

        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Returns all orders in this list as a Set.
     * This set is mutable and change-insulated against the internal list.
     */
    public Set<CalendarEntry> toSet() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return new HashSet<>(internalList);
    }

    /**
     * Replaces the CalendarEntry in this list with those in the argument calendar event list.
     */
    public void setCalEvents(Set<CalendarEntry> calendarEntries) {
        requireAllNonNull(calendarEntries);
        internalList.setAll(calendarEntries);
        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Ensures every calendar event in the argument list exists in this object.
     */
    public void mergeFrom(UniqueCalendarEntryList from) {
        final Set<CalendarEntry> existingEvents = this.toSet();
        from.internalList.stream()
                .filter(calEvent -> !existingEvents.contains(calEvent))
                .forEach(internalList::add);

        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Returns true if the list contains an equivalent {@code CalendarEntry} as the given argument.
     */
    public boolean contains(CalendarEntry toCheck) {
        requireNonNull(toCheck);
        return internalList.contains(toCheck);
    }

    /**
     * Adds an CalendarEntry to the list.
     *
     * @throws DuplicateCalendarEventException if the CalendarEntry to add
     * is a duplicate of an existing CalendarEntry in the list.
     */
    public void add(CalendarEntry toAdd) throws DuplicateCalendarEventException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCalendarEventException();
        }
        internalList.add(toAdd);

        assert CollectionUtil.elementsAreUnique(internalList);
    }

    /**
     * Removes CalendarEntry from list if it exists.
     */
    public void remove(CalendarEntry toRemove) {
        requireNonNull(toRemove);
        if (contains(toRemove)) {
            internalList.remove(toRemove);
        }
    }

    @Override
    public Iterator<CalendarEntry> iterator() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return internalList.iterator();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<CalendarEntry> asObservableList() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public boolean equals(Object other) {
        assert CollectionUtil.elementsAreUnique(internalList);
        return other == this // short circuit if same object
                || (other instanceof UniqueCalendarEntryList // instanceof handles nulls
                && this.internalList.equals(((UniqueCalendarEntryList) other).internalList));
    }

    /**
     * Returns true if the element in this list is equal to the elements in {@code other}.
     * The elements do not have to be in the same order.
     */
    public boolean equalsOrderInsensitive(UniqueCalendarEntryList other) {
        assert CollectionUtil.elementsAreUnique(internalList);
        assert CollectionUtil.elementsAreUnique(other.internalList);
        return this == other || new HashSet<>(this.internalList).equals(new HashSet<>(other.internalList));
    }

    @Override
    public int hashCode() {
        assert CollectionUtil.elementsAreUnique(internalList);
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateCalendarEventException extends DuplicateDataException {
        public DuplicateCalendarEventException() {
            super("Operation would result in duplicate events");
        }
    }

}
