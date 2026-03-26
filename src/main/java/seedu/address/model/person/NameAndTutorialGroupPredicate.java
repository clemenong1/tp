package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches all of the name keywords given (each as a word prefix,
 * case-insensitive) and/or the {@code TutorialGroup} matches any of the tutorial groups given.
 */
public class NameAndTutorialGroupPredicate implements Predicate<Person> {
    private final List<String> nameKeywords;
    private final List<TutorialGroup> tutorialGroups;
    private final List<String> emailPrefixes;
    private final List<String> teleHandlePrefixes;

    /**
     * Constructs a predicate that matches persons by name keywords and/or tutorial groups.
     *
     * @param nameKeywords   Name keywords to match; every keyword must match (case-insensitive, word-prefix match).
     * @param tutorialGroups Tutorial groups to match (exact match).
     * @param emailPrefixes  Email prefixes to match (case-insensitive prefix match).
     * @param teleHandlePrefixes Telegram handle prefixes to match (case-insensitive prefix match).
     */
    public NameAndTutorialGroupPredicate(List<String> nameKeywords, List<TutorialGroup> tutorialGroups,
                                         List<String> emailPrefixes, List<String> teleHandlePrefixes) {
        this.nameKeywords = nameKeywords;
        this.tutorialGroups = tutorialGroups;
        this.emailPrefixes = emailPrefixes;
        this.teleHandlePrefixes = teleHandlePrefixes;
    }

    @Override
    public boolean test(Person person) {
        if (nameKeywords.isEmpty() && tutorialGroups.isEmpty() && emailPrefixes.isEmpty()
                && teleHandlePrefixes.isEmpty()) {
            return false;
        }

        boolean matchesName = nameKeywords.isEmpty() || nameKeywords.stream()
                .allMatch(keyword -> StringUtil.containsWordPrefixIgnoreCase(person.getName().fullName, keyword));
        boolean matchesTutorialGroup = tutorialGroups.isEmpty() || tutorialGroups.stream()
                .anyMatch(group -> person.getTutorialGroup().value.equalsIgnoreCase(group.value));
        boolean matchesEmail = emailPrefixes.isEmpty() || emailPrefixes.stream()
                .anyMatch(prefix -> StringUtil.startsWithIgnoreCase(person.getEmail().value, prefix));
        boolean matchesTeleHandle = teleHandlePrefixes.isEmpty() || teleHandlePrefixes.stream()
                .anyMatch(prefix -> person.getTeleHandle()
                        .map(th -> StringUtil.startsWithIgnoreCase(th.value, prefix))
                        .orElse(false));

        return matchesName && matchesTutorialGroup && matchesEmail && matchesTeleHandle;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NameAndTutorialGroupPredicate)) {
            return false;
        }

        NameAndTutorialGroupPredicate otherPredicate = (NameAndTutorialGroupPredicate) other;
        return nameKeywords.equals(otherPredicate.nameKeywords)
                && tutorialGroups.equals(otherPredicate.tutorialGroups)
                && emailPrefixes.equals(otherPredicate.emailPrefixes)
                && teleHandlePrefixes.equals(otherPredicate.teleHandlePrefixes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nameKeywords", nameKeywords)
                .add("tutorialGroups", tutorialGroups)
                .add("emailPrefixes", emailPrefixes)
                .add("teleHandlePrefixes", teleHandlePrefixes)
                .toString();
    }
}
