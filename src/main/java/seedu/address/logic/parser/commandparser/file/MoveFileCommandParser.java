package seedu.address.logic.parser.commandparser.file;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Path;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.file.MoveFileCommand;
import seedu.address.logic.parser.commandparser.CommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ParserUtil;
import seedu.address.model.file.FilePath;

/**
 * Parses input arguments and creates a new MoveFileCommand object
 */
public class MoveFileCommandParser implements CommandParser<MoveFileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MoveFileCommand
     * and returns a MoveFileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MoveFileCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            Index index = ParserUtil.parseIndex(trimmedArgs.split(" ")[0]);
            if (trimmedArgs.indexOf(' ') == -1) {
                throw new ParseException("");
            }
            FilePath newPath =
                    new FilePath(Path.of(trimmedArgs.substring(trimmedArgs.indexOf(' ') + 1)).toString());
            return new MoveFileCommand(index, newPath);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MoveFileCommand.MESSAGE_USAGE), pe);
        }
    }

}
