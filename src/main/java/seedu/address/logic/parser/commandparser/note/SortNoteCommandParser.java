package seedu.address.logic.parser.commandparser.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_SORTBY;

import java.util.stream.Stream;

import seedu.address.logic.commands.note.SortNoteCommand;
import seedu.address.logic.parser.commandparser.CommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.ParserUtil;
import seedu.address.logic.parser.util.Prefix;
import seedu.address.model.note.SortByCond;



/**
 * Parses input arguments and creates a new EditCommand object
 */
public class SortNoteCommandParser implements CommandParser<SortNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditNoteCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORTBY);
        if (!arePrefixesPresent(argMultimap, PREFIX_SORTBY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortNoteCommand.MESSAGE_USAGE));
        }
        SortByCond sortByCond = ParserUtil.parseSortByCond(argMultimap.getValue(PREFIX_SORTBY).get());
        return new SortNoteCommand(sortByCond);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
