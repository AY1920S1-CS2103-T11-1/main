package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INCIDENTS;

import seedu.address.model.Model;

/**
 * Lists all incidents in the address book to the user.
 */
public class ListIncidentsCommand extends Command {

    public static final String COMMAND_WORD = "list-i";

    public static final String MESSAGE_SUCCESS = "Listed all incidents";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIncidentList(PREDICATE_SHOW_ALL_INCIDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
