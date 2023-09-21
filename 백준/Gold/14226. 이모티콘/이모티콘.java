import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int S = Integer.parseInt(br.readLine());

        boolean[][] visit = new boolean[2001][2001];  // 화면, 클립보드

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0, 0}); // 화면, 클립보드, 초
        while (!q.isEmpty()) {
            int[] p = q.poll();
            if (p[0] == S) {
                System.out.println(p[2]);
                break;
            }
            // 복사
            if (!visit[p[0]][p[0]]) {
                visit[p[0]][p[0]] = true;
                q.add(new int[]{p[0], p[0], p[2] + 1});
            }

            // 붙여넣기
            if (p[0] + p[1] < 2001 && !visit[p[0] + p[1]][p[1]]) {
                visit[p[0] + p[1]][p[1]] = true;
                q.add(new int[]{p[0] + p[1], p[1], p[2] + 1});
            }

            // 삭제
            if (p[0] - 1 > 1 && !visit[p[0] - 1][p[1]]) {
                visit[p[0] - 1][p[1]] = true;
                q.add(new int[]{p[0] - 1, p[1], p[2] + 1});
            }
        }
    }
}
