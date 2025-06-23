import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int n = priorities.length;

        Queue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(i -> -i));
        Queue<Integer> waiting = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            q.add(priorities[i]);
            waiting.add(i);
        }

        int count = 1;
        while (true) {
            int target = q.poll();
            System.out.println("target: " + target);
            while (!waiting.isEmpty()) {
                int w = waiting.poll();
                System.out.println("w: " + w);
                System.out.println("priorities[w]: " + priorities[w]);
                if (priorities[w] == target) {
                    if (w == location) {
                        return count;
                    }
                    count++;
                    break;
                } else {
                    waiting.add(w);
                }
            }
        }
        
    }
}