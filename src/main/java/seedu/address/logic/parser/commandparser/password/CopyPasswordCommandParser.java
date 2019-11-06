package seedu.address.logic.parser.commandparser.password;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.password.CopyPasswordCommand;
import seedu.address.logic.commands.password.CopyPasswordValueCommand;
import seedu.address.logic.commands.password.CopyUsernameCommand;
import seedu.address.logic.commands.password.CopyWebsiteCommand;
import seedu.address.logic.parser.commandparser.CommandParser;
import seedu.address.logic.parser.util.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new CopyPassword object
 */
public class CopyPasswordCommandParser implements CommandParser<CopyPasswordCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");


    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CopyPasswordCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyPasswordCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        try {
            Index index = ParserUtil.parseIndex(arguments);
            switch (commandWord) {
            case CopyPasswordValueCommand.COMMAND_WORD:
            case CopyPasswordValueCommand.COMMAND_WORD1:
                return new CopyPasswordValueCommand(index);
            case CopyWebsiteCommand.COMMAND_WORD:
            case CopyWebsiteCommand.COMMAND_WORD1:
                return new CopyWebsiteCommand(index);
            case CopyUsernameCommand.COMMAND_WORD:
            case CopyUsernameCommand.COMMAND_WORD1:
                return new CopyUsernameCommand(index);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyPasswordCommand.MESSAGE_USAGE));
        }
    }
}
