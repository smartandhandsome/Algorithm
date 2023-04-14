import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int N;
    static int K;

    static Set<Integer> group = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 소풍 갈 수 있는 최대 인원 수
        N = Integer.parseInt(st.nextToken());

        int F = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (!graph[s].contains(e)) {
                graph[s].add(e);
                graph[e].add(s);
            }
        }

        for (int i = 1; i <= N; i++) { // 초기 세팅 기준 친구 설정
            group.clear();
            group.add(i);
            check(i);
        }
        System.out.println(-1);
    }

    public static void check(int idx) {
        if (group.size() >= K) {
            for (Integer integer : group) {
                System.out.println(integer);
            }
            System.exit(0);
        }
        if (N  - idx + group.size() < K)
            return;
        for (int i = idx + 1; i <= N; i++) {
            if (isFriends(i)) {
                group.add(i);
                check(i);
                group.remove((Integer) i);
            }
        }
    }

    public static boolean isFriends(int idx) {
        int cnt = 0;
        for (Integer friend : graph[idx]) {
            if (group.contains(friend)) {
                cnt++;
            }
            if (cnt == group.size()) {
                return true;
            }
        }
        return false;
    }
}

