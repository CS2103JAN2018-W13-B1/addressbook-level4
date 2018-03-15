package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Group;
import seedu.address.model.tag.Preference;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Group> getGroupList();

    ObservableList<Preference> getPreferenceList();
}
