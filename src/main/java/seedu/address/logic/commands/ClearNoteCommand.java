package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.NoteBook;


/**
 * Clears the address book.
 */
public class ClearNoteCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Note book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setNoteBook(new NoteBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}