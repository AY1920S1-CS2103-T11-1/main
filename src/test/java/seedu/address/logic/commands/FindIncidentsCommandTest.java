package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INCIDENTS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_NO_INCIDENTS_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_SINGLE_INCIDENT_LISTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIncidents.FIFTH_INCIDENT;
import static seedu.address.testutil.TypicalIncidents.FIRST_INCIDENT;
import static seedu.address.testutil.TypicalIncidents.SECOND_INCIDENT;
import static seedu.address.testutil.TypicalIncidents.SEVENTH_INCIDENT;
import static seedu.address.testutil.TypicalIncidents.THIRD_INCIDENT;
import static seedu.address.testutil.TypicalIncidents.getTypicalIncidentManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.incident.DescriptionKeywordsPredicate;
import seedu.address.model.incident.IdKeywordsPredicate;
import seedu.address.model.incident.Incident;
import seedu.address.model.incident.NameKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindIncidentsCommand}.
 */
public class FindIncidentsCommandTest {
    //@@author Yoshi275

    private Model model = new ModelManager(getTypicalIncidentManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalIncidentManager(), new UserPrefs());

    @Test
    public void equals() {
        DescriptionKeywordsPredicate firstDescPredicate =
                new DescriptionKeywordsPredicate(List.of("fire"));
        DescriptionKeywordsPredicate secondDescPredicate =
                new DescriptionKeywordsPredicate(List.of("arson"));
        NameKeywordsPredicate firstOpNamePredicate =
                new NameKeywordsPredicate(List.of("bill"), true);
        IdKeywordsPredicate firstIncidentIdPredicate =
                new IdKeywordsPredicate("1220190001");

        List<Predicate<Incident>> firstDescPredicateArr = new ArrayList<>();
        firstDescPredicateArr.add(firstDescPredicate);
        List<Predicate<Incident>> secondDescPredicateArr = new ArrayList<>();
        secondDescPredicateArr.add(secondDescPredicate);
        List<Predicate<Incident>> firstOpNamePredicateArr = new ArrayList<>();
        firstOpNamePredicateArr.add(firstOpNamePredicate);
        List<Predicate<Incident>> firstIncidentIdPredicateArr = new ArrayList<>();
        firstIncidentIdPredicateArr.add(firstIncidentIdPredicate);

        FindIncidentsCommand findFirstDescCommand = new FindIncidentsCommand(firstDescPredicateArr);
        FindIncidentsCommand findSecondDescCommand = new FindIncidentsCommand(secondDescPredicateArr);
        FindIncidentsCommand findFirstOpNameCommand = new FindIncidentsCommand(firstOpNamePredicateArr);
        FindIncidentsCommand findFirstIdCommand = new FindIncidentsCommand(firstIncidentIdPredicateArr);

        // same object -> returns true
        assertTrue(findFirstDescCommand.equals(findFirstDescCommand));
        assertTrue(findFirstOpNameCommand.equals(findFirstOpNameCommand));
        assertTrue(findFirstIdCommand.equals(findFirstIdCommand));

        // same values -> returns true
        FindIncidentsCommand findFirstDescCommandCopy = new FindIncidentsCommand(firstDescPredicateArr);
        assertTrue(findFirstDescCommand.equals(findFirstDescCommandCopy));

        FindIncidentsCommand findFirstOpNameCommandCopy = new FindIncidentsCommand(firstOpNamePredicateArr);
        assertTrue(findFirstOpNameCommand.equals(findFirstOpNameCommandCopy));

        FindIncidentsCommand findFirstIdCommandCopy = new FindIncidentsCommand(firstIncidentIdPredicateArr);
        assertTrue(findFirstIdCommand.equals(findFirstIdCommandCopy));

        // different types of entries -> returns false
        assertFalse(findFirstDescCommand.equals(1));

        // different types of predicates -> returns false
        assertFalse(findFirstDescCommand.equals(findFirstOpNameCommand));
        assertFalse(findFirstOpNameCommand.equals(findFirstIdCommand));
        assertFalse(findFirstIdCommand.equals(findFirstDescCommand));

        // different description predicate -> returns false
        assertFalse(findFirstDescCommand.equals(findSecondDescCommand));

        // null -> returns false
        assertFalse(findFirstDescCommand.equals(null));
    }

