package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.HashMap;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AnalysePasswordCommand extends Command{

    public static final String COMMAND_WORD = "analyse";
    //TODO: abstract out all the messages
    //public static final String MESSAGE_NOT_UNIQUE = "The following accounts resued the same password";

    private ArrayList<MockPassword> accountList;

    public AnalysePasswordCommand() {
        accountList = new ArrayList<>(); //MOCK_UP for accountList
        accountList.add(new MockPassword("facebook", "user1", "password"));
        accountList.add(new MockPassword("google", "user2", "password1"));
        accountList.add(new MockPassword("goodgle", "user3", "password2"));
        accountList.add(new MockPassword("fabook", "user4", "password3"));
        accountList.add(new MockPassword("face", "user5", "asdfghjkl"));
        accountList.add(new MockPassword("facebook", "user6", "password"));
        accountList.add(new MockPassword("f", "user7", "pass"));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        checkUniquePassword(accountList);
        return new CommandResult("");
    }

    private void checkUniquePassword(ArrayList<MockPassword> accountList) {
        HashMap<String, ArrayList<MockPassword>> passwordToSamePassAccountMap = new HashMap<>();
        ArrayList<String> notUniquePasswords = new ArrayList<>();
        for (MockPassword p : accountList) {
            if (passwordToSamePassAccountMap.containsKey(p.password)) {
                passwordToSamePassAccountMap.get(p.password).add(p);
                notUniquePasswords.add(p.password);
            } else {
                passwordToSamePassAccountMap.put(p.password, new ArrayList<>());
                passwordToSamePassAccountMap.get(p.password).add(p);
            }
        }
        if (notUniquePasswords.size() != 0) {

            System.out.println("The following accounts were found to reuse the same password:");
            for (String p : notUniquePasswords) {
                ArrayList<MockPassword> accountsWithSamePassword = passwordToSamePassAccountMap.get(p);
                for (MockPassword account : accountsWithSamePassword) {
                    System.out.println(account.purpose + " | " + account.username + " | "
                                    + account.password + " | " + "Shares the password: " + account.password);
                }
            }
        } else {
            System.out.println("No passwords were reused!");
        }

    }

    public class MockPassword {
        private String purpose;
        private String username;
        private String password;

        public MockPassword(String purpose, String username, String password) {
            this.purpose = purpose;
            this.username = username;
            this.password = password;
        }

        //TODO: CALCULATE_STRENGTH_METHOD
    }
}
