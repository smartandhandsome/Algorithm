import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");

        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i < N; i++) {
            int[] line = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.compute(line[0], (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(new int[]{line[1], line[2]});
                return v;
            });
            graph.compute(line[1], (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(new int[]{line[0], line[2]});
                return v;
            });
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
            int[] line = Arrays.stream(in.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int dfs = dfs(graph, line[0], line[1], 0, new boolean[N + 1]);
            out.append(String.valueOf(dfs)).append("\n");
        }
        out.flush();

        in.close();
        out.close();
    }

    public static int dfs(Map<Integer, List<int[]>> graph, int node, int target, int cost, boolean[] visited) {
        visited[node] = true;
        if (node == target) {
            return cost;
        }
        for (int[] next : graph.get(node)) {
            if (visited[next[0]]) {
                continue;
            }

            int ret = dfs(graph, next[0], target, cost + next[1], visited);
            if (ret != -1) {
                return ret;
            }
        }
        return -1;
    }
}

