import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        Queue<Integer> q = new PriorityQueue<>();
        for (int s : scoville) {
            q.add(s);
        }
        
        while (q.peek() < K) { 
            if (q.size() < 2) {
                return -1;
            }
            int s1 = q.poll();
            int s2 = q.poll();
            int sum = s1 + (s2 * 2);
            q.add(sum);
            answer++;
        }
        
        
        return answer;
    }
}