package autocomplete;

import org.junit.Test;

import java.util.Arrays;

public class LinearRangeSearchTest {

    @Test
    public void testAllMatches() {
        Term a = new Term("hello", 10);
        Term b = new Term("hell", 9);
        Term c = new Term("he", 8);
        Term d = new Term("h", 7);
        Term[] terms = new Term[4];
        terms[0] = a;
        terms[1] = b;
        terms[2] = c;
        terms[3] = d;

        LinearRangeSearch test = new LinearRangeSearch(terms);
        Term[] result = test.allMatches("he");
        System.out.println(Arrays.toString(result));
    }
}
