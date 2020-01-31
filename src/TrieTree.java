import java.util.LinkedList;

public class TrieTree {

//    public static void main(String[] args) {
//        String keys[] = {"the", "#", "there", "answer", "any",
//                "by", "bye", "their"};
//
//        String output[] = {"Not present in trie", "Present in trie"};
//
//
//        // Construct trie
//        int i;
//        for (i = 0; i < keys.length; i++)
////            insert(keys[i]);
//
//            // Search for different keys
////        if(search("#") == true)
//            System.out.println("# --- " + output[1]);
////        else System.out.println("# --- " + output[0]);
//    }

    TrieNode root;


    void insert(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode p = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level);
            if (p.children[index] == null) {
                p.children[index] = new TrieNode();
            }

            p = p.children[index];
        }
        p.isWord = true;
    }

    boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode p = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level);

            if (p.children[index] == null) {
                return false;
            }

            p = p.children[index];
        }
        return p != null && p.isWord;
    }

     TrieNode findLongest() {
        LinkedList<TrieNode> queue = new LinkedList<>();
        queue.push(root);
        TrieNode current = null;
        while (!queue.isEmpty()) {
            current = queue.pop();
            if (current.children != null) {
                for (TrieNode children : current.children) {
                    queue.push(children);
                }
            }
        }
        return current;


    }
}

    class TrieNode {

        static final int SIZE = 300;
        TrieNode[] children = new TrieNode[SIZE];
        boolean isWord;

        public TrieNode() {
            isWord = false;
            for (int i = 0; i < SIZE; i++) {
                children[i] = null;
            }
        }
    }