    @Test
    public void execute_singleIdKeyword_noIncidentFound() {
        String expectedMessage = MESSAGE_NO_INCIDENTS_FOUND;
        IdKeywordsPredicate idKeywordsPredicate = prepareIdPredicate("123456789");
        Command command = new FindIncidentsCommand(Arrays.asList(idKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(idKeywordsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredIncidentList());
    }

    @Test
    public void execute_singleIdKeyword_singleIncidentFound() {
        String expectedMessage = MESSAGE_SINGLE_INCIDENT_LISTED;
        IdKeywordsPredicate idKeywordsPredicate = prepareIdPredicate("0120130001");
        Command command = new FindIncidentsCommand(Arrays.asList(idKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(idKeywordsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SECOND_INCIDENT), model.getFilteredIncidentList());
    }

    @Test
    public void execute_singleNameKeyword_noIncidentFound() {
        String expectedMessage = MESSAGE_NO_INCIDENTS_FOUND;
        NameKeywordsPredicate nameKeywordsPredicate = prepareNamePredicate("george", false);
        Command command = new FindIncidentsCommand(Arrays.asList(nameKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(nameKeywordsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredIncidentList());
    }

    @Test
    public void execute_singleNameKeyword_singleIncidentFound() {
        String expectedMessage = MESSAGE_SINGLE_INCIDENT_LISTED;
        NameKeywordsPredicate nameKeywordsPredicate = prepareNamePredicate("kurz", false);
        Command command = new FindIncidentsCommand(Arrays.asList(nameKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(nameKeywordsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIFTH_INCIDENT), model.getFilteredIncidentList());
    }

    @Test
    public void execute_singleNameKeyword_multipleIncidentsFound() {
        String expectedMessage = String.format(MESSAGE_INCIDENTS_LISTED_OVERVIEW, 2);
        NameKeywordsPredicate nameKeywordsPredicate = prepareNamePredicate("mEieR", false);
        Command command = new FindIncidentsCommand(Arrays.asList(nameKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(nameKeywordsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SEVENTH_INCIDENT, THIRD_INCIDENT), model.getFilteredIncidentList());
    }

    @Test
    public void execute_multipleNameKeywords_multipleIncidentsFound() {
        String expectedMessage = String.format(MESSAGE_INCIDENTS_LISTED_OVERVIEW, 3);
        NameKeywordsPredicate nameKeywordsPredicate = prepareNamePredicate("KurZ   mEieR", false);
        Command command = new FindIncidentsCommand(Arrays.asList(nameKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(nameKeywordsPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SEVENTH_INCIDENT, FIFTH_INCIDENT, THIRD_INCIDENT), model.getFilteredIncidentList());
    }

    @Test
    public void execute_singleDescriptionKeyword_noIncidentFound() {
        String expectedMessage = MESSAGE_NO_INCIDENTS_FOUND;
        DescriptionKeywordsPredicate descriptionPredicate = prepareDescriptionPredicate("geriatrician");
        Command command = new FindIncidentsCommand(Arrays.asList(descriptionPredicate));
        expectedModel.updateFilteredIncidentList(descriptionPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredIncidentList());
    }

    @Test
    public void execute_singleDescriptionKeyword_singleIncidentFound() {
        String expectedMessage = MESSAGE_SINGLE_INCIDENT_LISTED;
        DescriptionKeywordsPredicate descriptionPredicate = prepareDescriptionPredicate("park");
        Command command = new FindIncidentsCommand(Arrays.asList(descriptionPredicate));
        expectedModel.updateFilteredIncidentList(descriptionPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(THIRD_INCIDENT), model.getFilteredIncidentList());
    }

    @Test
    public void execute_singleDescriptionKeyword_multipleIncidentsFound() {
        String expectedMessage = String.format(MESSAGE_INCIDENTS_LISTED_OVERVIEW, 2);
        DescriptionKeywordsPredicate descriptionPredicate = prepareDescriptionPredicate("walkway");
        Command command = new FindIncidentsCommand(Arrays.asList(descriptionPredicate));
        expectedModel.updateFilteredIncidentList(descriptionPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SEVENTH_INCIDENT, FIRST_INCIDENT), model.getFilteredIncidentList());
    }

    @Test
    public void execute_multipleDescriptionKeywords_multipleIncidentsFound() {
        String expectedMessage = String.format(MESSAGE_INCIDENTS_LISTED_OVERVIEW, 3);
        DescriptionKeywordsPredicate descriptionPredicate = prepareDescriptionPredicate("park walkway");
        Command command = new FindIncidentsCommand(Arrays.asList(descriptionPredicate));
        expectedModel.updateFilteredIncidentList(descriptionPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SEVENTH_INCIDENT, THIRD_INCIDENT, FIRST_INCIDENT), model.getFilteredIncidentList());
    }

    @Test
    public void execute_multipleDescriptionIdKeywords_noIncidentsFound() {
        String expectedMessage = MESSAGE_NO_INCIDENTS_FOUND;
        IdKeywordsPredicate idKeywordsPredicate = prepareIdPredicate("12345678");
        DescriptionKeywordsPredicate descriptionPredicate = prepareDescriptionPredicate("park walkway");
        Predicate<Incident> combinedPredicate = descriptionPredicate.and(idKeywordsPredicate);
        Command command = new FindIncidentsCommand(Arrays.asList(descriptionPredicate, idKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new ArrayList<>(), model.getFilteredIncidentList());
    }

    @Test
    public void execute_multipleDescriptionIdKeywords_oneIncidentFound() {
        String expectedMessage = MESSAGE_SINGLE_INCIDENT_LISTED;
        IdKeywordsPredicate idKeywordsPredicate = prepareIdPredicate("0920160001");
        DescriptionKeywordsPredicate descriptionPredicate = prepareDescriptionPredicate("park walkway");
        Predicate<Incident> combinedPredicate = descriptionPredicate.and(idKeywordsPredicate);
        Command command = new FindIncidentsCommand(Arrays.asList(descriptionPredicate, idKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SEVENTH_INCIDENT), model.getFilteredIncidentList());
    }

    @Test
    public void execute_multipleDescriptionNameKeywords_multipleIncidentsFound() {
        String expectedMessage = String.format(MESSAGE_INCIDENTS_LISTED_OVERVIEW, 2);
        NameKeywordsPredicate nameKeywordsPredicate = prepareNamePredicate("KurZ   mEieR", false);
        DescriptionKeywordsPredicate descriptionPredicate = prepareDescriptionPredicate("park walkway");
        Predicate<Incident> combinedPredicate = descriptionPredicate.and(nameKeywordsPredicate);
        Command command = new FindIncidentsCommand(Arrays.asList(descriptionPredicate, nameKeywordsPredicate));
        expectedModel.updateFilteredIncidentList(combinedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(SEVENTH_INCIDENT, THIRD_INCIDENT), model.getFilteredIncidentList());
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionKeywordsPredicate}.
     * @param userInput
     * @return
     */
    private DescriptionKeywordsPredicate prepareDescriptionPredicate(String userInput) {
        List<String> splittedD = Arrays.asList(userInput.split("\\s+"));
        return new DescriptionKeywordsPredicate(splittedD);
    }

    /**
     * Parses {@code userInput} into a {@code IdKeywordsPredicate}.
     * @param userInput
     * @return
     */
    private IdKeywordsPredicate prepareIdPredicate(String userInput) {
        return new IdKeywordsPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code NameKeywordsPredicate}.
     * @param userInput
     * @return
     */
    private NameKeywordsPredicate prepareNamePredicate(String userInput, boolean isFullMatch) {
        List<String> splittedN = Arrays.asList(userInput.split("\\s+"));
        return new NameKeywordsPredicate(splittedN, isFullMatch);
    }
}
