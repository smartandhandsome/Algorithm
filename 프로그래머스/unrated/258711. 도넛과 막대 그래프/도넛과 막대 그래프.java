import java.util.*;

class Solution {
    static boolean[] visited = new boolean[1000001];
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        // 생성된 점의 특징 나가는 간선만 존재
        Map<Integer, int[]> forSearch = new HashMap<>();
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            map.put(edge[0], list);
            
            int[] a = forSearch.getOrDefault(edge[0], new int[2]);
            a[0]++;
            forSearch.put(edge[0], a);
            int[] b = forSearch.getOrDefault(edge[1], new int[2]);
            b[1]++;
            forSearch.put(edge[1], b);
        }
        
        int newNode = 0;
        for (Map.Entry<Integer, int[]> entry : forSearch.entrySet()) {
            int[] temp = entry.getValue();
            if (temp[0] > 1 && temp[1] == 0) {
                newNode = entry.getKey();
                print(newNode);
                break;
            }
        }
        answer[0] = newNode;
        
        // 각 그래프의 특징
        
        // 도넛: n개 노드 n개 정점 n개 간선 
        // 8자 모양: 
        // 두 개가 햇갈리겠네 -> bfs
        // 막대모양: n, n-1 사이클 도는거 없음
        
        // print(map);
        
        for (int next : map.get(newNode)) {
            int index = bfs(next, map);
            answer[index]++;
        }
        
        return answer;
    }
    
    public static void print(Object o) {
        System.out.println(o);
    }
    
    public int bfs(int node, Map<Integer, List<Integer>> map) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(node);
        visited[node] = true;
        
        while(!q.isEmpty()) {
            int n = q.poll();
            
            List<Integer> nexts = map.getOrDefault(n, Collections.emptyList());
            if (nexts.isEmpty()) {
                return 2;
            }
            if (nexts.size() == 2) {
                return 3;
            }
            
            for (int next : nexts) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;                    
                }
            }
        }
        return 1;
    }
}