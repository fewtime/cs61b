public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<Character>();

        for (int i = 0; i < word.length(); ++i) {
            res.addLast(word.charAt(i));
        }

        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordList = wordToDeque(word);

        return checkPalindrome(wordList);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordList = wordToDeque(word);

        return checkPalindrome(wordList, cc);
    }

    private boolean checkPalindrome(Deque<Character> wordList) {
        if (wordList.size() < 2) {
            return true;
        }

        if (wordList.removeFirst() == wordList.removeLast()) {
            return checkPalindrome(wordList);
        } else {
            return false;
        }
    }

    private boolean checkPalindrome(Deque<Character> wordList, CharacterComparator cc) {
        if (wordList.size() < 2) {
            return true;
        }

        if (cc.equalChars(wordList.removeFirst(), wordList.removeLast())) {
            return checkPalindrome(wordList, cc);
        } else {
            return false;
        }
    }
}
