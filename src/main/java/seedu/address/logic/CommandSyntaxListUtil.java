package seedu.address.logic;

import java.util.ArrayList;
import java.util.Collections;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.UndoCommand;

/**
 * Returns a list of command words.
 */
public final class CommandSyntaxListUtil {
    private static ArrayList<String> commandList;

    public static ArrayList<String> getCommandList() {
        commandList = new ArrayList<>();
        setCommandSyntaxList();
        return commandList;
    }

    private static void setCommandSyntaxList() {
        commandList.add(AddCommand.COMMAND_SYNTAX);
        commandList.add(ClearCommand.COMMAND_WORD);
        commandList.add(DeleteCommand.COMMAND_WORD);
        commandList.add(EditCommand.COMMAND_SYNTAX);
        commandList.add(ExitCommand.COMMAND_WORD);
        commandList.add(FindCommand.COMMAND_SYNTAX);
        commandList.add(HelpCommand.COMMAND_WORD);
        commandList.add(HistoryCommand.COMMAND_WORD);
        commandList.add(ListCommand.COMMAND_WORD);
        commandList.add(RedoCommand.COMMAND_WORD);
        commandList.add(SelectCommand.COMMAND_SYNTAX);
        commandList.add(UndoCommand.COMMAND_WORD);

        sortCommandList();
    }

    private static void sortCommandList() {
        Collections.sort(commandList);
    }
}
