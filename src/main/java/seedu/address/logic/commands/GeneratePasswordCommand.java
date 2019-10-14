package seedu.address.logic.commands;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.Model;

public class GeneratePasswordCommand extends Command{
    public static final String COMMAND_WORD = "generate";
    public static final String MESSAGE_USAGE = "Password Generated: %s" + "\n" + "Password has been copied to your clipboard!";
    public static final String MESSAGE_REQUIRE_CHECK_AT_LEAST_ONE = "At least one field is required to be true to generate password. ";

    private int length;
    private boolean lower;
    private boolean upper;
    private boolean num;
    private boolean special;

    public GeneratePasswordCommand(int length, boolean lower, boolean upper, boolean num, boolean special) {
        this.length = length;
        this.lower = lower;
        this.upper = upper;
        this.num = num;
        this.special = special;
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
        passwordLength = length;
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

        ArrayList<String[]> characterSet = new ArrayList<>();

        if (lower && !characterSet.contains(lowAlpha))
        {
            characterSet.add(lowAlpha);
        }
        else if (!lower && characterSet.contains(lowAlpha))
        {
            characterSet.remove(lowAlpha);
        }

        if (upper && !characterSet.contains(highAlpha))
        {
            characterSet.add(highAlpha);
        }
        else if (!upper && characterSet.contains(highAlpha))
        {
            characterSet.remove(highAlpha);
        }

        if (special && !characterSet.contains(specialChars))
        {
            characterSet.add(specialChars);
        }
        else if (!special && characterSet.contains(specialChars))
        {
            characterSet.remove(specialChars);
        }

        if (num && !characterSet.contains(numbers))
        {
            characterSet.add(numbers);
        }
        else if (!num && characterSet.contains(numbers))
        {
            characterSet.remove(numbers);
        }
        //TODO: check at least oen field is checked
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
    /**
     * Stores the details of password requirements.
     */
    public static class PasswordGeneratorDescriptor {
        private int length;
        private boolean lower;
        private boolean upper;
        private boolean num;
        private boolean special;

        public PasswordGeneratorDescriptor() {}

        /**
         * Returns true if at least one requirement is true.
         */
        public boolean isAnyFieldChecked() {
            return lower == true || upper == true || num == true || special == true;
        }

        public void setLength(int length) {
            this.length = length;
        }
        public void setLower(boolean lower) {
            this.lower = lower;
        }
        public void setUpper(boolean upper) {
            this.upper = upper;
        }
        public void setNum(boolean num) {
            this.num = num;
        }
        public void setSpecial(boolean special) {
            this.special = special;
        }

        public int getLength() {
            return length;
        }
        public boolean getLower() {
            return lower;
        }
        public boolean getUpper() {
            return upper;
        }
        public boolean getNum() {
            return num;
        }
        public boolean getSpecial() {
            return special;
        }
    }
}

