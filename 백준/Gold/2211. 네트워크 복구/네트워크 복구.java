import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static Map<Integer, List<Integer[]>> graph;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            List<Integer[]> listA = graph.getOrDefault(A, new ArrayList<>());
            listA.add(new Integer[]{B, C});
            List<Integer[]> listB = graph.getOrDefault(B, new ArrayList<>());
            listB.add(new Integer[]{A, C});
            graph.put(A, listA);
            graph.put(B, listB);
        }

        int[] dijkstra = new int[N + 1];
        Arrays.fill(dijkstra, Integer.MAX_VALUE);
        dijkstra[0] = 0;
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        q.add(new int[]{0, 1});
        boolean[] visited = new boolean[N + 1];
        Map<Integer, Integer> map = new HashMap<>();
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int w = t[0];
            int node = t[1];
            visited[node] = true;
            for (Integer[] integers : graph.get(node)) {
                if (visited[integers[0]]) {
                    continue;
                }
                int cand = w + integers[1];
                if (dijkstra[integers[0]] > cand) {
                    dijkstra[integers[0]] = cand;
                    map.put(integers[0], node);
                    q.add(new int[]{cand, integers[0]});
                }
            }
        }
        System.out.println(N - 1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
    }
}
