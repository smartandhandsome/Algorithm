import java.util.*;

class Solution {
    static int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE};
    static Map<Integer, Map<Integer, Integer>> path = new HashMap<>();
    static Set<Integer> summit = new HashSet<>();
    static Set<Integer> gate = new HashSet<>();
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        for (int i = 1; i <= n; i++) {
            path.put(i, new HashMap<>());
        }
        
        for (int s : summits) {
            summit.add(s);
        }
        for (int g : gates) {
            gate.add(g);
        }

        for (int i = 0; i < paths.length; i++) {
            int node1 = paths[i][0];
            int node2 = paths[i][1];
            int num = paths[i][2];

            path.get(node1).put(node2, num);
            path.get(node2).put(node1, num);
        }
        
        dij(n, gates);

        return answer;
    }
    
    static void dij(int n, int[] gates) {
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        
        for (int i = 0; i < gates.length; i++) {
            q.add(new int[] {gates[i], 0});
        }
        int[] dij = new int[n + 1];
        Arrays.fill(dij, Integer.MAX_VALUE);
        
        while(!q.isEmpty()) {
            int[] node = q.poll();
            if (summit.contains(node[0]) || dij[node[0]] < node[1]) {
                continue;
            }
            
            Set<Integer> nexts = path.get(node[0]).keySet();
            for (int next : nexts) {
                int weight = Math.max(path.get(node[0]).get(next), node[1]);
                if (weight < dij[next] && !gate.contains(next)) {
                    dij[next] = weight;
                    q.add(new int[] {next, weight});
                }
            }
        }
        
        for (int s : summit) {
            if (answer[1] >= dij[s]) {
                if (answer[1] == dij[s]) {
                    if (answer[0] > s) {
                        answer = new int[] {s, dij[s]};
                    }
                } else {
                    answer = new int[] {s, dij[s]};
                }
            }
        }
        
    }

}













































































