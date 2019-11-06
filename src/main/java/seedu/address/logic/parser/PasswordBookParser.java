package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.GoToCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.password.AddPasswordCommand;
import seedu.address.logic.commands.password.AnalysePasswordCommand;
import seedu.address.logic.commands.password.CopyPasswordCommand;
import seedu.address.logic.commands.password.DeletePasswordCommand;
import seedu.address.logic.commands.password.EditPasswordCommand;
import seedu.address.logic.commands.password.FindPasswordCommand;
import seedu.address.logic.commands.password.GeneratePasswordCommand;
import seedu.address.logic.commands.password.ListPasswordCommand;
import seedu.address.logic.commands.password.ReadPasswordCommand;
import seedu.address.logic.parser.commandparser.GoToCommandParser;
import seedu.address.logic.parser.commandparser.password.AddPasswordCommandParser;
import seedu.address.logic.parser.commandparser.password.AnalysePasswordCommandParser;
import seedu.address.logic.parser.commandparser.password.CopyPasswordCommandParser;
import seedu.address.logic.parser.commandparser.password.DeletePasswordCommandParser;
import seedu.address.logic.parser.commandparser.password.EditPasswordCommandParser;
import seedu.address.logic.parser.commandparser.password.FindPasswordCommandParser;
import seedu.address.logic.parser.commandparser.password.GeneratePasswordCommandParser;
import seedu.address.logic.parser.commandparser.password.ReadPasswordCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class PasswordBookParser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Used for initial separation of command word and args.
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case AddPasswordCommand.COMMAND_WORD:
            return new AddPasswordCommandParser().parse(arguments);
        case DeletePasswordCommand.COMMAND_WORD:
            return new DeletePasswordCommandParser().parse(arguments);
        case ReadPasswordCommand.COMMAND_WORD:
            return new ReadPasswordCommandParser().parse(arguments);
        case EditPasswordCommand.COMMAND_WORD:
            return new EditPasswordCommandParser().parse(arguments);
        case ListPasswordCommand.COMMAND_WORD:
            return new ListPasswordCommand();
        case FindPasswordCommand.COMMAND_WORD:
            return new FindPasswordCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case GoToCommand.COMMAND_WORD:
            return new GoToCommandParser().parse(arguments);
        case AnalysePasswordCommand.COMMAND_WORD:
            return new AnalysePasswordCommandParser().parse(arguments);
        case GeneratePasswordCommand.COMMAND_WORD:
            return new GeneratePasswordCommandParser().parse(arguments);
        case CopyPasswordCommand.COMMAND_WORD:
        case CopyPasswordCommand.COMMAND_WORD1:
            return new CopyPasswordCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

    }
}
