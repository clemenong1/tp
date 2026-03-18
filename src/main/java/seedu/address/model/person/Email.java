package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@u.nus.edu.sg "
        + "and adhere to the following constraints:\n"
        + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
        + "the parentheses, (" + SPECIAL_CHARACTERS + ").\n"
        + "2. Each special character must be surrounded by alphanumeric characters "
        + "(i.e. the local-part cannot start or end with a special character, and cannot contain consecutive special "
        + "characters).\n"
        + "3. The domain must be exactly u.nus.edu.sg.";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+";
    private static final String LOCAL_PART_REGEX =
            "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]" + ALPHANUMERIC_NO_UNDERSCORE + ")*";

    private static final String DOMAIN_REGEX = "@u\\.nus\\.edu\\.sg$";

    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + DOMAIN_REGEX;

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
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

        // instanceof handles nulls
        if (!(other instanceof Email)) {
            return false;
        }

        Email otherEmail = (Email) other;
        return value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
