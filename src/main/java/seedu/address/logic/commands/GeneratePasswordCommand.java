package seedu.address.logic.commands;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.security.SecureRandom;
import java.util.ArrayList;

import seedu.address.model.Model;

public class GeneratePasswordCommand extends Command{
    public static final String COMMAND_WORD = "generate";

    public static final String MESSAGE_USAGE = "Password Generated: %s" + "\n" + "Password has been copied to your clipboard!";

    public GeneratePasswordCommand() {
    }

    /**
     * Returns a CommandResult containing a randomly generated password.
     * @param model the manager model for SecureIT
     * @return randomly generated password
     */

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // TODO: Integrate with CP PasswordModelManger
        // TODO: Following method should be abstracted to a separate PasswordModelManager
        String password = generateRandomPassword();
        copyToClipboard(password, null);
        return new CommandResult(String.format(MESSAGE_USAGE, password));
    }

    private String generateRandomPassword() {
        // Attributes:
        SecureRandom randomNumGen;
        int passwordLength;
        ArrayList<String[]> characterSet;
        String[] lowAlpha = new String[]
                {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                        "u", "v", "w", "x", "y", "z"};
        String[] highAlpha = new String[]
                {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                        "U", "V", "W", "X", "Y", "Z"};
        String[] specialChars = new String[]
                {"~", "`", "!", "@", "#", "$", "%", "^", "&", "*",
                        "(", ")", "-", "_", "+", "=", "[", "{", "]", "}",
                        "|", "\\", "'", "\"", ";", ":", "?", "/", ".", ">",
                        "<", ","};
        String[] numbers = new String[]
                {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        // init
        characterSet = setCharacterSet(lowAlpha, highAlpha, specialChars, numbers);
        passwordLength = 8; // hardcoded passwordLength TODO: make it dynamic based on user input
        randomNumGen = new SecureRandom();
        StringBuilder password = new StringBuilder();

        //generate random character from characterSet for 8 times.
        for (int k = 0; k < passwordLength; k++) {
            int ranArrayChooser = randomNumGen.nextInt(characterSet.size());
            int randomLetterIndex = randomNumGen.nextInt(characterSet.get(ranArrayChooser).length -1);
            password.append(characterSet.get(ranArrayChooser)[randomLetterIndex]);
        }

        return password.toString();
    }

    private ArrayList<String[]> setCharacterSet(String[] lowAlpha, String[] highAlpha, String[] specialChars, String[] numbers) {
        // for now, password will allow lower, upper, numerical, special characters.
        // TODO: let the user set if which characterSet he wants to include into his random password.
        ArrayList<String[]> characterSet = new ArrayList<>();
        characterSet.add(lowAlpha);
        characterSet.add(highAlpha);
        characterSet.add(specialChars);
        characterSet.add(numbers);

        return characterSet;


    }

    private void requireNonNull(Model model) {
    }

    public void copyToClipboard(String textToCopy, ClipboardOwner user)

    {
        //Create & get the clipboard from the computer
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        //Make the text selected
        Transferable selectedText = new StringSelection(textToCopy);

        //Copy & Write the selected text to the user's clipboard
        clipboard.setContents(selectedText, user);
    }
}
