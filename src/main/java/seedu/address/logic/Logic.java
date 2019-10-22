package seedu.address.logic;

import java.nio.file.Path;
import java.util.Date;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyIncidentManager;
import seedu.address.model.incident.Incident;
import seedu.address.model.person.Person;
import seedu.address.model.vehicle.Vehicle;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the IncidentManager.
     *
     * @see seedu.address.model.Model#getIncidentManager()
     */
    ReadOnlyIncidentManager getIncidentManager();

    /** Returns the current person logged in */
    Person getLoggedInPerson();

    /** Returns the login time */
    Date getLoginTime();

    /** Returns true if currently in person view mode */
    boolean isPersonView();

    /** Toggles the current view mode */
    void isPersonView(boolean isPersonView);

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of incidents */
    ObservableList<Incident> getFilteredIncidentList();

    /** Returns an unmodifiable view of the filtered list of vehicles */
    ObservableList<Vehicle> getFilteredVehicleList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getIncidentManagerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
