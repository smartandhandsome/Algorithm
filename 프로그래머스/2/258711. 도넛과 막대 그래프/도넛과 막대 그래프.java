import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
        
        int max = -1; // visit 행렬 만들라고
        Set<Integer> in = new HashSet<>(); // 들어오는거 체크
        
        Map<Integer, List<Integer>> direct = new HashMap<>(); // 그래프 만든 거
        for (int[] edge : edges) {
            List<Integer> l = direct.computeIfAbsent(edge[0], k -> new ArrayList<>());
            in.add(edge[1]);
            l.add(edge[1]);
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }
        
        int node = -1;
        for (Integer i : direct.keySet()) {
            if (!in.contains(i) && direct.get(i).size() >= 2) {
                node = i;
                break;
            }
        } // 정점 체크
        
        answer[0] = node;
        boolean[] visited = new boolean[max + 1];
        for (int i : direct.get(node)) {
            visited[i] = true;
            Queue<Integer> q = new ArrayDeque<>();
            q.add(i);
            
            while(!q.isEmpty()) {
                int n = q.poll();
                List<Integer> nexts = direct.computeIfAbsent(n, k -> new ArrayList<>());
                if (nexts.isEmpty()) {
                    answer[2]++;
                    break;
                }
                if (nexts.size() > 1) {
                    answer[3]++;
                    break;
                }
                
                boolean flag = false;
                for(int next : nexts) {
                    if (visited[next]) {
                        continue;
                    }
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
        answer[1] = direct.get(node).size() - answer[2] - answer[3];
        return answer;
    }
}
