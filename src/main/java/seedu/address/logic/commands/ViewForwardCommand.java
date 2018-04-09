package seedu.address.logic.commands;
//@@author SuxianAlicia
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ChangeCalendarPageRequestEvent;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Switches current page of Calendar to next page.
 * Depending on the current viewing format of Calendar, the next page can be the next day, next week
 * or next month of the current displayed date.
 * This command will display the calendar if it is not displayed when command is executed.
 */
public class ViewForwardCommand extends Command {
    public static final String COMMAND_WORD = "calendarforward";
    public static final String COMMAND_ALIAS = "calforward";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays next page of current displayed date in calendar.\n"
            + "Depending on the current viewing format of Calendar, the next page can be the next day,"
            + " next week or next month of the current displayed date.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEW_CALENDAR_FORWARD_SUCCESS = "Displayed next page in Calendar.";
    public static final String REQUEST_FORWARD = "Forward";

    @Override
    public CommandResult execute() throws CommandException {
        EventsCenter.getInstance().post(new ChangeCalendarPageRequestEvent(REQUEST_FORWARD));
        return new CommandResult(MESSAGE_VIEW_CALENDAR_FORWARD_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewBackCommand); // instanceof handles nulls
    }
}
