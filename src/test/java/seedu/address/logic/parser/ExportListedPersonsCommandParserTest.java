package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ExportListedPersonsCommandParser.MESSAGE_FILENAME_CONSTRAINTS;
//import static seedu.address.logic.parser.ExportListedPersonsCommandParser.MESSAGE_FILE_ALREADY_EXISTS;

//import java.io.File;

import org.junit.Test;

//import seedu.address.logic.commands.ExportListedPersonsCommand;

public class ExportListedPersonsCommandParserTest {

    private ExportListedPersonsCommandParser parser = new ExportListedPersonsCommandParser();

    /*
    @Test
    public void parse_validArgs_returnsExportListedPersonsCommand() {
        assertParseSuccess(parser, "aaa", new ExportListedPersonsCommand("aaa.csv"));
    }
    */

    @Test
    public void parse_invalidArgsWrongFormat_throwsParseException() {
        assertParseFailure(parser, "aaa!", MESSAGE_FILENAME_CONSTRAINTS);
    }

    @Test
    public void parse_invalidArgsTooLong_throwsParseException() {
        assertParseFailure(parser, "123451234512345123451234512345X", MESSAGE_FILENAME_CONSTRAINTS);
    }

    /*
    @Test
    public void parse_fileExists_throwsParseException() {
        String tempFilename = "aaa";
        File tempPath = new File(tempFilename + ".csv");
        assertParseFailure(parser, "aaa", MESSAGE_FILE_ALREADY_EXISTS);
        tempPath.delete();
    }
    */

}
