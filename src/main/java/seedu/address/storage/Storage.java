package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCardBook;
import seedu.address.model.ReadOnlyFileBook;
import seedu.address.model.ReadOnlyNoteBook;
import seedu.address.model.ReadOnlyPasswordBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.card.CardBookStorage;
import seedu.address.storage.file.FileBookStorage;
import seedu.address.storage.note.NoteBookStorage;
import seedu.address.storage.password.PasswordBookStorage;
import seedu.address.storage.person.AddressBookStorage;

/**
 * API of the Storage component
 */

public interface Storage extends AddressBookStorage, FileBookStorage, CardBookStorage,
        NoteBookStorage, PasswordBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    String getStoragePassword();

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getFileBookFilePath();

    @Override
    Optional<ReadOnlyFileBook> readFileBook() throws DataConversionException, IOException;

    @Override
    void saveFileBook(ReadOnlyFileBook fileBook) throws IOException;

    @Override
    Path getCardBookFilePath();

    @Override
    Optional<ReadOnlyCardBook> readCardBook() throws DataConversionException, IOException;

    @Override
    void saveCardBook(ReadOnlyCardBook cardBook) throws IOException;

    Path getNoteBookFilePath();

    @Override
    Optional<ReadOnlyNoteBook> readNoteBook() throws DataConversionException, IOException;

    @Override
    void saveNoteBook(ReadOnlyNoteBook noteBook) throws IOException;

    Path getPasswordBookFilePath();

    @Override
    Optional<ReadOnlyPasswordBook> readPasswordBook() throws DataConversionException, IOException;

    @Override
    void savePasswordBook(ReadOnlyPasswordBook passwordBook) throws IOException;

}
