package seedu.address.model.incident;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Name;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameKeywordsPredicate implements Predicate<Incident> {
    private final List<String> keywords;
    private final boolean isFullMatch;

    public NameKeywordsPredicate(List<String> nameKeywords, boolean isFullMatch) {
        this.keywords = nameKeywords;
        this.isFullMatch = isFullMatch;
    }

    @Override
    public boolean test(Incident incident) {
        if (isFullMatch) {
            return keywords.stream()
                    .allMatch(keyword -> StringUtil.containsWordIgnoreCase(incident.getOperator().getName().fullName,
                            keyword));
        } else {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(incident.getOperator().getName().fullName,
                            keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameKeywordsPredicate) other).keywords)); // state check
    }

    public String getPredicate() {
        return keywords.toString();
    }
}
