package seedu.address.model.password.analyser;

import org.junit.jupiter.api.Test;
import seedu.address.model.password.Description;
import seedu.address.model.password.Password;
import seedu.address.model.password.PasswordValue;
import seedu.address.model.password.Username;
import seedu.address.model.password.analyser.match.SequenceMatch;
import seedu.address.model.password.analyser.result.SequenceResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

class SequenceAnalyserTest {

    @Test
    void analyse_passwordWithForwardNumericalSequence() {

        Password p = new Password(new Description("Gmail"), new Username("Randomguy"),
                new PasswordValue("123pass456word123456"), getTagSet("SocialMedia"));
        ArrayList<Password> list = new ArrayList<>();
        list.add(p);

        SequenceAnalyser a = new SequenceAnalyser();
        a.analyse(list);
        SequenceResult actual = a.getResults().get(0);

        ArrayList<SequenceMatch> expectedMatches = new ArrayList<>();
        expectedMatches.add(new SequenceMatch(0, 2, "123"));
        expectedMatches.add(new SequenceMatch(7,9,"456"));
        expectedMatches.add(new SequenceMatch(14, 19, "123456"));
        SequenceResult expected = new SequenceResult(p, "failed", expectedMatches);

        assertEquals(actual.getGreaterDetail(), expected.getGreaterDetail());

    }

    @Test
    void analyse_passwordWithBackwardNumericalSequence() {

        Password p = new Password(new Description("Gmail"), new Username("Randomguy"),
                new PasswordValue("321pass654word654321"), getTagSet("SocialMedia"));
        ArrayList<Password> list = new ArrayList<>();
        list.add(p);

        SequenceAnalyser a = new SequenceAnalyser();
        a.analyse(list);
        SequenceResult actual = a.getResults().get(0);

        ArrayList<SequenceMatch> expectedMatches = new ArrayList<>();
        expectedMatches.add(new SequenceMatch(0, 2, "321"));
        expectedMatches.add(new SequenceMatch(7,9,"654"));
        expectedMatches.add(new SequenceMatch(14, 19, "654321"));
        SequenceResult expected = new SequenceResult(p, "failed", expectedMatches);

        assertEquals(actual.getGreaterDetail(), expected.getGreaterDetail());
    }

    @Test
    void analyse_passwordWithForwardAlphaSequence() {
        Password p = new Password(new Description("Gmail"), new Username("Randomguy"),
                new PasswordValue("ABCpassXYZwordABCXYZ"), getTagSet("SocialMedia"));
        ArrayList<Password> list = new ArrayList<>();
        list.add(p);

        SequenceAnalyser a = new SequenceAnalyser();
        a.analyse(list);
        SequenceResult actual = a.getResults().get(0);

        ArrayList<SequenceMatch> expectedMatches = new ArrayList<>();
        expectedMatches.add(new SequenceMatch(0, 2, "ABC"));
        expectedMatches.add(new SequenceMatch(7,9,"XYZ"));
        expectedMatches.add(new SequenceMatch(14, 16, "ABC"));
        expectedMatches.add(new SequenceMatch(17, 19, "XYZ"));

        SequenceResult expected = new SequenceResult(p, "failed", expectedMatches);

        assertEquals(actual.getGreaterDetail(), expected.getGreaterDetail());
    }

    @Test
    void analyse_passwordWithBackwardAlphaSequence() {
        Password p = new Password(new Description("Gmail"), new Username("Randomguy"),
                new PasswordValue("CBApassZYXwordCBAZYX"), getTagSet("SocialMedia"));
        ArrayList<Password> list = new ArrayList<>();
        list.add(p);

        SequenceAnalyser a = new SequenceAnalyser();
        a.analyse(list);
        SequenceResult actual = a.getResults().get(0);

        ArrayList<SequenceMatch> expectedMatches = new ArrayList<>();
        expectedMatches.add(new SequenceMatch(0, 2, "CBA"));
        expectedMatches.add(new SequenceMatch(7,9,"ZYX"));
        expectedMatches.add(new SequenceMatch(14, 16, "CBA"));
        expectedMatches.add(new SequenceMatch(17, 19, "ZYX"));
        SequenceResult expected = new SequenceResult(p, "failed", expectedMatches);

        assertEquals(actual.getGreaterDetail(), expected.getGreaterDetail());
    }
}