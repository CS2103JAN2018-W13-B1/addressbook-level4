package systemtests;

import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.DeleteGroupCommand.MESSAGE_DELETE_GROUP_SUCCESS;
import static seedu.address.logic.commands.DeleteGroupCommand.MESSAGE_GROUP_NOT_FOUND;
import static seedu.address.testutil.TypicalGroups.BUDDIES;
import static seedu.address.testutil.TypicalGroups.COLLEAGUES;
import static seedu.address.testutil.TypicalGroups.FRIENDS;
import static seedu.address.testutil.TypicalPersons.KEYWORD_MATCHING_MEIER;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.tag.Group;
import seedu.address.model.tag.exceptions.GroupNotFoundException;

public class DeleteGroupCommandSystemTest extends AddressBookSystemTest {

    private static final String MESSAGE_INVALID_DELETE_COMMAND_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteGroupCommand.MESSAGE_USAGE);

    @Test
    public void deleteGroup() {
        /* ----------------- Performing delete group operation while an unfiltered list is being shown -------------------- */

        /* Case: delete the group "colleagues" in the list, command with leading spaces and trailing spaces -> deleted */
        Model expectedModel = getModel();
        Model modelBeforeDeletingLast = getModel();
        String command = "     " + DeleteGroupCommand.COMMAND_WORD + "      " + COLLEAGUES.tagName + "       ";
        Group deletedGroup = COLLEAGUES;
        String expectedResultMessage = String.format(MESSAGE_DELETE_GROUP_SUCCESS, deletedGroup);
        assertCommandSuccess(command, expectedModel, expectedResultMessage);

        /* Case: undo deleting the group "colleagues" in the list -> group "colleagues" restored */
        command = UndoCommand.COMMAND_WORD;
        expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, modelBeforeDeletingLast, expectedResultMessage);

        /* Case: redo deleting the group "colleagues" in the list -> "colleagues" deleted again */
        command = RedoCommand.COMMAND_WORD;
        removeGroup(modelBeforeDeletingLast, COLLEAGUES);
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, modelBeforeDeletingLast, expectedResultMessage);

        /* ------------------ Performing delete operation while a filtered list is being shown ---------------------- */

        /* Case: filtered person list, delete existing group but not in filtered person list -> deleted */
        showPersonsWithName(KEYWORD_MATCHING_MEIER);
        Group groupToDelete = COLLEAGUES;
        command = DeleteGroupCommand.COMMAND_WORD + " " + COLLEAGUES.tagName;
        expectedResultMessage = String.format(MESSAGE_DELETE_GROUP_SUCCESS, deletedGroup);
        assertCommandSuccess(command, expectedModel, expectedResultMessage);

        /* Case: filtered person list, delete non-existing group in address book -> rejected */
        showPersonsWithName(KEYWORD_MATCHING_MEIER);
        Group invalidGroup = BUDDIES;
        command = DeleteGroupCommand.COMMAND_WORD + " " + invalidGroup;
        assertCommandFailure(command, MESSAGE_GROUP_NOT_FOUND);

    }

    private void removeGroup(Model model, Group colleagues) {
        try {
            model.deleteGroup(colleagues);
        } catch (GroupNotFoundException gnfe) {
            throw new AssertionError("Colleagues group should exist in address book.");
        }
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays an empty string.<br>
     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the model related components equal to {@code expectedModel}.<br>
     * 4. Asserts that the browser url and selected card remains unchanged.<br>
     * 5. Asserts that the status bar's sync status changes.<br>
     * 6. Asserts that the command box has the default style class.<br>
     * Verifications 1 to 3 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        assertCommandSuccess(command, expectedModel, expectedResultMessage, null);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String)} except that the browser url
     * and selected card are expected to update accordingly depending on the card at {@code expectedSelectedCardIndex}.
     * @see DeleteGroupCommandSystemTest#assertCommandSuccess(String, Model, String)
     * @see AddressBookSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage,
                                      Index expectedSelectedCardIndex) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);

        if (expectedSelectedCardIndex != null) {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        } else {
            assertSelectedCardUnchanged();
        }

        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the model related components equal to the current model.<br>
     * 4. Asserts that the browser url, selected card and status bar remain unchanged.<br>
     * 5. Asserts that the command box has the error style.<br>
     * Verifications 1 to 3 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}


