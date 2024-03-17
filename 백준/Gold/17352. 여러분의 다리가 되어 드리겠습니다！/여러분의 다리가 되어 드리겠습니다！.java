import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N - 2; i++) {
            String[] s = br.readLine()
                    .split(" ");

            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            map.get(a).add(b);
            map.get(b).add(a);
        }

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int next : map.get(node)) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
        for (int i = 2; i <= N; i++) {
            if (!visited[i]) {
                System.out.println(1 + " " + i);
                break;
            }
        }
    }
}
