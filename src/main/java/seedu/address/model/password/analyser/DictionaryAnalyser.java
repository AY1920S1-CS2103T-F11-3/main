package seedu.address.model.password.analyser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Dictionary;
import seedu.address.commons.util.leetUtil;
import seedu.address.model.password.Password;
import seedu.address.model.password.analyser.analysis.DictionaryResult;
import seedu.address.model.password.analyser.match.DictionaryMatch;
import seedu.address.model.password.analyser.match.Match;

public class DictionaryAnalyser implements Analyser{

    private static final String MESSAGE_HEADER = "Analysing passwords for commonly used passwords:\n";
    private Dictionary dictionary;
    private ArrayList<DictionaryResult> analysisObjects;

    public DictionaryAnalyser(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void analyse(List<Password> passwordList) {
        ArrayList<DictionaryResult> analysisObjects = new ArrayList<>();
        for (Password acc : passwordList) {
            List<DictionaryMatch> matches = getAllMatches(acc.getPasswordValue().value);
            if (matches.isEmpty()) {
                analysisObjects.add(new DictionaryResult(acc, DESC_PASS, matches));
            } else {
                analysisObjects.add(new DictionaryResult(acc, DESC_FAIL, matches));
            }
        }
        this.analysisObjects = analysisObjects;
    }

    private List<DictionaryMatch> getAllMatches(String password) {
        List<DictionaryMatch> matches = new ArrayList<>();

        // Create all possible sub-sequences of the password
        for (int start = 0; start < password.length(); start++)
        {
            for (int end = start + 1; end <= password.length(); end++)
            {
                String split_password = password.substring(start, end);

                // Match on lower
                String lowerPart = split_password.toLowerCase();
                Integer lower_rank = dictionary.getDictionary().get(lowerPart);
                if (lower_rank != null) {
                    matches.add(new DictionaryMatch(start, end - 1, lowerPart, lower_rank));
                    continue;
                }

                //Match on leet
                List<String> unleetList = leetUtil.translateLeet(lowerPart);
                for (final String unleetPart : unleetList)
                {
                    Integer unleet_rank = dictionary.getDictionary().get(unleetPart);
                    if (unleet_rank != null)
                    {
                        matches.add(new DictionaryMatch(start, end - 1, unleetPart, unleet_rank));
                        continue;
                    }
                }
            }
        }
        Collections.sort(matches);
        return matches;
    }

    @Override
    public String outputSummaryReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(MESSAGE_HEADER);
        reportBuilder.append(MESSAGE_COLUMNS);
        for (DictionaryResult o : analysisObjects) {
            reportBuilder.append(o);
        }
        return reportBuilder.toString();
    }

    @Override
    public String outputDetailedReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(MESSAGE_INIT);
        reportBuilder.append(MESSAGE_HEADER);
        for (DictionaryResult o : analysisObjects) {
            reportBuilder.append(o.getGreaterDetail());
        }
        return reportBuilder.toString();
    }
}
