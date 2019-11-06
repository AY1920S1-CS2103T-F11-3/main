package seedu.address.logic.parser.commandparser.password;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.password.ReadPasswordCommand;
import seedu.address.logic.parser.commandparser.CommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new ReadPasswordCommand object
 */
public class ReadPasswordCommandParser implements CommandParser<ReadPasswordCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ReadPasswordCommand
     * and returns a ReadPasswordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ReadPasswordCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new ReadPasswordCommand(index);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadPasswordCommand.MESSAGE_USAGE));
        }
    }
}
