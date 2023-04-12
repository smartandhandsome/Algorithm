import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String N = st.nextToken();
        int K = Integer.parseInt(st.nextToken());
        if (Integer.parseInt(N) > K) {
            int temp = Integer.parseInt(N);
            System.out.println(temp - K);
            while (temp-- >= K) {
                System.out.print((temp+1) + " ");
            }
            System.exit(0);
        }
        Queue<String[]> q = new LinkedList<>();
        boolean[] visited = new boolean[100001]; // bfs 메모리 초과 시 visited 함수 쓰기
        visited[Integer.parseInt(N)] = true;
        q.add(new String[]{N, N});
        while (!q.isEmpty()) {
            String[] ss = q.poll();
            int pos = Integer.parseInt(ss[0]);
            String path = ss[1];
            if (pos == K) {
                st = new StringTokenizer(path);
                System.out.println(st.countTokens() - 1);
                while (st.hasMoreTokens()) {
                    System.out.print(st.nextToken() + " ");
                }
                break;
            }
            if (pos * 2 <= 100000 && pos * 2 >= 0 && !visited[pos * 2]) {
                q.add(new String[]{String.valueOf(pos * 2), path + " " + (pos * 2)});
                visited[pos * 2] = true;
            }
            if (pos - 1 <= 100000 && pos - 1 >= 0 && !visited[pos - 1]) {
                q.add(new String[]{String.valueOf(pos - 1), path + " " + (pos - 1)});
                visited[pos - 1] = true;
            }
            if (pos + 1 <= 100000 && pos + 1 >= 0 && !visited[pos + 1]) {
                q.add(new String[]{String.valueOf(pos + 1), path + " " + (pos + 1)});
                visited[pos + 1] = true;
            }
        }
    }
}
