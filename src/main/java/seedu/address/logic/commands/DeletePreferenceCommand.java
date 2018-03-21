package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Preference;
import seedu.address.model.tag.exceptions.PreferenceNotFoundException;

public class DeletePreferenceCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "deletepref";
    public static final String COMMAND_ALIAS = "dp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified preference from all persons in address book.\n"
            + "Parameters: PREFERENCE_NAME (must be alphanumeric)\n"
            + "Example: " + COMMAND_WORD + " computers";

    public static final String MESSAGE_DELETE_PREFERENCE_SUCCESS = "Deleted PREFERENCE: %1$s";
    public static final String MESSAGE_PREFERENCE_NOT_FOUND = "Preference does not exist in address book.";

    private Preference prefToDelete;

    public DeletePreferenceCommand(Preference targetPref) {
        this.prefToDelete = targetPref;
    }

    @Override
    protected CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(prefToDelete);
        try {
            model.deletePreference(prefToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PREFERENCE_SUCCESS, prefToDelete));
        } catch (PreferenceNotFoundException e) {
            throw new CommandException(MESSAGE_PREFERENCE_NOT_FOUND);
        }
    }
}
