package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.incident.DescriptionKeywordsPredicate;
import seedu.address.model.incident.IdKeywordsPredicate;
import seedu.address.model.incident.Incident;
import seedu.address.model.incident.NameKeywordsPredicate;


/**
 * Finds and lists all incidents in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchIncidentsCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches all incidents for which IDs match exactly "
            + "or description contains any of the specified keywords (case-insensitive) and displays them as a list "
            + "with index numbers.\n"
            + "Parameters: "
            + PREFIX_ID + "<ID> OR "
            + PREFIX_OPERATOR + "<OPERATOR> OR "
            + PREFIX_DESC + "<KEYWORD [MORE_KEYWORDS]...>\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OPERATOR + "alex";

    private final Predicate<Incident> predicate;

    public SearchIncidentsCommand(DescriptionKeywordsPredicate descriptionPredicate) {
        this.predicate = descriptionPredicate;
    }

    public SearchIncidentsCommand(IdKeywordsPredicate idPredicate) {
        this.predicate = idPredicate;
    }

    public SearchIncidentsCommand(NameKeywordsPredicate namePredicate) {
        this.predicate = namePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIncidentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INCIDENTS_LISTED_OVERVIEW, model.getFilteredIncidentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchIncidentsCommand // instanceof handles nulls
                && predicate.equals(((SearchIncidentsCommand) other).predicate)); // state check
    }
}
