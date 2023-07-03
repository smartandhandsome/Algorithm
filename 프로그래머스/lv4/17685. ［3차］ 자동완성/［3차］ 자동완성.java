import java.util.*;

class Solution {
    public int solution(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        // print(trie.root);
        trie.root.cnt = -1;
        int answer = 0;
        Arrays.sort(words);
        for (String word : words) {
            answer += trie.getAmount(word);
        }
        
        return answer;
    }
    
    void print(Node node) {
        if (node.next.size() == 0) {
            return;
        }
        for (char c : node.next.keySet()) {
            System.out.println(c + " " + node.next.get(c).cnt);
            print(node.next.get(c));
        }
    }
}

class Trie {
    Node root;
    
    public Trie() {
        root = new Node((char) 0);
    }
    
    public void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            if (node.next.containsKey(c)) {
                node = node.next.get(c);
                node.cnt++;
            } else {
                Node newNode = new Node(c);
                node.next.put(c, newNode);
                node = newNode;
            }
        }
    }
    
    public int getAmount(String word) {
        Node node = root;
        int ret = 0;
        for (char c : word.toCharArray()) {
            if (node.cnt == 1) {
                return ret;
            }
            node = node.next.get(c);
            ret++;
        }
        
        return word.length();
    }
}

class Node {
    Character value;
    Map<Character, Node> next = new HashMap<>();
    int cnt = 1;
    
    public Node(char value) {
        this.value = value;
    }
    
}