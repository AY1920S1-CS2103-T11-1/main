package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALLER_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.incident.CallerNumber;
import seedu.address.model.incident.Description;

/**
 * Parses input arguments and creates a new FillCommand object
 */
public class FillCommandParser implements Parser<FillCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FillCommand
     * and returns a FillCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FillCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_CALLER_NUMBER,
                PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX, PREFIX_CALLER_NUMBER, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FillCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        CallerNumber callerNumber = ParserUtil.parseCallerNumber(argMultimap.getValue(PREFIX_CALLER_NUMBER).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        return new FillCommand(index, callerNumber, description);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
