package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class leetUtil {
    private static Map<Character, Character[]> defaultLeetTable = new HashMap<>();
    static {
        defaultLeetTable.put('4', new Character[]{'a'});
        defaultLeetTable.put('@', new Character[]{'a'});
        defaultLeetTable.put('8', new Character[]{'b'});
        defaultLeetTable.put('(', new Character[]{'c'});
        defaultLeetTable.put('{', new Character[]{'c'});
        defaultLeetTable.put('[', new Character[]{'c'});
        defaultLeetTable.put('<', new Character[]{'c'});
        defaultLeetTable.put('3', new Character[]{'e'});
        defaultLeetTable.put('9', new Character[]{'g'});
        defaultLeetTable.put('6', new Character[]{'g'});
        defaultLeetTable.put('&', new Character[]{'g'});
        defaultLeetTable.put('#', new Character[]{'h'});
        defaultLeetTable.put('!', new Character[]{'i', 'l'});
        defaultLeetTable.put('1', new Character[]{'i', 'l'});
        defaultLeetTable.put('|', new Character[]{'i', 'l'});
        defaultLeetTable.put('0', new Character[]{'o'});
        defaultLeetTable.put('$', new Character[]{'s'});
        defaultLeetTable.put('5', new Character[]{'s'});
        defaultLeetTable.put('+', new Character[]{'t'});
        defaultLeetTable.put('7', new Character[]{'t', 'l'});
        defaultLeetTable.put('%', new Character[]{'x'});
        defaultLeetTable.put('2', new Character[]{'z'});
    }

    //Generate the possible list of translations given a password.
    public static List<String> translateLeet(String password) {
        ArrayList<String> translations = new ArrayList();
        final TreeMap<Integer, Character[]> replacements = new TreeMap<>();
        for (int i = 0; i < password.length(); i++)
        {
            Character[] replacement = defaultLeetTable.get(password.charAt(i));
            if (replacement != null)
            {
                replacements.put(i, replacement); // for each character, if the special character has mapping to normal, then put inside the tree.
            }
        }

        // Do not bother continuing if we're going to replace every single character
        if(replacements.keySet().size() == password.length())
            return translations;

        if (replacements.size() > 0)
        {
            char[] password_char = new char[password.length()];
            for (int i = 0; i < password.length(); i++)
            {
                password_char[i] = password.charAt(i);
            }
            replaceAtIndex(replacements, replacements.firstKey(), password_char, translations); //starting from the first element in tree map, build all unleets.
        }

        return translations;

    }

    // Internal function to recursively build the list of un-leet possibilities.
    private static void replaceAtIndex(final TreeMap<Integer, Character[]> replacements, Integer current_index, char[] password, List<String> final_passwords)
    {
        Character[] listOfReplacementsForSpecialCharacter = replacements.get(current_index);
        for (char replacement : listOfReplacementsForSpecialCharacter) //for each possible replacement in char[]
        {
            password[current_index] = replacement;
            if (current_index.equals(replacements.lastKey()))
            {
                final_passwords.add(new String(password));
            }
            else if (final_passwords.size() > 100)
            {
                // Give up if we've already made 100 replacements
                return;
            }
            else
            {
                replaceAtIndex(replacements, replacements.higherKey(current_index), password, final_passwords); //move onto the next index in the password.
            }
        }
    }



}
