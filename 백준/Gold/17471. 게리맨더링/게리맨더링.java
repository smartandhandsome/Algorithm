import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static int[] pop;
    private static int sum;
    private static int answer = Integer.MAX_VALUE;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        pop = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sum = Arrays.stream(pop).sum();
        for (int i = 0; i < N; i++) {
            List<Integer> line = Arrays.stream(br.readLine().split(" ")).map(s -> Integer.parseInt(s) - 1).collect(Collectors.toList());
            graph.put(i, line.subList(1, line.size()));
        }

        for (int i = 1; i <= N / 2; i++) {
            combi(0, N, i, new ArrayList<>());
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void combi(int start, int n, int r, List<Integer> list) {
        if (list.size() == r) {
            int s = list.stream().mapToInt(integer -> pop[integer]).sum();
            List<Integer> another = IntStream.range(0, n).filter(i -> !list.contains(i)).boxed().collect(Collectors.toList());
            if (answer > Math.abs(sum - 2 * s) && isConnect(n, list) && isConnect(n, another)) {
                answer = Math.abs(sum - 2 * s);
            }
            return;
        }

        for (int i = start; i < n; i++) {
            list.add(i);
            combi(i + 1, n, r, list);
            list.remove((Integer) i);
        }
    }

    private static boolean isConnect(int n, List<Integer> list) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        int start = list.get(0);
        q.add(start);
        visited[start] = true;
        int connect = 1;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (Integer next : graph.get(node)) {
                if (!visited[next] && list.contains(next)) {
                    q.add(next);
                    visited[next] = true;
                    connect++;
                }
            }
        }
        return connect == list.size();
    }

}
