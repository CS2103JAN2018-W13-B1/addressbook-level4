package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ChangeOrderStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ChangeOrderStatusCommand object.
 */
public class ChangeOrderStatusCommandParser {
    private static final int ORDER_STATUS_ARG_INDEX = 1;

    /**
     * Parses the given {@code String} of arguments in the context of the ChangeOrderStatusCommand
     * and returns an ChangeOrderStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ChangeOrderStatusCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            String orderStatus = args.split("\\s+")[ORDER_STATUS_ARG_INDEX];
            return new ChangeOrderStatusCommand(index, orderStatus);
        } catch (ArrayIndexOutOfBoundsException | IllegalValueException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeOrderStatusCommand.MESSAGE_USAGE)
            );
        }
    }
}
