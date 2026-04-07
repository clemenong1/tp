package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's tutorial group in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorialGroup(String)}.
 */
public class TutorialGroup {

    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial group should start with 'T' followed by exactly 2 digits (e.g. T01, T12).";
    public static final String VALIDATION_REGEX = "^T\\d{2}$";

    public final String value;

    /**
     * Constructs a {@code TutorialGroup}.
     *
     * @param tutorialGroup A valid tutorial group.
     */
    public TutorialGroup(String tutorialGroup) {
        requireNonNull(tutorialGroup);
        checkArgument(isValidTutorialGroup(tutorialGroup), MESSAGE_CONSTRAINTS);
        value = tutorialGroup;
    }

    /**
     * Returns true if a given string is a valid tutorial group.
     */
    public static boolean isValidTutorialGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TutorialGroup)) {
            return false;
        }

        TutorialGroup otherTutorialGroup = (TutorialGroup) other;
        return value.equals(otherTutorialGroup.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
