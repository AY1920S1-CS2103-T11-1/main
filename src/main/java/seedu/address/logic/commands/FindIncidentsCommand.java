package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.incident.DescriptionKeywordsPredicate;
import seedu.address.model.incident.IdKeywordsPredicate;
import seedu.address.model.incident.Incident;
import seedu.address.model.incident.NameKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;


/**
 * Finds and lists all incidents in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindIncidentsCommand extends Command {

    public static final String COMMAND_WORD = "find-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches all incidents for which IDs match exactly "
            + "or description contains the first specified keyword under the relevant parameter and displays them as "
            + "a list "
            + "with index numbers.\n"
            + "Parameters: "
            + SEARCH_PREFIX_ID + "<ID> OR "
            + SEARCH_PREFIX_OPERATOR + "<OPERATOR> OR "
            + SEARCH_PREFIX_DESCRIPTION + "<KEYWORD [MORE_KEYWORDS]...> OR "
            + SEARCH_PREFIX_SELF + "\n"
            + "Example: " + COMMAND_WORD + " "
            + SEARCH_PREFIX_OPERATOR + "alex";

    private Predicate<Incident> predicate;
    private boolean isSelfSearch = false;

    public FindIncidentsCommand(DescriptionKeywordsPredicate descriptionPredicate) {
        this.predicate = descriptionPredicate;
    }

    public FindIncidentsCommand(IdKeywordsPredicate idPredicate) {
        this.predicate = idPredicate;
    }

    public FindIncidentsCommand(NameKeywordsPredicate namePredicate) {
        this.predicate = namePredicate;
    }

    public FindIncidentsCommand(Prefix prefix) {
        if (prefix == SEARCH_PREFIX_SELF) {
            this.isSelfSearch = true;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isSelfSearch) {
            // quick fix to allow searching using first name. when full name is allowed in search, change this
            Name operatorName = new Name(model.getLoggedInPerson().getName().fullName.split(" ", 2)[0]);
            predicate = new NameKeywordsPredicate(operatorName);
        }
        model.updateFilteredIncidentList(predicate);
        if (model.getFilteredIncidentList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_NO_INCIDENTS_FOUND);
        } else if (model.getFilteredIncidentList().size() == 1) {
            return new CommandResult(Messages.MESSAGE_SINGLE_INCIDENT_LISTED);
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_INCIDENTS_LISTED_OVERVIEW, model.getFilteredIncidentList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindIncidentsCommand // instanceof handles nulls
                && predicate.equals(((FindIncidentsCommand) other).predicate)); // state check
    }
}
