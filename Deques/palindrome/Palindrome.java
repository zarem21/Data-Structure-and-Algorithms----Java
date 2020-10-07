package deques.palindrome;

import deques.Deque;
import deques.LinkedDeque;

public class Palindrome {
    // Given a word, should return a deque where characters appear in the same order as the word
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> linkedWords = new LinkedDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            linkedWords.addLast(word.charAt(i));

        }
        return linkedWords;
    }

    // I worked closely with another student named Jia Ling chan to come up with the recursive solution to isPalindrome.
    public boolean isPalindrome(String word) {
        Deque<Character> palindrome = wordToDeque(word);
        return isPalindrome(palindrome);

    }

    private boolean isPalindrome(Deque<Character> palindrome) {
        if (palindrome.size() == 0 || palindrome.size() == 1) {
            return true;
        } else if (palindrome.removeFirst() == palindrome.removeLast()) {
            return isPalindrome(palindrome);
        }

        return false;
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> oboPalindrome = wordToDeque(word);
        return isPalindrome(oboPalindrome, cc);
    }

    private boolean isPalindrome(Deque<Character> oboPalindrome, CharacterComparator cc) {
        if (oboPalindrome.size() == 1 || oboPalindrome.size() == 0) {
            return true;
        } else if (cc.equalChars(oboPalindrome.removeFirst(), oboPalindrome.removeLast())) {
            return isPalindrome(oboPalindrome, cc);
        }
        return false;
    }
}
