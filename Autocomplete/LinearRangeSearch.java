package autocomplete;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearRangeSearch implements Autocomplete {
    private Term[] terms;

    /**
     * Validates and stores the given array of terms.
     * Assumes that the given array will not be used externally afterwards (and thus may directly
     * store and mutate it).
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public LinearRangeSearch(Term[] terms) {
        if (terms == null || Arrays.asList(terms).contains(null)) {
            throw new IllegalArgumentException("Array is null");
        }
        this.terms = terms;
    }



    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public Term[] allMatches(String prefix) {
        if (prefix.equals(null)) {
            throw new IllegalArgumentException("input prefix is null");
        } else {
            List<Term> makeList = new ArrayList<Term>();
            int r = prefix.length();
            for (int i = 0; i < terms.length; i++) {
                if (terms[i].queryPrefix(r).equals(prefix)) {
                    makeList.add(terms[i]);
                }

            }
            makeList.sort(Term::compareToByReverseWeightOrder);
            Term[] matches = makeList.toArray(new Term[makeList.size()]);
            return matches;

        }
    }
}

