package seedu.address.model.password.analyser.match;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.model.util.SampleDataUtil.getTagSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.password.Description;
import seedu.address.model.password.Password;
import seedu.address.model.password.PasswordValue;
import seedu.address.model.password.Username;

class SimilarityMatchTest {

    @Test
    void compareTo_equalSimilarity_returnZero() {
        Password p = new Password(new Description("Gmail"), new Username("Randomguy"),
                new PasswordValue("password"), getTagSet("SocialMedia"));
        SimilarityMatch instanceOne = new SimilarityMatch(0, 4, "token", p, 0.7);
        SimilarityMatch instanceTwo = new SimilarityMatch(0, 4, "nekot", p, 0.7);
        assertTrue(instanceOne.compareTo(instanceTwo) == 0);
    }

    @Test
    void compareTo_unequalRank_returnPositive() {
        Password p = new Password(new Description("Gmail"), new Username("Randomguy"),
                new PasswordValue("password"), getTagSet("SocialMedia"));
        SimilarityMatch instanceOne = new SimilarityMatch(0, 4, "lowerRank", p, 0.3);
        SimilarityMatch instanceTwo = new SimilarityMatch(0, 4, "higherRank", p, 0.5);
        assertTrue(instanceOne.compareTo(instanceTwo) > 0);
    }

}
