package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.vehicle.Availability;
import seedu.address.model.vehicle.Vehicle;

/**
 * Deletes a vehicle identified using its index in the IMS.
 */
public class DeleteVehicleCommand extends Command {

    public static final String COMMAND_WORD = "delete-v";
    public static final String MESSAGE_USAGE = "";

    public static final String MESSAGE_DELETE_VEHICLE_SUCCESS = "Deleted Vehicle %1$s";
    public static final String MESSAGE_DELETE_VEHICLE_ERROR = "You cannot delete a vehicle that is currently "
            + "dispatched.";
    private Index index;

    public DeleteVehicleCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Vehicle> listOfVehicles = model.getFilteredVehicleList();
        if (index.getZeroBased() >= listOfVehicles.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VEHICLE_INDEX);
        }
        Vehicle toDelete = listOfVehicles.get(index.getZeroBased());

        if (toDelete.getAvailability().equals(new Availability("Busy"))) {
            throw new CommandException(MESSAGE_DELETE_VEHICLE_ERROR);
        }

        model.deleteVehicle(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_VEHICLE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteVehicleCommand)) {
            return false;
        }

        return ((DeleteVehicleCommand) other).index.equals(this.index);
    }
}
