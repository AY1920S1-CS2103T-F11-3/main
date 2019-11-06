package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.VersionedNoteBook;


/**
 * Clears the address book.
 */
public class ClearNoteCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Note book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.commitNote();
        model.setNoteBook(new VersionedNoteBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
