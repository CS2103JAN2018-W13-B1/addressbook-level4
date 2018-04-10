package seedu.address.logic.parser;
//@@author SuxianAlicia
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GroupsContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindGroupCommand object
 */
public class FindGroupCommandParser implements Parser<FindGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindGroupCommand
     * and returns an FindGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindGroupCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindGroupCommand.MESSAGE_USAGE));
        }

        String[] groupKeywords = trimmedArgs.split("\\s+");

        return new FindGroupCommand(new GroupsContainKeywordsPredicate(Arrays.asList(groupKeywords)));
    }
}
