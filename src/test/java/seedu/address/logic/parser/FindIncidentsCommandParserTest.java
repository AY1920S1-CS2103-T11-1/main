package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.SEARCH_PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindIncidentsCommand;
import seedu.address.model.incident.Description;
import seedu.address.model.incident.DescriptionKeywordsPredicate;

public class FindIncidentsCommandParserTest {

    private FindIncidentsCommandParser parser = new FindIncidentsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindIncidentsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindIncidentsCommand expectedSearchIncidentsCommand =
                new FindIncidentsCommand(new DescriptionKeywordsPredicate(new Description("test")));
        assertParseSuccess(parser, " " + SEARCH_PREFIX_DESCRIPTION + "test", expectedSearchIncidentsCommand);
    }

}
