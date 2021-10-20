package learning.trie;

import java.util.*;

public class Dictionary {

    private int size;
    private TrieNode root;

    public Dictionary() {
        root = new TrieNode();
    }

    /**
     * Add words in Dictionary
     *
     * @param word
     */
    public void add(String word) {
        if (word.length() == 0)
            return;
        TrieNode current = root;
        char[] chArr = word.toCharArray();
        for (int i = 0; i < chArr.length - 1; i++) {
            char ch = chArr[i];
            if (!current.map.keySet().contains(ch))
                current.map.put(ch, new TrieNode(ch));
            else
                current.map.get(ch).prefixCount++;

            current = current.map.get(ch);
        }

        // For last character increment word ending
        char ch = chArr[chArr.length - 1];
        // Entry on a single character word, where this character was not entered earlier
        if (!current.map.keySet().contains(ch))
            current.map.put(ch, new TrieNode(ch));
        else
            current.map.get(ch).prefixCount++; // In case when we have deleted the word initially and then re adding it

        current.map.get(ch).wordEnding++;
        size++;
    }


    /**
     * Search words in Dictionary
     *
     * @param word
     * @return
     */
    public boolean contains(String word) {
        if (word.length() == 0)
            return false;

        TrieNode current = root;
        for (var ch : word.toCharArray()) {
            if (!current.map.keySet().contains(ch))
                return false;
            current = current.map.get(ch);
        }

        // Checking if for last character wordEnding > 0
        return current.wordEnding > 0;
    }


    /**
     * Remove words from Dictionary
     *
     * @param word
     * @return
     */
    public boolean remove(String word) {
        if (word.length() == 0)
            return false;

//        Checking if Dictionary even contains the word to be removed:
        if (!contains(word))
            return false;

        TrieNode current = root;
        char[] chArr = word.toCharArray();
        for (int i = 0; i < chArr.length - 1; i++) {
            char ch = chArr[i];
            current.map.get(ch).prefixCount--;
            current = current.map.get(ch);
        }

        // For last character decrement word ending
        char ch = chArr[chArr.length - 1];
        // Supporting multiple words ; else set wordEnding = 0
        current.map.get(ch).wordEnding--;
        current.map.get(ch).prefixCount--;
        size--;
        return true;
    }

    /**
     * Find words starting with prefix
     *
     * @param prefix
     * @return -1 if prefix not present
     */
    public int getPrefixCount(String prefix) {
        if (prefix.length() == 0)
            return -1;

        TrieNode current = root;
        for (var ch : prefix.toCharArray()) {
            if (!current.map.keySet().contains(ch))
                return -1;
            current = current.map.get(ch);
        }

        return current.prefixCount;
    }


    /**
     * Auto Complete feature for dictionary
     *
     * @param word
     * @param count
     * @return null if input word is not present in dictionary or count size is less than 1
     */
    public List<String> getAutoCompleteOptions(String word, int count) {
        if (count < 1 || word.length() == 0) return null;

        List<String> options = new LinkedList<>();
        TrieNode current = root;
        for (var ch : word.toCharArray()) {
            if (!current.map.keySet().contains(ch))
                return options;
            current = current.map.get(ch);
        }
        findOptions(current, options, count, word);
        return options;
    }

    private boolean findOptions(TrieNode node, List<String> options, int optionsNeeded, String wordSoFar) {
//        Checking if we have found the required no. of options, then return
        if (options.size() == optionsNeeded) return true;

//        Pre order traversal - Adding node first (if valid)
        if (node.wordEnding > 0)
            options.add(wordSoFar);

//        If there are no more characters from the current word   -- optional works without below 2 lines
//        if (node.map.keySet().size() == 0)
//            return false;

        for (var ch : node.map.keySet())
            if (findOptions(node.map.get(ch), options, optionsNeeded, wordSoFar + node.map.get(ch).data))
                return true;

        return false;
    }

    public void printAutoCompleteOptions(List<String> options) {
        if (options == null) return;
        for (var option : options)
            System.out.print(option + " ");
    }

    //    Size of dictionary
    public int size() {
        return size;
    }

    private class TrieNode {
        char data;
        int wordEnding = 0;
        int prefixCount = 0;
        Map<Character, TrieNode> map;

        public TrieNode() {
            map = new TreeMap<>();
        }

        public TrieNode(char data) {
            this.data = data;
            this.prefixCount = 1;
            // We need to use TreeMap for auto complete feature to work
            map = new TreeMap<>();
        }
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();

//        Adding values in dictionary :
        dictionary.add("aa");
        dictionary.add("ab");
        dictionary.add("abc");
        dictionary.add("abd");
        dictionary.add("acd");
        dictionary.add("cat");
        dictionary.add("ball");
        dictionary.add("e");
        System.out.println("Size of dictionary : " + dictionary.size());

//        Searching/Contains in dictionary:
        System.out.println("Searching ball , expected true ; actual : " + dictionary.contains("ball"));
        System.out.println("Searching bat , expected false ; actual : " + dictionary.contains("bat"));
        System.out.println("Searching e , expected true ; actual : " + dictionary.contains("e"));
        System.out.println("Searching a , expected false ; actual : " + dictionary.contains("a"));
        System.out.println("Searching '' , expected false ; actual : " + dictionary.contains(""));
        System.out.println();

//        Auto complete
        List<String> options;
        String word = "a";
        options = dictionary.getAutoCompleteOptions(word, 6);
        dictionary.printAutoCompleteOptions(options);
        System.out.println("\n");

//        Removing values from dictionary:
        System.out.println(String.format("Removing %s ; excepted : actual :: true : %s", "ball", dictionary.remove("ball")));
        System.out.println(String.format("Removing %s ; excepted : actual :: false : %s", "bat", dictionary.remove("bat")));
        System.out.println(String.format("Removing %s ; excepted : actual :: true : %s", "e", dictionary.remove("e")));
        System.out.println(String.format("Removing %s ; excepted : actual :: false : %s", "a", dictionary.remove("a")));
        System.out.println(String.format("Removing %s ; excepted : actual :: true : %s", "aa", dictionary.remove("aa")));
        System.out.println();

        System.out.println("Size of dictionary : " + dictionary.size());
        System.out.println();

//        Get prefix count in Dictionary:
        System.out.println(String.format("Prefix count for %s ; excepted : actual :: 0 : %s", "aa", dictionary.getPrefixCount("aa")));
        System.out.println(String.format("Prefix count for %s ; excepted : actual :: 3 : %s", "ab", dictionary.getPrefixCount("ab")));
        System.out.println(String.format("Prefix count for %s ; excepted : actual :: 4 : %s", "a", dictionary.getPrefixCount("a")));

        dictionary.add("e");
        dictionary.add("a");
        dictionary.add("aa");
        dictionary.add("elephant");
        System.out.println("Size of dictionary : " + dictionary.size());
        System.out.println();


        word = "a";
        options = dictionary.getAutoCompleteOptions(word, 2);
        dictionary.printAutoCompleteOptions(options);
        System.out.println("\n");
    }
}



/*
        if (word.length() == 0)
            return;
        // Checking if root contains the pointer to first character node
        char firstChar = word.charAt(0);
        if (!root.map.keySet().contains(firstChar)){
            root.map.put(firstChar,new TierNode(firstChar));
        }
 */