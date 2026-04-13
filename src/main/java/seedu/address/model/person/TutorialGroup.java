package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents a Person's tutorial group in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorialGroup(String)}.
 * Input may use any letter casing; the stored {@link #value} is always uppercase (canonical form).
 */
public class TutorialGroup {

    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial group should be 3 to 5 alphanumeric characters (letters or digits) inclusive. "
            + "Letters are case-insensitive; stored in uppercase.";
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9]{3,5}$";

    public final String value;

    /**
     * Constructs a {@code TutorialGroup}.
     * Leading and trailing spaces are trimmed; letters are uppercased.
     *
     * @param tutorialGroup A valid tutorial group in any letter casing.
     */
    public TutorialGroup(String tutorialGroup) {
        requireNonNull(tutorialGroup);
        checkArgument(isValidTutorialGroup(tutorialGroup), MESSAGE_CONSTRAINTS);
        value = tutorialGroup.trim().toUpperCase(Locale.ROOT);
    }

    /**
     * Returns true if {@code test} is non-null and trims to a valid tutorial group.
     */
    public static boolean isValidTutorialGroup(String test) {
        if (test == null) {
            return false;
        }
        return test.trim().matches(VALIDATION_REGEX);
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
