package autocomplete;

import java.util.Arrays;

public class BinaryRangeSearch implements Autocomplete {
    //make private
    private Term[] terms;

    /**
     * Validates and stores the given array of terms.
     * Assumes that the given array will not be used externally afterwards (and thus may directly
     * store and mutate it).
     *
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public BinaryRangeSearch(Term[] terms) {
        if (terms == null || Arrays.asList(terms).contains(null)) {
            throw new IllegalArgumentException("Array is null");
        }
        Arrays.sort(terms);
        this.terms = terms;
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     *
     * @throws IllegalArgumentException if prefix is null
     */

    //Used the website https://www.geeksforgeeks.org/find-first-and-last-positions-of-an-element-in-a-sorted-array/
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix passed is null");
        }

        int firstIndex = findFirst(prefix, 0, terms.length - 1);
        int lastIndex = findLast(prefix, 0, terms.length - 1);
        if (firstIndex == -1) {
            return new Term[0];
        } else {
            Term[] matches = new Term[lastIndex - firstIndex + 1];
            int index = 0;
            for (int i = firstIndex; i <= lastIndex; i++) {
                matches[index] = terms[i];
                index += 1;
            }

            Arrays.sort(matches, TermComparators.byReverseWeightOrder());
            return matches;
        }
    }

    private int findFirst(String prefix, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2; // this is fine
            String termBefore = terms[mid - 1].queryPrefix(prefix.length());
            String term = terms[mid].queryPrefix(prefix.length());
            int compare = prefix.compareTo(term);
            int compareBefore = prefix.compareTo(termBefore);

            if (mid == 0 || compareBefore > 0 && term.equals(prefix)) {
                return mid;
            } else if (compare > 0) {
                return findFirst(prefix, mid + 1, high);
            } else {
                return findFirst(prefix, low, mid - 1);
            }
        } else {
            return -1;
        }
    }

    private int findLast(String prefix, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            String termAfter = terms[mid + 1].queryPrefix(prefix.length());
            String term = terms[mid].queryPrefix(prefix.length());
            int compare = prefix.compareTo(term);
            int compareAfter = prefix.compareTo(termAfter);

            if (mid == terms.length - 1 || compareAfter < 0 && term.equals(prefix)) {
                return mid;
            } else if (compare < 0) {
                return findLast(prefix, low, mid - 1);
            } else {
                return findLast(prefix, mid + 1, high);
            }
        } else {
            return -1;
        }
    }


}
