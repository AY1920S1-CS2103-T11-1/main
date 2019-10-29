package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISTRICT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.incident.Incident;
import seedu.address.model.person.Person;
import seedu.address.model.vehicle.District;

/**
 * Generates a new incident report.
 */
public class NewCommand extends Command {

    public static final String COMMAND_WORD = "new";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Drafts an incident report to the incident management "
            + "system." + "Parameters: "
            + PREFIX_DISTRICT + "DISTRICT NUMBER "
            + PREFIX_AUTO + "[y/n]";

    public static final String MESSAGE_SUCCESS = "New incident drafted: %1$s";
    public static final String MESSAGE_DUPLICATE_REPORT = "This draft already exists in the incident "
            + "management system";

    private final District location;
    private final boolean isAuto;
    private Incident draft;

    /**
     * Creates a NewCommand to generate a new {@code Incident}
     */
    public NewCommand(District location, boolean isAuto) {
        requireNonNull(location);
        this.location = location;
        this.isAuto = isAuto;
    }

    /**
     * Assigns vehicle directly to draft if isAuto is true.
     * Else, lists nearby vehicles as per normal vehicle search.
     * @param draft
     * @param isAuto
     * @param model
     */
    public void dispatchVehicle(Incident draft, boolean isAuto, Model model) {
        FindVehiclesCommand findVehicle = new FindVehiclesCommand(draft, isAuto);
        findVehicle.execute(model);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person operator = model.getLoggedInPerson();
        Incident draft = new Incident(operator, location);
        this.draft = draft;

        if (model.hasIncident(draft)) {
            throw new CommandException(MESSAGE_DUPLICATE_REPORT);
        }

        dispatchVehicle(draft, isAuto, model);

        model.addIncident(draft);
        return new CommandResult(String.format(MESSAGE_SUCCESS, draft));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NewCommand // instanceof handles nulls
                && location.equals(((NewCommand) other).location))
                && draft.equals(((NewCommand) other).draft);
    }
}
