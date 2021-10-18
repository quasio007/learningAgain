package learning.trie;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private TierNode root;

    private class TierNode {
        Map<Character, TierNode> map = new HashMap<>();
    }

    public static void main(String[] args) {

    }
}
