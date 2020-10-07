package deques.palindrome;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OffByOneTest {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void equalCharsTest() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('x', 'y'));
        assertTrue(offByOne.equalChars('e', 'f'));

        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertFalse(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('a', '1'));
        assertFalse(offByOne.equalChars('1', 'B'));
        assertFalse(offByOne.equalChars('@', '#'));
        assertFalse(offByOne.equalChars('a', 'B'));
        assertTrue(offByOne.equalChars('1', '2'));
        assertFalse(offByOne.equalChars('1', '3'));
    }
}
