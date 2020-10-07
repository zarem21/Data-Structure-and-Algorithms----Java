package deques.palindrome;

import deques.Deque;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeTest {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne obo = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("essay"));
        assertFalse(palindrome.isPalindrome("home"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("bob"));
        assertTrue(palindrome.isPalindrome("redder"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("       "));
        assertTrue(palindrome.isPalindrome(" "));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("12321"));
        assertFalse(palindrome.isPalindrome("1222222"));
        assertFalse(palindrome.isPalindrome("12##$234j kl5"));
        assertFalse(palindrome.isPalindrome("RaceCAR"));

    }

    @Test
    public void testIsPalindromeObo() {
        assertTrue(palindrome.isPalindrome("ab", obo));
        assertTrue(palindrome.isPalindrome("rq", obo));
        assertTrue(palindrome.isPalindrome("12", obo));
        assertTrue(palindrome.isPalindrome("32", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("1", obo));
        assertFalse(palindrome.isPalindrome("Ab", obo));
        assertFalse(palindrome.isPalindrome("ae", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("flAKE", obo));
        assertFalse(palindrome.isPalindrome("16", obo));
        assertFalse(palindrome.isPalindrome("$%%^&$", obo));
        assertTrue(palindrome.isPalindrome("#$", obo));


    }
}
