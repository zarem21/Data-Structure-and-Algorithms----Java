package autocomplete;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TermTest {
    @Test
    public void testSimpleCompareTo() {
        Term a = new Term("autocomplete", 0);
        Term b = new Term("me", 0);
        assertTrue(a.compareTo(b) < 0); // "autocomplete" < "me"
    }

    @Test
    public void testTermReturns() {
        Term a = new Term("absent", 10);
        Term b = new Term("able", 10);
        System.out.println(a.weight());
        System.out.println(a.query());
        System.out.println(a.queryPrefix(6));
        System.out.println(a.compareTo(b));
    }

    // Write more unit tests below.
}