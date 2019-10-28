package seedu.address.model.password.analyser.match;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class BaseMatchTest {
    @Test
    public void testConstructor() {
        BaseMatch instance = new BaseMatchImpl( 0, 5, "dummy");
        String token = instance.getToken();
        int start = instance.getStartIndex();
        int end = instance.getEndIndex();
        assertEquals("dummy", token);
        assertTrue(0 == start);
        assertTrue(5 == end);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BaseMatchImpl(0, 5, null));
    }

    @Test
    public void constructor_invalidMatchIndex_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new BaseMatchImpl(5, 0, "dummy"));
    }

    @Test
    public void constructor_invalidMatchToken_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new BaseMatchImpl(0, 5, ""));
    }

    public class BaseMatchImpl extends BaseMatch {
        public BaseMatchImpl(int start, int end, String token) {
            super(start, end, token);
        }
    }
}