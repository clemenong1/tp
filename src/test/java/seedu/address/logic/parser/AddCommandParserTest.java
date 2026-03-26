package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELE_HANDLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTutorialGroup(VALID_TUTORIAL_GROUP_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + PHONE_DESC_BOB + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, new AddCommand(expectedPerson));

        // teleHandle optional
        Person expectedPersonWithoutTeleHandle = new PersonBuilder(BOB).withTeleHandle(null).build();
        assertParseSuccess(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + TUTORIAL_GROUP_DESC_BOB, new AddCommand(expectedPersonWithoutTeleHandle));

        // different tutorial group
        Person expectedPersonDiffTg = new PersonBuilder(BOB).withTutorialGroup(VALID_TUTORIAL_GROUP_AMY).build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB + TELE_HANDLE_DESC_BOB
                        + TUTORIAL_GROUP_DESC_AMY,
                new AddCommand(expectedPersonDiffTg));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + PHONE_DESC_BOB + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple teleHandles
        assertParseFailure(parser, TELE_HANDLE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELE_HANDLE));

        // multiple studentIds
        assertParseFailure(parser, STUDENT_ID_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STUDENT_ID));

        // multiple tutorial groups
        assertParseFailure(parser, TUTORIAL_GROUP_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TUTORIAL_GROUP));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + STUDENT_ID_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY
                        + TELE_HANDLE_DESC_AMY + NAME_DESC_AMY + TUTORIAL_GROUP_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_TELE_HANDLE, PREFIX_STUDENT_ID, PREFIX_TUTORIAL_GROUP));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid teleHandle
        assertParseFailure(parser, INVALID_TELE_HANDLE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELE_HANDLE));

        // invalid tutorial group
        assertParseFailure(parser, INVALID_TUTORIAL_GROUP_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TUTORIAL_GROUP));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid teleHandle
        assertParseFailure(parser, validExpectedPersonString + INVALID_TELE_HANDLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TELE_HANDLE));

        // invalid tutorial group
        assertParseFailure(parser, validExpectedPersonString + INVALID_TUTORIAL_GROUP_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TUTORIAL_GROUP));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + VALID_PHONE_BOB
                + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + VALID_EMAIL_BOB + PHONE_DESC_BOB
                + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, expectedMessage);

        // missing studentId prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_STUDENT_ID_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, expectedMessage);

        // missing tutorial group prefix
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + TELE_HANDLE_DESC_BOB + VALID_TUTORIAL_GROUP_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_TELE_HANDLE_BOB
                + VALID_STUDENT_ID_BOB + VALID_TUTORIAL_GROUP_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + INVALID_PHONE_DESC
                + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + INVALID_EMAIL_DESC + PHONE_DESC_BOB
                + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid teleHandle
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + INVALID_TELE_HANDLE_DESC + TUTORIAL_GROUP_DESC_BOB, TeleHandle.MESSAGE_CONSTRAINTS);

        // invalid studentId
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_STUDENT_ID_DESC + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB, StudentId.MESSAGE_CONSTRAINTS);

        // invalid tutorial group
        assertParseFailure(parser, NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + TELE_HANDLE_DESC_BOB + INVALID_TUTORIAL_GROUP_DESC, TutorialGroup.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB
                + INVALID_TELE_HANDLE_DESC + TUTORIAL_GROUP_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + STUDENT_ID_DESC_BOB + EMAIL_DESC_BOB
                + PHONE_DESC_BOB + TELE_HANDLE_DESC_BOB + TUTORIAL_GROUP_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
