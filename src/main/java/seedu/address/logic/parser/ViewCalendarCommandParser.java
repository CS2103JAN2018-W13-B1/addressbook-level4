package seedu.address.logic.parser;

import seedu.address.logic.commands.ViewCalendarCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCalendarCommand object
 */
public class ViewCalendarCommandParser implements Parser<ViewCalendarCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCalendarCommand
     * and returns an ViewCalendarCommand object for execution.
     */
    @Override
    public ViewCalendarCommand parse(String userInput) throws ParseException {
        return new ViewCalendarCommand(userInput.trim());
    }
}
