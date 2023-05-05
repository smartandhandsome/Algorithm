import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        Queue<Word> q = new LinkedList<>();
        q.offer(new Word(begin, -1, 0));
        
        while(!q.isEmpty()) {
            Word w = q.poll();
            if (w.word.equals(target)) {
                return w.count;
            }
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canChange(w.word, words[i])) {
                    q.add(new Word(words[i], i, w.count+1));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }
    
    public static boolean canChange(String a, String b) {
        // 모든 단어의 길이는 같다.
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
                if (count >= 2) {
                    return false;
                }
            }
        }
        return count == 1 ? true : false;
    }
}

class Word {
    String word;
    int idx;
    int count;
    
    Word(String word, int idx, int count) {
        this.word = word;
        this.idx = idx;
        this.count = count;
    }
}